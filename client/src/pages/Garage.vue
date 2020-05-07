<template>
  <v-container fluid>

    <v-app-bar app color="yellow darken-2" dark>
      <div class="d-flex align-center" @click="goToHome">
        <v-img alt="Livrair'" class="shrink mr-2" contain src="../assets/drone_fix.png"
          transition="scale-transition" width="40" />
      </div>
      <v-toolbar-title class="headline text-upper" @click="goToHome">
        <span class="font-weight-light">LIVRAIR - </span>
        <span data-cy="garage_title">GARAGE</span>
      </v-toolbar-title>

      <v-spacer></v-spacer>

      <v-btn href="https://github.com/pns-isa-devops/projet-isa-devops-20-team-f-20" target="_blank" text>
        <span class="mr-2">Git Repo</span>
        <v-icon>mdi-open-in-new</v-icon>
      </v-btn>
    </v-app-bar>

    <v-row md12 align="strat" justify="space-around">
      <v-col cols="8" align-self="center">
        <v-card>
          <v-card-text>
            <span class="title text-upper" color="" dark>
              <span style="margin-right: 5px;" class="font-weight-light">CHANGE</span>
              <span>DRONE STATUS</span>
            </span>
            <v-form ref="form" v-model="validPackage" lazy-validation>
              <v-row align="center" justify="space-around" wrap>
                <v-col cols="3">
                  <v-text-field  data-cy="garage_drone_id" name="droneID" v-model="droneId" label="Drone ID" id="idDrone" :rules="idRules"
                    required></v-text-field>
                </v-col>
                <v-col cols="3">
                  <v-select  data-cy="garage_drone_status" :items="items" v-model="sroneStatus" label="Drone Status" :rules="statusRules">
                  </v-select>
                </v-col>
                <v-col cols="3">
                    <v-btn tile data-cy="garage_drone_status_button" :disabled="!validDrone" color="yellow darken-1" @click="validate">
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
    </v-row>
    <v-row md12 align="strat" justify="space-around">
        <v-col cols="12" align-self="center">
                <DroneList ref="droneList"/>
        </v-col>
    </v-row>
  </v-container>
</template>

<script>
  import DroneList from "../components/DroneList"
  export default {
    components: {
      DroneList,
    },
    name: 'Garage',
    props: {
      msg: String
    },
    data() {
      return {
        items: ['AVAILABLE', 'DELIVERING', 'CHARGING', 'REVIEW'],
        validDrone: true,
        droneId: '',
        idRules: [
          v => (v && !isNaN(v)) || 'ID must be a number',
        ],
        droneStatus: '',
        statusRules: [
          v => (v && (v == 'AVAILABLE' || v == 'DELIVERING' || v == 'CHARGING' || v == 'REVIEW')) || 'Status not correct',
        ],
      }
    },
    methods: {
      validate() {
        let valid = this.$refs.form.validate()
        console.log(valid)
      },
      gotoHome(){
        this.$router.push('/home')
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>