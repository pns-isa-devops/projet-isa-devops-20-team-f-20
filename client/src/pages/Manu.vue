<template>
  <v-container fluid>

    <v-app-bar app color="blue lighten-2" dark>
      <div class="d-flex align-center" @click="goToHome()">
        <v-img alt="Livrair'" class="shrink mr-2" contain src="../assets/drone_package.png"
          transition="scale-transition" width="40" />
      </div>
      <v-toolbar-title class="headline text-upper" @click="goToHome()">
        <span class="font-weight-light">LIVRAIR - </span>
        <span data-cy="manu_title">MANUTENTIONNAIRE</span>
      </v-toolbar-title>

      <v-spacer></v-spacer>

      <v-btn href="https://github.com/pns-isa-devops/projet-isa-devops-20-team-f-20" target="_blank" text>
        <span class="mr-2">Git Repo</span>
        <v-icon>mdi-open-in-new</v-icon>
      </v-btn>
    </v-app-bar>

    <v-row align="strat" justify="space-around">
      <v-col cols="3" align-self="center">
        <v-alert data-cy="manu_package_success_alert" dismissible dense text type="success" v-model="this.displaySuccessPackage">
                Package status <strong>succesfully</strong> changed !
            </v-alert>

            <v-alert data-cy="manu_package_failed_alert" dismissible dense outlined type="error" v-model="this.displayFailedPackage">
                Package status modification <strong>failed</strong> !
            </v-alert>
        <v-card>
          <v-card-text>
            <span class="title text-upper" color="" dark>
              <span style="margin-right: 5px;" class="font-weight-light">CHANGE</span>
              <span>PACKAGE STATUS</span>
            </span>
            <v-form ref="formPackage" v-model="validPackage" lazy-validation>
              <v-row align="center" justify="space-around">
                <v-col cols="4">
                  <v-text-field data-cy="manu_package_id" name="packageID" v-model="packageId" label="Package ID"
                    id="idPackage" :rules="idRules" required></v-text-field>
                </v-col>
                <v-col cols="7">
                  <v-select data-cy="manu_package_status" :items="items" v-model="packageStatus" label="Package Status"
                    :rules="statusRules">
                  </v-select>
                </v-col>
              </v-row>
              <v-row align="center" justify="space-around">
                <v-btn tile data-cy="manu_package_status_button" :disabled="!validPackage" color="blue white--text"
                  @click="validatePackage">
                  Change package status
                </v-btn>
              </v-row>
            </v-form>
            <!-- <v-divider style="margin-top: 25px"></v-divider> -->
            <!-- <v-row align="center" justify="center">
              <v-col cols="11" align-self="center">
                <PackageList ref="packageList" mode="manu" />
              </v-col>
            </v-row> -->
          </v-card-text>
        </v-card>
        <v-row align="center" justify="center">
          <v-col cols="11" align-self="center">
            <PackageList ref="packageList" mode="manu" />
          </v-col>
        </v-row>
      </v-col>
      <v-col cols="5">
        <CalendarDelivery mode='day' />
      </v-col>
      <v-col cols="3" align-self="start">
        <v-alert data-cy="manu_delivery_success_alert" dismissible dense text type="success" v-model="this.displaySuccessDelivery">
                Delivery <strong>succesfully</strong> launched !
            </v-alert>

            <v-alert data-cy="manu_delivery_failed_alert" dismissible dense outlined type="error" v-model="this.displayFailedDelivery">
                Delivery launching <strong>failed</strong> !
            </v-alert>
        <v-card>
          <v-card-text>
            <span class="title text-upper" color="" dark>
              <span style="margin-right: 5px;" class="font-weight-light">LAUNCH</span>
              <span>DELIVERY</span>
            </span>
            <v-form ref="formDelivery" v-model="validDelivery" lazy-validation>
              <v-row align="center" justify="center">
                <v-col cols="4">
                  <v-text-field data-cy="manu_launch_delivery_delivery_id" name="DeliveryID" v-model="deliveryId"
                    label="Delivery ID" id="idDelivery" :rules="idRules" required></v-text-field>
                </v-col>
              </v-row>
              <v-row align="center" justify="space-around">
                <v-btn tile data-cy="manu_launch_delivery_button" :disabled="!validDelivery" color="blue white--text"
                  @click="validateDelivery">
                  Launch Drone and Delivery
                </v-btn>
              </v-row>
            </v-form>
            <!-- <v-divider style="margin-top: 25px"></v-divider> -->
            <!-- <v-row align="center" justify="center">
              <v-col cols="8" align-self="center">
                <DeliveryList ref="packageList" mode="light" />
              </v-col>
            </v-row> -->
          </v-card-text>
        </v-card>
        <v-row align="center" justify="center">
          <v-col cols="11" align-self="center">
            <DeliveryList ref="deliveryList" mode="light" />
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
  import PackageList from "../components/PackageList"
  import DeliveryList from "../components/DeliveryList"
  import CalendarDelivery from "../components/CalendarDelivery"
  export default {
    components: {
      PackageList,
      DeliveryList,
      CalendarDelivery,
    },
    name: 'Manu',
    props: {
      msg: String
    },
    data() {
      return {
        items: ['REGISTERED', 'WAITING', 'ASSIGNED'],
        validPackage: true,
        validDelivery: true,
        packageId: '',
        deliveryId: '',
        idRules: [
          v => (v && !isNaN(v)) || 'ID must be a number',
        ],
        packageStatus: '',
        statusRules: [
          v => (v && (v == 'REGISTERED' || v == 'WAITING' || v == 'ASSIGNED')) || 'Status not correct',
        ],
        displaySuccessPackage: false,
        displayFailedPackage: false,
        displayedPackage: false,
        packageChanged: false,
        displaySuccessDelivery: false,
        displayFailedDelivery: false,
        displayedDelivery: false,
        createdDelivery: false,
      }
    },
    methods: {
      changePackageStatus() {
        this.xmlhttp.open('POST', 'http://' + process.env.VUE_APP_BACKEND +
          ':8080/scheduler/webservices/SchedulerWS?wsdl', true);

        // build SOAP request
        var sr = `
                    <Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
                        <Body>
                            <changePackageStatut xmlns="http://www.polytech.unice.fr/si/4a/isa/drone-delivery/logistic">
                                <id xmlns="">`+ this.packageId +`</id>
                                <packageStatus xmlns="">`+ this.packageStatus +`</packageStatus>
                            </changePackageStatut>
                        </Body>
                    </Envelope>
                    `

        console.log(sr)

        let context = this

        this.xmlhttp.onreadystatechange = function () {
          if (context.xmlhttp.readyState == 4) {
            if (context.xmlhttp.status == 200) {

              let respXML = context.xmlhttp.responseXML;

              let packageChanged = respXML.getElementsByTagName('changed')[0].innerHTML
              console.log(packageChanged)

              if (packageChanged == 'true') {
                context.resetPackage()
                context.packageChanged = true;
                context.displaySuccessPackage = true;
                context.displayFailedPackage = false;
              } else {
                context.packageChanged = false;
                context.displaySuccessPackage = false;
                context.displayFailedPackage = true;
              }
              context.displayedPackage = true;
            }
          }
        }
        // Send the POST request
        this.xmlhttp.setRequestHeader('Content-Type', 'text/xml');
        this.xmlhttp.send(sr);
      },
      launchDelivery() {
        this.xmlhttp.open('POST', 'http://' + process.env.VUE_APP_BACKEND +
          ':8080/scheduler/webservices/SchedulerWS?wsdl', true);

        // build SOAP request
        var sr = `
                    <Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
                        <Body>
                            <startDelivery xmlns="http://www.polytech.unice.fr/si/4a/isa/drone-delivery/logistic">
                                <id xmlns="">`+ this.deliveryId +`</id>
                            </startDelivery>
                        </Body>
                    </Envelope>
                    `

        console.log(sr)

        let context = this

        this.xmlhttp.onreadystatechange = function () {
          if (context.xmlhttp.readyState == 4) {
            if (context.xmlhttp.status == 200) {

              let respXML = context.xmlhttp.responseXML;

              let createdDelivery = respXML.getElementsByTagName('strated')[0].innerHTML
              console.log(createdDelivery)

              if (createdDelivery == 'true') {
                context.resetDelivery()
                context.createdDelivery = true;
                context.displaySuccessDelivery = true;
                context.displayFailedDelivery = false;
              } else {
                context.createdDelivery = false;
                context.displaySuccessDelivery = false;
                context.displayFailedDelivery = true;
              }
              context.displayedDelivery = true;
            }
          }
        }
        // Send the POST request
        this.xmlhttp.setRequestHeader('Content-Type', 'text/xml');
        this.xmlhttp.send(sr);
      },
      validatePackage() {
        let valid = this.$refs.formPackage.validate()
        this.displayFailedPackage = false;
        this.displaySuccessPackage = false;
        if(valid){
          this.changePackageStatus()
        }
        console.log(valid)
      },
      validateDelivery() {
        let valid = this.$refs.formDelivery.validate()
        this.displayFailedDelivery = false;
        this.displaySuccessDelivery = false;
        if(valid){
          this.launchDelivery()
        }
        console.log(valid)
      },
      resetPackage() {
          this.$refs.formPackage.reset()
      },
      resetDelivery() {
          this.$refs.formDelivery.reset()
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