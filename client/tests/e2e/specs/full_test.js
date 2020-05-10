// https://docs.cypress.io/api/introduction/api.html

import {
    host
} from "../support";


describe('Manutentionnaire Part 1', () => {

    context('Marcel retreive the packages', () => {

        it('Should retreive all the packages', () => {
            cy.visit('http://' + host + ':8844/client')
            cy.get('[data-cy=client_title]').contains('SERVICE CLIENT')
            cy.get('[data-cy=client_retreive_package]').click({force: true})
            cy.wait(5000)
        })

        it('Should have 10 items in package list', () => {
            cy.get('[data-cy=refresh_package]').click({force: true})   
            cy.wait(2000)   
            cy.get("#app > div > main > div > div > div.row.align-start.justify-space-around > div:nth-child(2) > div > div > div:nth-child(2) > div > div.v-data-table.elevation-4.theme--light > div.v-data-table__wrapper > table > tbody")
              .as('packages')
            cy.get('@packages').find('tr').should('have.length', 10)
        })

        it('Should have registered packages in list', () => {
            cy.get('[data-cy=refresh_package]').click({force: true})      
            cy.wait(2000)
            cy.get("#app > div > main > div > div > div.row.align-start.justify-space-around > div:nth-child(2) > div > div > div:nth-child(2) > div > div.v-data-table.elevation-4.theme--light > div.v-data-table__wrapper > table > tbody")
              .as('packages')
            cy.get('@packages').find('tr').contains('REGISTERED')
        })
    })

    context('Marcel scan a package in order to let him avaible for a delivery creation', () => {

        it('Should be possible to change a package status', () => {
            cy.visit('http://' + host + ':8844/manu')
            cy.get('[data-cy=manu_title]').contains('MANUTENTIONNAIRE')

            cy.get('[data-cy=manu_package_id]').scrollIntoView().clear({force: true}).type('0', {force: true})
        
            cy.get("#app > div.v-application--wrap > main > div > div > div > div.col.col-3.align-self-center > div.v-card.v-sheet.theme--light > div > form > div:nth-child(1) > div.col.col-7 > div > div > div.v-input__slot > div.v-select__slot")
            .click({force: true})
            cy.get("#list-item-159-1").click({force: true})
            cy.get('[data-cy=manu_package_status_button]').scrollIntoView().click()
            cy.get('[data-cy=manu_package_success_alert]').should('be.visible').click({force: true})
        })

        it('Should switch first package to waiting', () => {
            cy.get('[data-cy=refresh_package]').click({force: true})   
            cy.wait(2000)
            cy.get("#app > div > main > div > div > div > div.col.col-3.align-self-center > div.row.align-center.justify-center > div > div > div > div:nth-child(2) > div > div.v-data-table.elevation-4.theme--light > div.v-data-table__wrapper > table > tbody > tr:nth-child(1)")
            .as('package')
            cy.get('@package').contains('WAITING')
        })      
    })
})

describe('Garage Part 1', () => {

    context('Garfield can create a drone', () => {
        
        it('Should be possible to create a drone', () => {
            cy.visit('http://' + host + ':8844/garage')
            cy.get('[data-cy=garage_title]').contains('GARAGE')
            cy.get('[data-cy=garage_id_drone]').scrollIntoView().clear({force: true}).type('0', {force: true})
            cy.get('[data-cy=garage_drone_validate_btn]').scrollIntoView().click({force: true})
            cy.get('[data-cy=add_drone_success_alert]').should('be.visible').click({force: true})
        })
      
        it('Should have update drone list', () => {
            cy.get('[data-cy=refresh_drone]').scrollIntoView().click({force: true})
            cy.wait(2000)
            cy.get("#app > div > main > div > div > div.row.align-strat.justify-space-around > div > div > div > div:nth-child(2) > div > div.v-data-table.elevation-4.theme--light > div.v-data-table__wrapper > table > tbody > tr > td.text-start")
            .as('drone')
            cy.get('@drone').contains('0')
        })
    })
})

