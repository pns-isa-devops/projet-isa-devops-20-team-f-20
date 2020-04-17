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

    // it('Should have 3 items in package list', () => {
    //   cy.server()
    //   cy.route({
    //     method: 'POST',
    //     url: 'http://localhost:8080/delivery/webservices/DeliveryWS?wsdl',
    //   }).as('apiCheck')
      
    //   cy.wait('@apiCheck').then((xhr) => {
    //     cy.get('[data-cy=refresh_package]').click()
    //     assert.isNotNull(xhr.response.body.data, '1st API call has data')
    //     cy.get("#app > div > main > div > div > div.row.align-center.justify-space-around > div.col.col-4.align-self-center > div:nth-child(2) > div > div:nth-child(2) > div > div.v-data-table__wrapper > table > tbody")
    //       .as('packages')
    //     //cy.wait('@packages')
    //     cy.get('@packages').find('tr').should('have.length', 3)
    //   })
    // })

    it('Should have 3 items in package list', () => {
      //cy.get('[data-cy=refresh_package]').click()
      cy.get("#app > div > main > div > div > div.row.align-center.justify-space-around > div.col.col-4.align-self-center > div:nth-child(2) > div > div:nth-child(2) > div > div.v-data-table__wrapper > table > tbody")
        .as('packages')
      cy.get('@packages').find('tr').should('have.length', 3)
    })

    it('Should hav no items in delivery list', () => {
      cy.get('[data-cy=refresh_delivery]').click()
      cy.get("#app > div > main > div > div > div.row.align-center.justify-center > div > div > div > div:nth-child(2) > div > div.v-data-table__wrapper > table > tbody > tr > td")
        .as('deliveries')
      cy.wait('@deliveries')
      cy.get('@deliveries').contains('No data available')
    })
  })
})