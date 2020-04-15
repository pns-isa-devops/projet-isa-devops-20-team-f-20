<template>
    <v-row md12>
        <v-col>

        <v-snackbar v-if="created" v-model="snackbar" :timeout="timeout" style="green">
            Delivery succesfully created...!
            <v-btn color="blue" text @click="snackbar = false">
                Close
            </v-btn>
        </v-snackbar>

        <v-snackbar v-else v-model="snackbar" :timeout="timeout" style="red">
            Delivery not created...!
            <v-btn color="blue" text @click="snackbar = false">
                Close
            </v-btn>
        </v-snackbar>
        <v-card>

            <v-card-text>
                <p class="headline text--primary">Add New Delivery</p>
                <v-form ref="form" v-model="valid" lazy-validation>
                    <v-text-field v-model="id" :counter="10" label="Package Id"
                        prepend-icon="mdi-package-variant-closed" :rules="idRules" required></v-text-field>

                    <v-menu v-model="menuDate" :close-on-content-click="false" :nudge-right="40"
                        transition="scale-transition" offset-y min-width="290px">
                        <template v-slot:activator="{ on }">
                            <v-text-field v-model="date" label="Picker without buttons" prepend-icon="event" readonly
                                v-on="on">
                            </v-text-field>
                        </template>
                        <v-date-picker v-model="date" @input="menuDate = false"></v-date-picker>
                    </v-menu>

                    <v-menu ref="menu" v-model="menuTime" :close-on-content-click="false" :nudge-right="40"
                        :return-value.sync="time" transition="scale-transition" offset-y max-width="290px"
                        min-width="290px">
                        <template v-slot:activator="{ on }">
                            <v-text-field v-model="time" label="Picker in menu" color="primary"
                                prepend-icon="access_time" readonly v-on="on">
                            </v-text-field>
                        </template>
                        <v-time-picker v-if="menuTime" v-model="time" format="24hr" full-width
                            @click:minute="$refs.menu.save(time)">
                        </v-time-picker>
                    </v-menu>
                </v-form>
            </v-card-text>


            <v-card-actions>
                <v-btn :disabled="!valid" color="success" @click="validate">
                    Validate
                </v-btn>
                <v-btn color="error" @click="reset">
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
                snackbar: false,
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
        methods: {
            addDelivery() {
                this.xmlhttp.open('POST', 'http://localhost:8080/delivery/webservices/DeliveryWS?wsdl', true);

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
                                context.snackbar = true;
                                context.created = true;
                                context.reset()
                            } else {
                                context.snackbar = true;
                                context.created = false;
                            }

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