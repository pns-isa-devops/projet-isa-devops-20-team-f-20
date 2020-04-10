// https://docs.cypress.io/api/introduction/api.html

describe('Init Test', () => {
  it('Visits the app root url', () => {
    cy.visit('http://localhost:8844/')
  })
  it('Go to Client Page', () => {
    //cy.visit('/client')
    cy.get('#client').click()
    cy.get('#title').contains('SERVICE CLIENT')
  })
})
