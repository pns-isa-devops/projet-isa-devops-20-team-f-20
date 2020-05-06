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
  
    context('Load the data in the page by clicking on each refresh buttons', () => {
  
      it('Should have 3 items in package list', () => {
        cy.get('[data-cy=refresh_package]').click()
  
        cy.wait(5000)
        
        cy.get("#app > div.v-application--wrap > main > div > div > div > div.col.col-3.align-self-center > div > div > div > div > div > div > div:nth-child(2) > div > div.v-data-table__wrapper > table > tbody")
          .as('packages')
        cy.get('@packages').find('tr').should('have.length', 3)
      })
    })
  })
  
  describe('Change status tests', () => {
  
    context('Change package status', () => {
  
      it('Cannot change status for a package that does not exist', () => {

        cy.get('[data-cy=manu_package_id]').scrollIntoView().clear({force: true}).type('-1', {force: true})
        // cy.get('[data-cy=manu_package_status]').scrollIntoView().clear({force: true}).select('WAITING', {force: true})
        // cy.get('#inputs-16').scrollIntoView().clear({force: true}).select('WAITING', {force: true})
        cy.get("#app > div.v-application--wrap > main > div > div > div > div.col.col-3.align-self-center > div > div > form > div:nth-child(1) > div.col.col-7 > div > div > div.v-input__slot > div.v-select__slot > div.v-input__append-inner")
        .click()
        cy.get("#list-item-131-0").click()
        cy.get('[data-cy=manu_package_status_button]').scrollIntoView().click()

        cy.wait(1000)

        // popup alert doit apparaitre
        // TODO : change data-cy
        // cy.get('[data-cy=failed_alert]').should('be.visible').click()
        // cy.get("#app > div.v-application--wrap > main > div > div > div.row.align-center.justify-space-around > div.col.col-4.align-self-center > div:nth-child(1) > div > div.v-alert.v-sheet.theme--light.v-alert--dense.v-alert--outlined.error--text > div > button > span > i")
        // .scrollIntoView().click()
      })
  
      it('Can change status for right a package', () => {
        cy.get('[data-cy=manu_package_id]').scrollIntoView().clear({force: true}).type('0', {force: true})
        // cy.get('[data-cy=manu_package_status]').scrollIntoView().clear({force: true}).select('WAITING', {force: true})
        // cy.get('#inputs-16').scrollIntoView().clear({force: true}).select('WAITING', {force: true})
        cy.get("#app > div.v-application--wrap > main > div > div > div > div.col.col-3.align-self-center > div > div > form > div:nth-child(1) > div.col.col-7 > div > div > div.v-input__slot > div.v-select__slot > div.v-input__append-inner")
        .click()
        cy.get("#list-item-131-0").click()
        cy.get('[data-cy=manu_package_status_button]').scrollIntoView().click()

        cy.wait(1000)

        // popup alert doit apparaitre
        // TODO : change data-cy
        // cy.get("#app > div.v-application--wrap > main > div > div > div.row.align-center.justify-space-around > div.col.col-4.align-self-center > div:nth-child(1) > div > div.v-alert.v-sheet.theme--dark.v-alert--dense.v-alert--text.success--text")
        // .should('be.visible')
        // cy.get("#app > div.v-application--wrap > main > div > div > div.row.align-center.justify-space-around > div.col.col-4.align-self-center > div:nth-child(1) > div > div.v-alert.v-sheet.theme--dark.v-alert--dense.v-alert--text.success--text > div > button > span > i")
        // .click()
      })
  
    })
  
    context('Launch a delivery', () => {
  
      it('Cannot launch a delivery with a non exiting drone', () => {
        cy.get('[data-cy=manu_launch_delivery_drone_id]').scrollIntoView().clear({force: true}).type('-1', {force: true})
        cy.get('[data-cy=manu_launch_delivery_button]').scrollIntoView().click()

        cy.wait(1000)

        // popup alert doit apparaitre
        // TODO : change data-cy
        // cy.get('[data-cy=failed_alert]').should('be.visible').click()
        // cy.get("#app > div.v-application--wrap > main > div > div > div.row.align-center.justify-space-around > div.col.col-4.align-self-center > div:nth-child(1) > div > div.v-alert.v-sheet.theme--light.v-alert--dense.v-alert--outlined.error--text > div > button > span > i")
        // .scrollIntoView().click()
      })
  
      it('Cannot launch a delivery with a drone non assigned to a delivery', () => {
        cy.get('[data-cy=manu_launch_delivery_drone_id]').scrollIntoView().clear({force: true}).type('1', {force: true})
        cy.get('[data-cy=manu_launch_delivery_button]').scrollIntoView().click()

        cy.wait(1000)

        // popup alert doit apparaitre
        // TODO : change data-cy
        // cy.get('[data-cy=failed_alert]').should('be.visible').click()
        // cy.get("#app > div.v-application--wrap > main > div > div > div.row.align-center.justify-space-around > div.col.col-4.align-self-center > div:nth-child(1) > div > div.v-alert.v-sheet.theme--light.v-alert--dense.v-alert--outlined.error--text > div > button > span > i")
        // .scrollIntoView().click()
      })
  
      it('Can launch a delivery with right drone', () => {
        cy.get('[data-cy=manu_launch_delivery_drone_id]').scrollIntoView().clear({force: true}).type('0', {force: true})
        cy.get('[data-cy=manu_launch_delivery_button]').scrollIntoView().click()

        cy.wait(1000)

        // popup alert doit apparaitre
        // TODO : change data-cy
        // cy.get('[data-cy=failed_alert]').should('be.visible').click()
        // cy.get("#app > div.v-application--wrap > main > div > div > div.row.align-center.justify-space-around > div.col.col-4.align-self-center > div:nth-child(1) > div > div.v-alert.v-sheet.theme--light.v-alert--dense.v-alert--outlined.error--text > div > button > span > i")
        // .scrollIntoView().click()
      })
    })  
  })
  