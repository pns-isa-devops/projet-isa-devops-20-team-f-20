<template>
  <v-container fluid>

    <v-app-bar app color="green darken-2" dark>
      <div class="d-flex align-center" @click="goToHome()">
        <v-img alt="Livrair'" class="shrink mr-2" contain src="../assets/drone_package.png"
          transition="scale-transition" width="40" />
      </div>
      <v-toolbar-title class="headline text-upper" @click="goToHome()">
        <span>LIVRAIR</span>
        <span class="font-weight-light"> - MANUTENTIONNAIRE</span>
      </v-toolbar-title>

      <v-spacer></v-spacer>

      <v-btn href="https://github.com/pns-isa-devops/projet-isa-devops-20-team-f-20" target="_blank" text>
        <span class="mr-2">Git Repo</span>
        <v-icon>mdi-open-in-new</v-icon>
      </v-btn>
    </v-app-bar>

    <v-row align="strat" justify="space-around">
      <v-col cols="3" align-self="center">
        <v-card>
          <v-card-text>
            <span class="title text-upper" color="" dark>
              <span style="margin-right: 5px;" class="font-weight-light">CHANGE</span>
              <span>PACKAGE STATUS</span>
            </span>
            <v-form ref="form" v-model="validPackage" lazy-validation>
              <v-row align="center" justify="space-around">
                <v-col cols="4">
                  <v-text-field name="packageID" v-model="packageId" label="Package ID" id="idPackage" :rules="idRules"
                    required></v-text-field>
                </v-col>
                <v-col cols="7">
                  <v-select :items="items" v-model="packageStatus" label="Package Satut" :rules="statusRules">
                  </v-select>
                </v-col>
              </v-row>
              <v-row align="center" justify="space-around">
                <v-btn tile data-cy="validate_status_btn" :disabled="!validPackage" color="success" @click="validate">
                  Change package status
                </v-btn>
              </v-row>
            </v-form>
            <v-divider style="margin-top: 25px"></v-divider>
            <v-row align="center" justify="center">
              <v-col cols="8" align-self="center">
                <PackageList ref="packageList" mode="manu" />
              </v-col>
            </v-row>
          </v-card-text>
        </v-card>
      </v-col>
      <v-col cols="6">
        <CalendarDelivery mode='day' />
      </v-col>
      <v-col cols="3" align-self="center">
        <v-card>
          <v-card-text>
             <span class="title text-upper" color="" dark>
              <span style="margin-right: 5px;" class="font-weight-light">LAUNCH</span>
              <span>DELIVERY</span>
            </span>
            <v-form ref="form" v-model="validDelivery" lazy-validation>
              <v-row align="center" justify="center">
                <v-col cols="4">
                  <v-text-field name="DroneID" v-model="packageId" label="Drone ID" id="idDrone" :rules="idRules"
                    required></v-text-field>
                </v-col>
              </v-row>
              <v-row align="center" justify="space-around">
                <v-btn tile data-cy="validate_btn" :disabled="!validDelivery" color="success" @click="validate">
                  Launch Drone and Delivery
                </v-btn>
              </v-row>
            </v-form>
            <v-divider style="margin-top: 25px"></v-divider>
            <v-row align="center" justify="center">
              <v-col cols="8" align-self="center">
                <DeliveryList ref="packageList" mode="light"/>
              </v-col>
            </v-row>
          </v-card-text>
        </v-card>
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
        idRules: [
          v => (v && !isNaN(v)) || 'ID must be a number',
        ],
        packageStatus: '',
        statusRules: [
          v => (v && (v == 'REGISTERED' || v == 'WAITING' || v == 'ASSIGNED')) || 'Status not correct',
        ],

      }
    },
    methods: {
      validate() {
        let valid = this.$refs.form.validate()
        console.log(valid)
      },
      gotoHome(){
        this.$router.push('/')
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>