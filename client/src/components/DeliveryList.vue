<template>
    <v-row>
        <v-col>
            <v-row align="center" justify="center">
        <span class="title grey--text text--darken-1 text-upper" dark>
          <span style="margin-right: 5px;">DELIVERY</span>
          <span class="font-weight-light">LIST</span>
        </span>
                <v-btn data-cy="refresh_delivery" color="grey darken-2" icon @click="this.getAllDeliveries">
                    <v-icon>mdi-cached</v-icon>
                </v-btn>
            </v-row>
            <v-row align="center" justify="center">
                <v-card>
                    <v-card-title>
                        <v-text-field v-model="search" append-icon="mdi-magnify" label="Search" single-line
                                      hide-details>
                        </v-text-field>
                    </v-card-title>
                    <v-data-table :headers="headers" :items="deliveries" :search="search" class="elevation-4">
                        <template v-slot:item.status="{ item }">
                            <v-chip :color="getColor(item.status)" dark>{{ item.status }}</v-chip>
                        </template>
                    </v-data-table>
                </v-card>
            </v-row>
        </v-col>
    </v-row>
</template>

<script>
    export default {
        name: 'DeliveryList',
        props: {
            msg: String,
            mode: String,
        },
        data() {
            return {
                xmlhttp: new XMLHttpRequest(),
                search: '',
                headersFull: [{
                    text: 'Delivery ID',
                    align: 'start',
                    sortable: true,
                    value: 'delivery_id',
                },
                    {
                        text: 'Package ID',
                        value: 'package_id',
                        align: 'center',
                    },
                    {
                        text: 'Status',
                        value: 'status',
                        align: 'center',
                    },
                    {
                        text: 'Delivery Date',
                        value: 'delivery_date',
                        align: 'center',
                    },
                    {
                        text: 'Delivery Time',
                        value: 'delivery_time',
                        align: 'center',
                    },
                    {
                        text: 'Drone ID',
                        value: 'drone_id',
                        align: 'center',
                    },
                ],
                headersLight: [{
                    text: 'Delivery ID',
                    align: 'start',
                    sortable: true,
                    value: 'delivery_id',
                },
                    {
                        text: 'Status',
                        value: 'status',
                        align: 'center',
                    },
                    {
                        text: 'Package ID',
                        value: 'package_id',
                        align: 'center',
                    },
                    {
                        text: 'Drone ID',
                        value: 'drone_id',
                        align: 'center',
                    },
                    {
                        text: 'Time',
                        value: 'delivery_time',
                        align: 'center',
                    },
                ],
                deliveries: [
                    /* {
                      id: '874512',
                      status: 'ASSIGNED',
                      address: '53 rue de la porte',
                    },
                    {
                      id: '654465',
                      status: 'ASSIGNED',
                      address: '18 avenue de chalaise',
                    },
                    {
                      id: '515845',
                      status: 'WAITING',
                      address: '74 boulerd garnier',
                    } */
                ],

            }
        },
        computed: {
            headers() {
                if (this.mode == 'full') {
                    return this.headersFull
                }
                if (this.mode == 'light') {
                    return this.headersLight
                }
                return this.headersFull
            },
        },
        methods: {
            getColor(status) {
                if (status == 'FAILED') return 'red'
                else if (status == 'READY') return 'orange'
                else if (status == 'SENT') return 'green'
                else return 'grey'
            },
            delay(ms) {
                return new Promise(resolve => setTimeout(resolve, ms));
            },
            getAllDeliveries() {
                (async () => {
                    await this.delay(500);
                    this.xmlhttp.open('POST', 'http://' + process.env.VUE_APP_BACKEND +
                        ':8080/scheduler/webservices/LogisticWS?wsdl', true);

                    // build SOAP request
                    var sr =
                        `<?xml version="1.0" encoding="utf-8"?>
          <Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
              <Body>
                  <getPlannedDeliveries xmlns="http://www.polytech.unice.fr/si/4a/isa/drone-delivery/logistic"/>
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
                                console.log(respXML)

                                let deliveries = respXML.getElementsByTagName('delivery')
                                console.log('deliveries')
                                console.log(deliveries)

                                context.deliveries = []
                                for (let deli of deliveries) {
                                    console.log('deli')
                                    console.log(deli)
                                    let respDeli = {
                                        delivery_id: deli.getElementsByTagName('idDelivery')[0].innerHTML,
                                        status: deli.getElementsByTagName('statusDelivery')[0].innerHTML,
                                        package_id: deli.getElementsByTagName('aPackage')[0].getElementsByTagName('idPackage')[0]
                                            .innerHTML,
                                        drone_id: deli.getElementsByTagName('drone')[0].getElementsByTagName('idDrone')[0]
                                            .innerHTML,
                                        delivery_date: new Date(parseInt(deli.getElementsByTagName('deliveryDate')[0].innerHTML) *
                                            1000).toLocaleDateString(),
                                        delivery_time: new Date(parseInt(deli.getElementsByTagName('deliveryDate')[0].innerHTML) *
                                            1000).toLocaleTimeString().slice(0, -3),
                                        //delivery_date: deli.getElementsByTagName('deliveryDate')[0].innerHTML,
                                    }
                                    context.deliveries.push(respDeli);
                                }
                            }
                        }
                    }
                    // Send the POST request
                    this.xmlhttp.setRequestHeader('Content-Type', 'text/xml');
                    this.xmlhttp.send(sr);
                })();
            }
        },
        mounted() {
            this.getAllDeliveries()
        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
