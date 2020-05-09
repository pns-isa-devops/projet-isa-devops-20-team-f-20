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
    it('Should have no items in drone list', () => {
      cy.get('[data-cy=refresh_drone]').click()

      cy.wait(1000)

      cy.get("#app > div > main > div > div > div.row.align-strat.justify-space-around > div > div > div > div:nth-child(2) > div > div.v-data-table.elevation-4.theme--light > div.v-data-table__wrapper > table > tbody > tr > td")
        .as('drones')
      cy.get('@drones').contains('No data available')
    })
  })
})

describe('Add drone tests', () => {

  context('Add drone', () => {

    it('Should be possible to create a drone', () => {
      cy.get('[data-cy=garage_id_drone]').scrollIntoView().clear({
        force: true
      }).type('0', {
        force: true
      })

      cy.get('[data-cy=garage_drone_validate_btn]').scrollIntoView().click({
        force: true
      })

      cy.wait(1000)

      cy.get('[data-cy=add_drone_success_alert]').should('be.visible').click({
        force: true
      })
    })

    it('Should have update drone list', () => {
      cy.get('[data-cy=refresh_drone]').scrollIntoView().click({
        force: true
      })
      cy.get("#app > div > main > div > div > div.row.align-strat.justify-space-around > div > div > div > div:nth-child(2) > div > div.v-data-table.elevation-4.theme--light > div.v-data-table__wrapper > table > tbody > tr > td.text-start")
      .as('drone')
      cy.get('@drone').contains('0')
    })
  })
})

describe('Change status tests', () => {

  context('Change drone status', () => {

    it('Cannot change status for a drone that does not exist', () => {

      cy.get('[data-cy=garage_drone_id]').scrollIntoView().clear({
        force: true
      }).type('-1', {
        force: true
      })

      cy.get("#app > div > main > div > div > div:nth-child(2) > div > div > div > form > div > div:nth-child(2) > div > div > div.v-input__slot > div.v-select__slot > div.v-input__append-inner")
        .click({
          force: true
        })

      cy.get("#list-item-74-1").click({
        force: true
      })
      cy.get('[data-cy=garage_drone_status_button]').scrollIntoView().click({
        force: true
      })

      cy.wait(1000)

      cy.get('[data-cy=garage_drone_failed_alert]').should('be.visible').click({
        force: true
      })
    })

    it('Can change status for right a drone', () => {
      cy.get('[data-cy=garage_drone_id]').scrollIntoView().clear({
        force: true
      }).type('0', {
        force: true
      })

      cy.get("#app > div > main > div > div > div:nth-child(2) > div > div > div > form > div > div:nth-child(2) > div > div > div.v-input__slot > div.v-select__slot > div.v-input__append-inner")
        .click()
      cy.get("#list-item-74-1").click()
      cy.get('[data-cy=garage_drone_status_button]').scrollIntoView().click()

      cy.wait(1000)

      cy.get('[data-cy=garage_drone_success_alert]').should('be.visible').click({
        force: true
      })
    })

    it('Should have update drone list', () => {
      cy.get('[data-cy=refresh_drone]').scrollIntoView().click({
        force: true
      })
      cy.get("#app > div.v-application--wrap > main > div > div > div.row.align-strat.justify-space-around > div > div > div > div:nth-child(2) > div > div.v-data-table.elevation-4.theme--light > div.v-data-table__wrapper > table > tbody > tr > td:nth-child(2) > span > span")
      .as('drone')
      cy.get('@drone').contains('DELIVERING')
    })

  })
})