describe('Client Service Part', () => {

    context('Clissandre can create a delivery', () => {
        
        it('Can create a delivery from right data', () => {
            cy.visit('http://' + host + ':8844/client')
            cy.get('[data-cy=client_title]').contains('SERVICE CLIENT')

            cy.get('[data-cy=id_field]').scrollIntoView().clear({force: true}).type('0', {force: true})
            cy.get('[data-cy=date_field]').scrollIntoView().clear({force: true}).type('2020-04-18', {force: true})
            cy.get('[data-cy=time_field]').scrollIntoView().clear({force: true}).type('12:00', {force: true})
            cy.get('[data-cy=validate_btn]').scrollIntoView().click({force: true})
      
            cy.wait(2000)
      
            cy.get("#app > div.v-application--wrap > main > div > div > div.row.align-center.justify-space-around > div.col.col-4.align-self-center > div:nth-child(1) > div > div.v-alert.v-sheet.theme--dark.v-alert--dense.v-alert--text.success--text")
            .should('be.visible')
      
            cy.get("#app > div.v-application--wrap > main > div > div > div.row.align-center.justify-space-around > div.col.col-4.align-self-center > div:nth-child(1) > div > div.v-alert.v-sheet.theme--dark.v-alert--dense.v-alert--text.success--text > div > button > span > i")
            .click({force: true})
        })

        it('Sould have the delivery created in list', () => {
            cy.get('[data-cy=refresh_delivery]').click({force: true})
        
            cy.wait(2000)
        
            cy.get("#app > div.v-application--wrap > main > div > div > div.row.align-start.justify-space-around > div:nth-child(1) > div > div > div:nth-child(2) > div > div.v-data-table.elevation-4.theme--light > div.v-data-table__wrapper > table > tbody")
                .as('deliveries')
            cy.get('@deliveries').contains('READY')
        })
    })
})

describe('Manutentionnaire Part 2', () => {

    context('Marcel can launch a delivery', () => {
        
        it('Can launch the delivery', () => {
            cy.visit('http://' + host + ':8844/manu')
            cy.get('[data-cy=manu_title]').contains('MANUTENTIONNAIRE')
            cy.get('[data-cy=refresh_delivery]').click({force: true})

            cy.wait(2000)

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
        })

        it('Should have the delivery updated in list', () => {
            cy.get('[data-cy=refresh_delivery]').click({force: true})
      
            cy.wait(2000)
      
            cy.get("#app > div > main > div > div > div > div.col.col-3.align-self-start > div.row.align-center.justify-center > div > div > div > div:nth-child(2) > div > div.v-data-table.elevation-4.theme--light > div.v-data-table__wrapper > table > tbody > tr")
              .as('deliverie')
            cy.get('@deliverie').contains('SENT')
          })

    })
})

describe('Garage Part 2', () => {

    context('Garfield can change a drone status', () => {

        it('Should have update drone list to delivering', () => {
            cy.visit('http://' + host + ':8844/garage')
            cy.get('[data-cy=garage_title]').contains('GARAGE')
            cy.get('[data-cy=refresh_drone]').scrollIntoView().click({
              force: true
            })
            cy.wait(2000)
            cy.get("#app > div.v-application--wrap > main > div > div > div.row.align-strat.justify-space-around > div > div > div > div:nth-child(2) > div > div.v-data-table.elevation-4.theme--light > div.v-data-table__wrapper > table > tbody > tr > td:nth-child(2) > span > span")
            .as('drone')
            cy.get('@drone').contains('DELIVERING')
          })
        
        it('Can change status for right a drone when delivery is over', () => {
            cy.get('[data-cy=garage_drone_id]').scrollIntoView().clear({force: true}).type('0', {force: true})
      
            cy.get("#app > div > main > div > div > div:nth-child(2) > div > div > div > form > div > div:nth-child(2) > div > div > div.v-input__slot > div.v-select__slot > div.v-input__append-inner")
              .click()
            cy.get("#list-item-74-2").click()
            cy.get('[data-cy=garage_drone_status_button]').scrollIntoView().click()
      
            cy.wait(1000)
      
            cy.get('[data-cy=garage_drone_success_alert]').should('be.visible').click({
              force: true
            })
          })
      
          it('Should have update drone list to charging', () => {
            cy.get('[data-cy=refresh_drone]').scrollIntoView().click({
              force: true
            })
            cy.wait(2000)
            cy.get("#app > div.v-application--wrap > main > div > div > div.row.align-strat.justify-space-around > div > div > div > div:nth-child(2) > div > div.v-data-table.elevation-4.theme--light > div.v-data-table__wrapper > table > tbody > tr > td:nth-child(2) > span > span")
            .as('drone')
            cy.get('@drone').contains('CHARGING')
          })
    })
})