<template>
    <v-row md12>
        <v-col>

            <v-alert data-cy="success_alert" dismissible dense text type="success" v-model="this.displaySuccess">
                Drone <strong>succesfully</strong> created !
            </v-alert>

            <v-alert data-cy="failed_alert" dismissible dense outlined type="error" v-model="this.displayFailed">
                Creation of the drone <strong>failed</strong> !
            </v-alert>

            <v-card>

                <v-card-text>
                    <span class="title text-upper" color="" dark>
                        <span style="margin-right: 5px;" class="font-weight-light">ADD</span>
                        <span>NEW DRONE</span>
                    </span>
                    <v-form ref="form" v-model="valid" lazy-validation>
                        <v-text-field data-cy="garage_id_drone" v-model="id" :counter="10" label="Drone Id"
                            prepend-icon="mdi-package-variant-closed" :rules="idRules" required></v-text-field>
                    </v-form>
                </v-card-text>


                <v-card-actions>
                    <v-row align="center" justify="space-around">
                        <v-col cols="4">
                            <v-btn data-cy="garage_drone_validate_btn" :disabled="!valid" color="yellow darken-2 white--text"
                                @click="validate">
                                Validate
                            </v-btn>
                        </v-col>
                        <v-col cols="4">
                            <v-btn color="purple lighten-2 white--text" @click="reset">
                                Reset Form
                            </v-btn>
                        </v-col>
                    </v-row>

                </v-card-actions>


            </v-card>
        </v-col>
    </v-row>

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
            addDrone() {
                this.xmlhttp.open('POST', 'http://' + process.env.VUE_APP_BACKEND +
                    ':8080/scheduler/webservices/TransportWS?wsdl', true);

                // build SOAP request
                var sr = `
                    <Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
                        <Body>
                            <addDrone xmlns="http://www.polytech.unice.fr/si/4a/isa/drone-delivery/transport">
                                <id xmlns="">`+ this.id +`</id>
                            </addDrone>
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

                            let created = respXML.getElementsByTagName('add_drone')[0].innerHTML
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
                if (valid) {
                    this.addDrone();
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