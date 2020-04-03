// https://docs.cypress.io/api/introduction/api.html

describe('My First Test', () => {
  it('Visits the app root url', () => {
    cy.visit('/')
  })
  it('Go to Client Page', () => {
    //cy.visit('/client')
    cy.get('#client').click()
    cy.get('#title').contains('SERVICE CLIENT')
  })
})
