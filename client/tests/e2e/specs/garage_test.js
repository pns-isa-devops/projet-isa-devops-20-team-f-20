// https://docs.cypress.io/api/introduction/api.html

import {
    host
  } from "../support";
  
  
  describe('Init Test', () => {
  
    context('Load the page', () => {
  
      it('Go to Garage Page', () => {
        cy.visit('http://' + host + ':8844/garage')

        cy.get('[data-cy=garage_title]').contains('GARAGE')
      })
  
    })
  
    context('Load the data in the page by clicking on each refresh buttons', () => {
  
      // TODO : how many drone ?
      it('Should have n items in drone list', () => {
        cy.get('[data-cy=refresh_drone]').click()
  
        cy.wait(5000)
        
        // cy.get("#app > div.v-application--wrap > main > div > div > div > div.col.col-3.align-self-center > div > div > div > div > div > div > div:nth-child(2) > div > div.v-data-table__wrapper > table > tbody")
        //   .as('packages')
        // cy.get('@packages').find('tr').should('have.length', 3)
      })
    })
  })
  
  describe('Change status tests', () => {
  
    context('Change drone status', () => {
  
      it('Cannot change status for a drone that does not exist', () => {

        cy.get('[data-cy=garage_drone_id]').scrollIntoView().clear({force: true}).type('-1', {force: true})
        // cy.get('[data-cy=manu_package_status]').scrollIntoView().clear({force: true}).select('WAITING', {force: true})
        // cy.get('#inputs-16').scrollIntoView().clear({force: true}).select('WAITING', {force: true})
        cy.get("#app > div > main > div > div > div:nth-child(2) > div > div > div > form > div > div:nth-child(2) > div > div > div.v-input__slot > div.v-select__slot > div.v-input__append-inner")
        .click({force: true})
        cy.get("#list-item-58-0").click({force: true})
        cy.get('[data-cy=garage_drone_status_button]').scrollIntoView().click({force: true})

        cy.wait(1000)

        // popup alert doit apparaitre
        // TODO : change data-cy
        // cy.get('[data-cy=failed_alert]').should('be.visible').click()
        // cy.get("#app > div.v-application--wrap > main > div > div > div.row.align-center.justify-space-around > div.col.col-4.align-self-center > div:nth-child(1) > div > div.v-alert.v-sheet.theme--light.v-alert--dense.v-alert--outlined.error--text > div > button > span > i")
        // .scrollIntoView().click()
      })
  
      it('Can change status for right a drone', () => {
        cy.get('[data-cy=garage_drone_id]').scrollIntoView().clear({force: true}).type('0', {force: true})
        // cy.get('[data-cy=manu_package_status]').scrollIntoView().clear({force: true}).select('WAITING', {force: true})
        // cy.get('#inputs-16').scrollIntoView().clear({force: true}).select('WAITING', {force: true})
        cy.get("#app > div > main > div > div > div:nth-child(2) > div > div > div > form > div > div:nth-child(2) > div > div > div.v-input__slot > div.v-select__slot > div.v-input__append-inner")
        .click()
        cy.get("#list-item-58-0").click()
        cy.get('[data-cy=garage_drone_status_button]').scrollIntoView().click()

        cy.wait(1000)

        // popup alert doit apparaitre
        // TODO : change data-cy
        // cy.get('[data-cy=failed_alert]').should('be.visible').click()
        // cy.get("#app > div.v-application--wrap > main > div > div > div.row.align-center.justify-space-around > div.col.col-4.align-self-center > div:nth-child(1) > div > div.v-alert.v-sheet.theme--light.v-alert--dense.v-alert--outlined.error--text > div > button > span > i")
        // .scrollIntoView().click()
      })
  
    })
  })
  