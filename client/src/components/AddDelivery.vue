<template>
    <v-row md12>
        <v-col>

            <!-- <v-snackbar data-cy="success_snackbar" v-show="created" v-model="snackbar" :timeout="timeout" style="green">
            Delivery succesfully created...!
            <v-btn color="blue" text @click="snackbar = false">
                Close
            </v-btn>
        </v-snackbar>

        <v-snackbar data-cy="failed_snackbar" v-show="!created" v-model="snackbar" :timeout="timeout" style="red">
            Delivery not created...!
            <v-btn color="blue" text @click="snackbar = false">
                Close
            </v-btn>
        </v-snackbar> -->

            <v-alert data-cy="success_alert" dense dismissible text type="success" v-model="this.displaySuccess">
                Delivery <strong>succesfully</strong> created ! {{ this.displaySuccess }}
            </v-alert>

            <v-alert data-cy="failed_alert" dense dismissible outlined type="error" v-model="this.displayFailed">
                Creation of the delivery <strong>failed</strong> ! {{ this.displayFailed }}
            </v-alert>

            <v-card>

                <v-card-text>
                    <p class="headline text--primary">Add New Delivery</p>
                    <v-form lazy-validation ref="form" v-model="valid">
                        <v-text-field :counter="10" :rules="idRules" data-cy="id_field" label="Package Id"
                                      prepend-icon="mdi-package-variant-closed" required
                                      v-model="id"></v-text-field>

                        <v-menu :close-on-content-click="false" :nudge-right="40" min-width="290px"
                                offset-y transition="scale-transition" v-model="menuDate">
                            <template v-slot:activator="{ on }">
                                <v-text-field data-cy="date_field" label="Pick a Date" prepend-icon="event"
                                              readonly v-model="date" v-on="on">
                                </v-text-field>
                            </template>
                            <v-date-picker @input="menuDate = false" v-model="date"></v-date-picker>
                        </v-menu>

                        <v-menu :close-on-content-click="false" :nudge-right="40" :return-value.sync="time" max-width="290px"
                                min-width="290px" offset-y ref="menu" transition="scale-transition"
                                v-model="menuTime">
                            <template v-slot:activator="{ on }">
                                <v-text-field color="primary" data-cy="time_field" label="Select a Time" prepend-icon="access_time"
                                              readonly v-model="time" v-on="on">
                                </v-text-field>
                            </template>
                            <v-time-picker @click:minute="$refs.menu.save(time)" format="24hr" full-width v-if="menuTime"
                                           v-model="time">
                            </v-time-picker>
                        </v-menu>
                    </v-form>
                </v-card-text>


                <v-card-actions>
                    <v-btn :disabled="!valid" @click="validate" color="success" data-cy="validate_btn">
                        Validate
                    </v-btn>
                    <v-btn @click="reset" color="warning">
                        Reset Form
                    </v-btn>

                </v-card-actions>


            </v-card>
        </v-col>
    </v-row>
    <!-- <div class="text-center">
        <v-btn dark color="orange darken-2" @click="snackbar = true">
            Open Snackbar
        </v-btn>
    </div>
 -->

</template>

<script>
    export default {
        name: 'AddDelivery',
        data() {
            return {
                xmlhttp: new XMLHttpRequest(),
                displaySuccess: false,
                displayFailed: false,
                displayed: false,
                created: false,
                timeout: 2000,
                valid: true,
                id: '',
                idRules: [
                    v => !!v || 'Name is required',
                    v => (v && !isNaN(v)) || 'ID must be a number',
                ],
                date: new Date().toISOString().substr(0, 10),
                time: null,
                menuDate: false,
                menuTime: false
            }
        },
        computed: {
            displayedAlertSuccess() {
                return (this.displayed && this.created)
            },
            displayedAlertFailed() {
                return (this.displayed && !this.created)
            },
        },
        methods: {
            addDelivery() {
                this.xmlhttp.open('POST', 'http://' + process.env.VUE_APP_BACKEND +
                    ':8080/logistic/webservices/LogisticWS?wsdl', true);

                // build SOAP request
                var sr = `
                    <Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
                        <Body>
                            <createDelivery xmlns="http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery">
                                <id xmlns="">` + this.id + `</id>
                                <jour xmlns="">` + this.date.split('-', 3)[2] + `</jour>
                                <mois xmlns="">` + this.date.split('-', 3)[1] + `</mois>
                                <annee xmlns="">` + this.date.split('-', 3)[0] + `</annee>
                                <heure xmlns="">` + this.time.split(':', 2)[0] + `</heure>
                                <minute xmlns="">` + this.time.split(':', 2)[1] + `</minute>
                            </createDelivery>
                        </Body>
                    </Envelope>
                    `

                console.log(sr)

                let context = this

                this.xmlhttp.onreadystatechange = function () {
                    if (context.xmlhttp.readyState == 4) {
                        if (context.xmlhttp.status == 200) {

                            let respXML = context.xmlhttp.responseXML;
                            // console.log(respXML)

                            // let packages = respXML.getElementsByTagName('package')
                            // console.log(packages)

                            let created = respXML.getElementsByTagName('is_planned')[0].innerHTML
                            console.log(created)

                            if (created == 'true') {
                                context.reset()
                                context.created = true;
                                context.displaySuccess = true;
                                context.displayFailed = false;
                            } else {
                                context.created = false;
                                context.displaySuccess = false;
                                context.displayFailed = true;
                            }
                            context.displayed = true;

                            // for (let pack of packages) {
                            //     console.log(pack)
                            //     let respPackage = {
                            //         id: pack.getElementsByTagName('id')[0].innerHTML,
                            //         status: pack.getElementsByTagName('packageStatus')[0].innerHTML,
                            //         address: pack.getElementsByTagName('address')[0].innerHTML,
                            //     }
                            //     context.desserts.push(respPackage);
                            // }
                        }
                    }
                }
                // Send the POST request
                this.xmlhttp.setRequestHeader('Content-Type', 'text/xml');
                this.xmlhttp.send(sr);
            },
            validate() {
                this.displayFailed = false;
                this.displaySuccess = false;
                let valid = this.$refs.form.validate()
                console.log(valid)
                console.log(this.id)
                console.log(this.date)
                console.log(this.time)
                console.log('formatage')
                console.log(this.date.split('-', 3)[2])
                console.log(this.date.split('-', 3)[1])
                console.log(this.date.split('-', 3)[0])
                console.log(this.time.split(':', 2)[0])
                console.log(this.time.split(':', 2)[1])
                if (valid) {
                    this.addDelivery();
                }
            },
            reset() {
                this.$refs.form.reset()
            },
        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
