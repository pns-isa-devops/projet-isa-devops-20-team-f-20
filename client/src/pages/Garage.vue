<template>
  <v-container fluid>

    <v-app-bar app color="yellow darken-2" dark>
      <div class="d-flex align-center" @click="goToHome()">
        <v-img alt="Livrair'" class="shrink mr-2" contain src="../assets/drone_fix.png" transition="scale-transition"
          width="40" />
      </div>
      <v-toolbar-title class="headline text-upper" @click="goToHome()">
        <span class="font-weight-light">LIVRAIR - </span>
        <span data-cy="garage_title">GARAGE</span>
      </v-toolbar-title>

      <v-spacer></v-spacer>

      <v-btn href="https://github.com/pns-isa-devops/projet-isa-devops-20-team-f-20" target="_blank" text>
        <span class="mr-2">Git Repo</span>
        <v-icon>mdi-open-in-new</v-icon>
      </v-btn>
    </v-app-bar>

    <v-row md12 align="center" justify="space-around">
      <v-col cols="8" align-self="center">
        <v-alert data-cy="garage_delivery_success_alert" dismissible dense text type="success"
          v-model="this.displaySuccess">
          Drone status <strong>succesfully</strong> changed !
        </v-alert>

        <v-alert data-cy="garage_delivery_failed_alert" dismissible dense outlined type="error"
          v-model="this.displayFailed">
          Drone status modification <strong>failed</strong> !
        </v-alert>
        <v-card>
          <v-card-text>
            <span class="title text-upper" color="" dark>
              <span style="margin-right: 5px;" class="font-weight-light">CHANGE</span>
              <span>DRONE STATUS</span>
            </span>
            <v-form ref="form" v-model="validPackage" lazy-validation>
              <v-row align="center" justify="space-around" wrap>
                <v-col cols="2">
                  <v-text-field data-cy="garage_drone_id" name="droneID" v-model="droneId" label="Drone ID" id="idDrone"
                    :rules="idRules" required></v-text-field>
                </v-col>
                <v-col cols="2">
                  <v-select data-cy="garage_drone_status" :items="items" v-model="droneStatus" label="Drone Status"
                    :rules="statusRules">
                  </v-select>
                </v-col>
                <v-col cols="2">
                  <v-btn tile data-cy="garage_drone_status_button" :disabled="!validDrone" color="yellow darken-1"
                    @click="validate">
                    Change drone status
                  </v-btn>
                </v-col>
              </v-row>
              <!-- <v-row align="center" justify="space-around">
                              <v-btn tile data-cy="garage_drone_status_button" :disabled="!validDrone" color="success" @click="validate">
                                Change drone status
                              </v-btn>
                            </v-row> -->
            </v-form>
            <!-- <v-divider style="margin-top: 25px"></v-divider>
                        <v-row align="center" justify="center">
                          <v-col cols="8" align-self="center">
                           <PackageList ref="packageList" mode="manu" />
                          </v-col>
                        </v-row> -->
          </v-card-text>
        </v-card>
      </v-col>
      <v-col>
        <AddDrone ref="droneList" />
      </v-col>
    </v-row>
    <v-row md12 align="strat" justify="space-around">
      <v-col cols="12" align-self="center">
        <DroneList ref="droneList" />
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
  import DroneList from "../components/DroneList"
  import AddDrone from "../components/AddDrone"

  export default {
    components: {
      DroneList,
      AddDrone,
    },
    name: 'Garage',
    props: {
      msg: String
    },
    data() {
      return {
        xmlhttp: new XMLHttpRequest(),
        items: ['AVAILABLE', 'DELIVERING', 'CHARGING', 'REVIEW'],
        validDrone: true,
        droneId: '',
        idRules: [
          v => (v && !isNaN(v)) || 'ID must be a number',
        ],
        droneStatus: '',
        statusRules: [
          v => (v && (v == 'AVAILABLE' || v == 'DELIVERING' || v == 'CHARGING' || v == 'REVIEW')) ||
          'Status not correct',
        ],
        displaySuccess: false,
        displayFailed: false,
        displayed: false,
        changed: false,
      }
    },
    methods: {
      changeDroneStatus() {
        this.xmlhttp.open('POST', 'http://' + process.env.VUE_APP_BACKEND +
          ':8080/scheduler/webservices/TransportWS?wsdl', true);

        // build SOAP request
        var sr = `
                    <Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
                        <Body>
                            <changeState xmlns="http://www.polytech.unice.fr/si/4a/isa/drone-delivery/transport">
                                <id xmlns="">` + this.droneId + `</id>
                                <droneStatus xmlns="">` + this.droneStatus + `</droneStatus>
                            </changeState>
                        </Body>
                    </Envelope>
                    `

        console.log(sr)

        let context = this

        this.xmlhttp.onreadystatechange = function () {
          if (context.xmlhttp.readyState == 4) {
            if (context.xmlhttp.status == 200) {

              let respXML = context.xmlhttp.responseXML;

              let droneChanged = respXML.getElementsByTagName('changed')[0].innerHTML
              console.log(droneChanged)

              if (droneChanged == 'true') {
                context.reset()
                context.changed = true;
                context.displaySuccess = true;
                context.displayFailed = false;
              } else {
                context.changed = false;
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
        let valid = this.$refs.form.validate()
        this.displayFailed = false;
        this.displaySuccess = false;
        if (valid) {
          this.changeDroneStatus()
        }
        console.log(valid)
      },
      reset() {
        this.$refs.form.reset()
      },
      gotoHome() {
        this.$router.push('/home')
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>