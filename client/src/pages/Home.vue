<template>
  <v-container fluid>

    <v-app-bar app color="green darken-2" dark>
      <div class="d-flex align-center" @click="goToHome()">
        <v-img alt="Livrair'" class="shrink mr-2" contain src="../assets/drone_green.png" transition="scale-transition"
          width="40" />
      </div>
      <v-toolbar-title class="headline text-upper" @click="goToHome()">
        <span class="font-weight-light">LIVRAIR</span>
      </v-toolbar-title>

      <v-spacer></v-spacer>

      <v-btn href="https://github.com/pns-isa-devops/projet-isa-devops-20-team-f-20" target="_blank" text>
        <span class="mr-2">Git Repo</span>
        <v-icon>mdi-open-in-new</v-icon>
      </v-btn>
    </v-app-bar>

    <v-row>
      <v-col cols="12">
        <v-row align="center" justify="center">
          <v-card data-cy="client" class="pa-1 mx-auto" outlined tile max-width="250" @click="goToClient()">

            <v-img class="white--text align-end" height="250px" transition="scale-transition"
              src="../assets/drone_cam.png" />

            <v-card-subtitle class="pb-0">Le service client</v-card-subtitle>

            <v-card-text class="text--primary">
              <div>Whitsunday Island, Whitsunday Islands</div>
            </v-card-text>

            <v-card-actions>
              <v-btn color="primary" text>
                Selectionner
              </v-btn>
            </v-card-actions>
            
          </v-card>
          <v-card data-cy="manu" class="pa-1 mx-auto" outlined tile max-width="250" @click="goToManu()">

            <v-img class="white--text align-end" height="250px" transition="scale-transition"
              src="../assets/drone_package.png" />

            <v-card-subtitle class="pb-0">Le manutentionnaire</v-card-subtitle>

            <v-card-text class="text--primary">
              <div>Whitsunday Island, Whitsunday Islands</div>
            </v-card-text>

            <v-card-actions>
              <v-btn color="primary" text>
                Selectionner
              </v-btn>
            </v-card-actions>

          </v-card>
          <v-card class="pa-1 mx-auto" outlined tile max-width="250">

            <v-img class="white--text align-end" height="250px" transition="scale-transition"
              src="../assets/drone_empty.png" />

            <v-card-subtitle class="pb-0">Le gestionnaire</v-card-subtitle>

            <v-card-text class="text--primary">
              <div>Whitsunday Island, Whitsunday Islands</div>
            </v-card-text>

            <v-card-actions>
              <v-btn color="primary" text>
                Selectionner
              </v-btn>
            </v-card-actions>
          </v-card>
          <v-card class="pa-1 mx-auto" outlined tile max-width="250" @click="goToGarage()">

            <v-img class="white--text align-end" height="250px" transition="scale-transition"
              src="../assets/drone_wireless.png" />

            <v-card-subtitle class="pb-0">Le chargeur</v-card-subtitle>

            <v-card-text class="text--primary">
              <div>Whitsunday Island, Whitsunday Islands</div>
            </v-card-text>

            <v-card-actions>
              <v-btn color="primary" text>
                Selectionner
              </v-btn>
            </v-card-actions>
          </v-card>
          <v-card class="pa-1 mx-auto" outlined tile max-width="250">

            <v-img class="white--text align-end" height="250px" transition="scale-transition"
              src="../assets/drone_fix.png" />

            <v-card-subtitle class="pb-0">Le mecanicien</v-card-subtitle>

            <v-card-text class="text--primary">
              <div>Whitsunday Island, Whitsunday Islands</div>
            </v-card-text>

            <v-card-actions>
              <v-btn color="primary" text>
                Selectionner
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
  export default {
    name: 'Home',
    props: {
      msg: String
    },
    methods: {
      goToClient() {
        this.$router.push('/client')
      },
      goToManu() {
        this.$router.push('/manu')
      },
      goToHome() {
        this.$router.push('/home')
      },
      goToGarage() {
        this.$router.push('/garage')
      }
    },
    beforeRouteEnter(to, from, next) {
      next({ path: '/' })
    },
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>