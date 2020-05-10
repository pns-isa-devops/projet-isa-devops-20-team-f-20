// https://docs.cypress.io/api/introduction/api.html

import {
  host
} from "../support";


describe('Init Test', () => {

  context('Load the page', () => {

    it('Go to Manu Page', () => {
      cy.visit('http://' + host + ':8844/manu')

      cy.get('[data-cy=manu_title]').contains('MANUTENTIONNAIRE')
    })

  })

  context('Setup environnement for future test', () => {

    it('Retreive incomming packages', () => {
      // build SOAP request
      var env =
        `<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
                <Body>
                    <retrieveIncomingPackages xmlns="http://www.polytech.unice.fr/si/4a/isa/drone-delivery/logistic"/>
                </Body>
            </Envelope>`

      cy.request('POST', 'http://' + host + ':8080/scheduler/webservices/LogisticWS?wsdl', env)
        .then((response) => {
          // response.body is automatically serialized into JSON
          expect(response.body).to.have.string('retrieveIncomingPackages')
        })
    })

    it('Create a drone', () => {
      var env = `
          <Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
              <Body>
                  <addDrone xmlns="http://www.polytech.unice.fr/si/4a/isa/drone-delivery/transport">
                      <id xmlns="">0</id>
                  </addDrone>
              </Body>
          </Envelope>
          `

      cy.request('POST', 'http://' + host + ':8080/scheduler/webservices/TransportWS?wsdl', env)
        .then((response) => {
          // response.body is automatically serialized into JSON
          expect(response.body).to.have.string('<add_drone>true</add_drone>') // true
        })

    })

    it('Create a delivery', () => {
      var env = `
          <Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
                        <Body>
                            <planDelivery xmlns="http://www.polytech.unice.fr/si/4a/isa/drone-delivery/scheduler">
                                <id xmlns="">0</id>
                                <jour xmlns="">08</jour>
                                <mois xmlns="">05</mois>
                                <annee xmlns="">2020</annee>
                                <heure xmlns="">08</heure>
                                <minute xmlns="">00</minute>
                            </planDelivery>
                        </Body>
                    </Envelope>
          `

      cy.request('POST', 'http://' + host + ':8080/scheduler/webservices/SchedulerWS?wsdl', env)
        .then((response) => {
          // response.body is automatically serialized into JSON
          expect(response.body).to.have.string('true') // true
        })

    })
  })

  context('Load the data in the page by clicking on each refresh buttons', () => {

    it('Should have 10 items in package list', () => {
      cy.get('[data-cy=refresh_package]').click()

      cy.wait(1000)

      cy.get("#app > div > main > div > div > div > div.col.col-3.align-self-center > div.row.align-center.justify-center > div > div > div > div:nth-child(2) > div > div.v-data-table.elevation-4.theme--light > div.v-data-table__wrapper > table > tbody")
        .as('packages')
      cy.get('@packages').find('tr').should('have.length', 10)
    })

    it('Should have the first package assigned', () => {
      cy.get('[data-cy=refresh_package]').click()

      cy.wait(1000)

      cy.get("#app > div > main > div > div > div > div.col.col-3.align-self-center > div.row.align-center.justify-center > div > div > div > div:nth-child(2) > div > div.v-data-table.elevation-4.theme--light > div.v-data-table__wrapper > table > tbody > tr:nth-child(1) > td.text-center > span > span")
        .as('package')
      cy.get('@package').contains('ASSIGNED')
    })
  })

})

describe('Change status tests', () => {

  context('Change package status', () => {

    it('Cannot change status for a package that does not exist', () => {

      cy.get('[data-cy=manu_package_id]').scrollIntoView().clear({
        force: true
      }).type('-1', {
        force: true
      })

      //cy.get("manu_package_status")
      //cy.get("manu_package_status_col")
      
      cy.get("#app > div.v-application--wrap > main > div > div > div > div.col.col-3.align-self-center > div.v-card.v-sheet.theme--light > div > form > div:nth-child(1) > div.col.col-7 > div > div > div.v-input__slot > div.v-select__slot")
        .click({
          force: true
        })

      cy.get("#list-item-159-0").click({
        force: true
      })
      cy.get('[data-cy=manu_package_status_button]').scrollIntoView().click()

      cy.wait(1000)

      cy.get('[data-cy=manu_package_failed_alert]').should('be.visible').click()

    })

    it('Can change status for right a package', () => {
      cy.get('[data-cy=manu_package_id]').scrollIntoView().clear({
        force: true
      }).type('1', {
        force: true
      })

      cy.get("#app > div.v-application--wrap > main > div > div > div > div.col.col-3.align-self-center > div.v-card.v-sheet.theme--light > div > form > div:nth-child(1) > div.col.col-7 > div > div > div.v-input__slot > div.v-select__slot")
        .click({
          force: true
        })


      cy.get("#list-item-159-1").click({
        force: true
      })
      cy.get('[data-cy=manu_package_status_button]').scrollIntoView().click()

      cy.wait(1000)


      cy.get('[data-cy=manu_package_success_alert]').should('be.visible').click({
        force: true
      })
    })

  })

  context('Launch a delivery', () => {

    
    it('Cannot launch a delivery with a non exiting delivery', () => {
      cy.get('[data-cy=refresh_delivery]').click({force: true})
      cy.get('[data-cy=manu_launch_delivery_delivery_id]').scrollIntoView().clear({
        force: true
      }).type('-1', {
        force: true
      })
      cy.get('[data-cy=manu_launch_delivery_button]').scrollIntoView().click({
        force: true
      })

      cy.wait(1000)

      cy.get('[data-cy=manu_delivery_failed_alert]').should('be.visible').click({
        force: true
      })
    })

    it('Can launch a delivery with right one', () => {
      
      cy.get('#app > div > main > div > div > div > div.col.col-3.align-self-start > div.row.align-center.justify-center > div > div > div > div:nth-child(2) > div > div.v-data-table.elevation-4.theme--light > div.v-data-table__wrapper > table > tbody > tr:nth-child(1) > td.text-start')
      .invoke('text').then((elem) => {
        //let id = $elem.text()
        cy.get('[data-cy=manu_launch_delivery_delivery_id]').scrollIntoView().clear({
          force: true
        }).type(elem, {
          force: true
        })
      })

      cy.get('[data-cy=manu_launch_delivery_button]').scrollIntoView().click({
        force: true
      })

      cy.wait(1000)

      cy.get('[data-cy=manu_delivery_success_alert]').should('be.visible').click({
        force: true
      })

      // let id = cy.get("#app > div > main > div > div > div > div.col.col-3.align-self-start > div.row.align-center.justify-center > div > div > div > div:nth-child(2) > div > div.v-data-table.elevation-4.theme--light > div.v-data-table__wrapper > table > tbody > tr:nth-child(1) > td.text-start")
      // .invoke('text')


    })
  })
})