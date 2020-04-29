// https://docs.cypress.io/api/introduction/api.html

import {
  host
} from "../support";


describe('Init Test', () => {

  context('Load the page', () => {

    it('Visits the app root url', () => {
      cy.visit('http://' + host + ':8844/')
    })

    it('Go to Client Page', () => {
      //cy.visit('/client')
      cy.get('[data-cy=client]').click()
      cy.get('[data-cy=client_title]').contains('SERVICE CLIENT')
    })

  })

  context('Load the data in the page by clicking on each refresh buttons', () => {

    it('Should have 3 items in package list', () => {
      cy.get('[data-cy=refresh_package]').click()
      cy.get("#app > div > main > div > div > div.row.align-center.justify-space-around > div.col.col-4.align-self-center > div:nth-child(2) > div > div:nth-child(2) > div > div.v-data-table__wrapper > table > tbody")
        .as('packages')
      cy.get('@packages').find('tr').should('have.length', 3)
    })

    it('Should have no items in delivery list', () => {
      cy.get('[data-cy=refresh_delivery]').click()
      cy.get("#app > div > main > div > div > div.row.align-center.justify-center > div > div > div > div:nth-child(2) > div > div.v-data-table__wrapper > table > tbody > tr > td")
        .as('deliveries')
      cy.get('@deliveries').contains('No data available')
    })

    it('Should be possible to create a drone', () => {
      var env = `
      <Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
          <Body>
              <addDrone xmlns="http://www.delivrair.fr/backend/transport">
                  <id xmlns="">0</id>
              </addDrone>
          </Body>
      </Envelope>
      `

      cy.request('POST', 'http://'+host+':8080/delivery/webservices/TransportWS?wsdl',
          env)
        .then((response) => {
          // response.body is automatically serialized into JSON
          expect(response.body).to.have.string('<add_drone>true</add_drone>') // true
        })
    })
  })
})

describe('Add delivery test', () => {

  context('Create new delivery', () => {

    it('Cannot create a delivery from a package that does not exist', () => {

      cy.get('[data-cy=id_field]').scrollIntoView().clear({force: true}).type('-1', {force: true})
      cy.get('[data-cy=date_field]').scrollIntoView().clear({force: true}).type('2020-04-18', {force: true})
      cy.get('[data-cy=time_field]').scrollIntoView().clear({force: true}).type('12:00', {force: true})
      cy.get('[data-cy=validate_btn]').scrollIntoView().click()

      cy.wait(500)

      cy.get('[data-cy=failed_alert]').should('be.visible').click()
      cy.get("#app > div.v-application--wrap > main > div > div > div.row.align-center.justify-space-around > div.col.col-4.align-self-center > div:nth-child(1) > div > div.v-alert.v-sheet.theme--light.v-alert--dense.v-alert--outlined.error--text > div > button > span > i")
      .scrollIntoView().click()
      //cy.get('[data-cy=success_alert]').should('be.invisible')
    })

    it('Cannot create a delivery from a date and time that does not match', () => {
      cy.get('[data-cy=id_field]').scrollIntoView().clear({force: true}).type('0', {force: true})
      cy.get('[data-cy=date_field]').scrollIntoView().clear({force: true}).type('2020-04-18', {force: true})
      cy.get('[data-cy=time_field]').scrollIntoView().clear({force: true}).type('00:00', {force: true})
      cy.get('[data-cy=validate_btn]').scrollIntoView().click()

      cy.wait(500)

      cy.get('[data-cy=failed_alert]').should('be.visible')
      cy.get("#app > div.v-application--wrap > main > div > div > div.row.align-center.justify-space-around > div.col.col-4.align-self-center > div:nth-child(1) > div > div.v-alert.v-sheet.theme--light.v-alert--dense.v-alert--outlined.error--text > div > button > span > i")
      .scrollIntoView().click()
    })

    it('Can create a delivery from right data', () => {
      cy.get('[data-cy=id_field]').scrollIntoView().clear({force: true}).type('0', {force: true})
      cy.get('[data-cy=date_field]').scrollIntoView().clear({force: true}).type('2020-04-18', {force: true})
      cy.get('[data-cy=time_field]').scrollIntoView().clear({force: true}).type('12:00', {force: true})
      cy.get('[data-cy=validate_btn]').scrollIntoView().click()

      cy.wait(500)

      cy.get("#app > div.v-application--wrap > main > div > div > div.row.align-center.justify-space-around > div.col.col-4.align-self-center > div:nth-child(1) > div > div.v-alert.v-sheet.theme--dark.v-alert--dense.v-alert--text.success--text")
      .should('be.visible')

      cy.get("#app > div.v-application--wrap > main > div > div > div.row.align-center.justify-space-around > div.col.col-4.align-self-center > div:nth-child(1) > div > div.v-alert.v-sheet.theme--dark.v-alert--dense.v-alert--text.success--text > div > button > span > i")
      .click()
      //cy.get('[data-cy=failed_alert]').should('be.invisible')
    })

    it('Cannot create a delivery from a package that is already assigned ', () => {
      cy.get('[data-cy=id_field]').scrollIntoView().clear({force: true}).type('0', {force: true})
      cy.get('[data-cy=date_field]').scrollIntoView().clear({force: true}).type('2020-04-18', {force: true})
      cy.get('[data-cy=time_field]').scrollIntoView().clear({force: true}).type('18:00', {force: true})
      cy.get('[data-cy=validate_btn]').scrollIntoView().click()

      cy.wait(500)

      cy.get('[data-cy=failed_alert]').should('be.visible')
    })

  })

  context('Update of all components', () => {

    it('The package sould switch to assigned', () => {
      cy.get('[data-cy=refresh_package]').click()
      cy.wait(500)
      cy.get("#app > div.v-application--wrap > main > div > div > div.row.align-center.justify-space-around > div.col.col-4.align-self-center > div:nth-child(2) > div > div:nth-child(2) > div > div.v-data-table__wrapper > table > tbody > tr:nth-child(1)")
      .contains('ASSIGNED')
    })

    it('Sould have the delivery created in list', () => {
      cy.get('[data-cy=refresh_delivery]').click()
      cy.wait(500)
      cy.get("#app > div.v-application--wrap > main > div > div > div.row.align-center.justify-center > div > div > div > div:nth-child(2) > div > div.v-data-table__wrapper > table > tbody > tr")
        .as('deliveries')
      cy.get('@deliveries').contains('READY')
    })

    it('There is a slot taken in the calendar', () => {
      //cy.visit('/client')
    })

  })


})
