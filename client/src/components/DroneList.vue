<template>
    <v-row>
        <v-col>
            <v-row align="center" justify="center">
                <span class="title grey--text text--darken-1 text-upper" color="" dark>
                    <span style="margin-right: 5px;">DRONE</span>
                    <span class="font-weight-light">LIST</span>
                </span>
                <v-btn data-cy="refresh_drone" color="grey darken-2" icon @click="this.getAllDrones">
                    <v-icon>mdi-cached</v-icon>
                </v-btn>
            </v-row>
            <v-row align="center" justify="center">
                <v-card>
                    <v-card-title>
                        <v-text-field v-model="search" append-icon="mdi-magnify" label="Search" single-line
                            hide-details></v-text-field>
                    </v-card-title>
                    <v-data-table :headers="headers" :items="drones" :search="search" class="elevation-4">
                        <template v-slot:item.status="{ item }">
                            <v-chip :color="getStatusColor(item.status)" dark>{{ item.status }}</v-chip>
                        </template>
                        <template v-slot:item.chargeLevel="{ item }">
                            <v-progress-linear
                                :value="item.chargeLevel"
                                height="15"
                                striped
                                :color="getChargeColor(item.chargeLevel)"
                            >
                                <strong class="font-weight-light">{{ Math.ceil(item.chargeLevel) }}%</strong>
                            </v-progress-linear>
                        </template>
                    </v-data-table>
                </v-card>
            </v-row>
        </v-col>
    </v-row>
</template>

<script>
    export default {
        name: 'DroneList',
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
                        text: 'Charge level',
                        value: 'chargeLevel',
                        align: 'center',
                    },
                    {
                        text: 'Flying Time',
                        value: 'flyingTime',
                        align: 'center',
                    },
                ],
                drones: [{
                        id: '0',
                        status: 'AVAILABLE',
                        chargeLevel: '100',
                        flyingTime: '0h',
                    },
                    {
                        id: '1',
                        status: 'DELIVERING',
                        chargeLevel: '75',
                        flyingTime: '2h',
                    },
                    {
                        id: '2',
                        status: 'CHARGING',
                        chargeLevel: '50',
                        flyingTime: '10h',
                    },
                    {
                        id: '3',
                        status: 'REVIEW',
                        chargeLevel: '25',
                        flyingTime: '20h',
                    }
                ],

            }
        },
        methods: {
            getStatusColor(status) {
                if (status == 'REVIEW') return 'red'
                else if (status == 'CHARGING') return 'orange'
                else if (status == 'AVAILABLE') return 'green'
                else return 'grey'
            },
            getChargeColor(chargeLevel) {
                if (chargeLevel <= 10 ) return 'red'
                if (chargeLevel <= 30 ) return 'deep-orange'
                else if (chargeLevel  <= 50 ) return 'orange'
                else if (chargeLevel  <= 70 ) return 'yellow'
                else if (chargeLevel  <= 90 ) return 'light-green'
                else if (chargeLevel <= 100 ) return 'green'
                else return 'grey'
            },
            getAllDrones() {
                console.log(process.env)


                this.xmlhttp.open('POST', 'http://' + process.env.VUE_APP_BACKEND +
                    ':8080/scheduler/webservices/TransportWS?wsdl', true);

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
            this.getAllDrones()
        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>