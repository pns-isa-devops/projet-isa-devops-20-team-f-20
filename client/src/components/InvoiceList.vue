<template>
    <v-row>
        <v-col>
            <v-row align="center" justify="center">
                <span class="title grey--text text--darken-1 text-upper" color="" dark>
                    <span style="margin-right: 5px;">INVOICE</span>
                    <span class="font-weight-light">LIST</span>
                </span>
                <v-btn data-cy="refresh_invoice" color="grey darken-2" icon @click="this.getAllInvoices">
                    <v-icon>mdi-cached</v-icon>
                </v-btn>
            </v-row>
            <v-row align="center" justify="center">
                <v-card>
                    <v-card-title>
                        <v-text-field v-model="search" append-icon="mdi-magnify" label="Search" single-line
                            hide-details></v-text-field>
                    </v-card-title>
                    <v-data-table :headers="headers" :items="invoices" :search="search" class="elevation-4">
                        <template v-slot:item.status="{ item }">
                            <v-chip :color="getStatusColor(item.status)" dark>{{ item.status }}</v-chip>
                        </template>
                    </v-data-table>
                </v-card>
            </v-row>
        </v-col>
    </v-row>
</template>

<script>
    export default {
        name: 'InvoiceList',
        props: {
            mode: String
        },
        data() {
            return {
                xmlhttp: new XMLHttpRequest(),
                search: '',
                headers: [{
                        text: 'ID',
                        align: 'start',
                        sortable: true,
                        value: 'id',
                    },
                    {
                        text: 'Status',
                        align: 'center',
                        value: 'status'
                    },
                    {
                        text: 'Supplier',
                        value: 'supplier',
                        align: 'center',
                    },
                    {
                        text: 'Price',
                        value: 'price',
                        align: 'center',
                    },
                    {
                        text: 'Date',
                        value: 'date',
                        align: 'center',
                    },
                ],
                invoices: [{
                        id: '0',
                        status: 'DRAFT',
                        supplier: 'UPS',
                        price: '1200€',
                        date: 'date',
                    },
                    {
                        id: '1',
                        status: 'SENT',
                        supplier: 'UPS',
                        price: '87956€',
                        date: 'date',
                    },
                    {
                        id: '2',
                        status: 'PAID',
                        supplier: 'UPS',
                        price: '9364€',
                        date: 'date',
                    },
                    {
                        id: '3',
                        status: 'PAID',
                        supplier: 'UPS',
                        price: '12872€',
                        date: 'date',
                    }
                ],

            }
        },
        methods: {
            getStatusColor(status) {
                if (status == 'DRAFT') return 'red'
                else if (status == 'SENT') return 'orange'
                else if (status == 'PAID') return 'green'
                else return 'grey'
            },
            getAllInvoices() {
                console.log(process.env)


                this.xmlhttp.open('POST', 'http://' + process.env.VUE_APP_BACKEND +
                    ':8080/scheduler/webservices/InvoiceWS?wsdl', true);

                // build SOAP request
                var sr =
                    `<?xml version="1.0" encoding="utf-8"?>
          <Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
              <Body>
                  <getAllDrones xmlns="http://www.delivrair.fr/backend/transport"/>
              </Body>
          </Envelope>`

                let context = this

                this.xmlhttp.onreadystatechange = function () {
                    if (context.xmlhttp.readyState == 4) {
                        if (context.xmlhttp.status == 200) {

                            let respXML = context.xmlhttp.responseXML;
                            // let respJSON = require('xml-js').xml2json(context.xmlhttp.response, {
                            //   compact: true,
                            //   spaces: 4
                            // });
                            //console.log(respXML)

                            let packages = respXML.getElementsByTagName('drone')
                            //console.log(packages)

                            context.packages = []
                            for (let pack of packages) {
                                //console.log(pack)

                                let respDrone = {
                                    id: pack.getElementsByTagName('idDrone')[0].innerHTML,
                                    status: pack.getElementsByTagName('statusDrone')[0].innerHTML,
                                    chargeLevel: pack.getElementsByTagName('chargeLevel')[0].innerHTML,
                                    flyingTime: pack.getElementsByTagName('flyingTime')[0].innerHTML,
                                }

                                context.drones.push(respDrone);
                            }
                        }
                    }
                }
                // Send the POST request
                this.xmlhttp.setRequestHeader('Content-Type', 'text/xml');
                this.xmlhttp.send(sr);
            }
        },
        mounted() {
            this.getAllInvoices()
        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>