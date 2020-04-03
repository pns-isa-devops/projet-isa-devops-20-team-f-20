<template>
  <v-container fluid>

    <v-app-bar app color="purple darken-2" dark>
      <div class="d-flex align-center" @click="goToHome()">
        <v-img alt="Livrair'" class="shrink mr-2" contain src="../assets/drone_cam.png" transition="scale-transition"
          width="40" />
      </div>
      <v-toolbar-title class="headline text-upper" @click="goToHome()">
        <span>LIVRAIR - </span>
        <span id="title" class="font-weight-light">SERVICE CLIENT</span>
      </v-toolbar-title>

      <v-spacer></v-spacer>

      <v-btn href="https://github.com/pns-isa-devops/projet-isa-devops-20-team-f-20" target="_blank" text>
        <span class="mr-2">Git Repo</span>
        <v-icon>mdi-open-in-new</v-icon>
      </v-btn>
    </v-app-bar>


    <v-row align="center" justify="space-around">
      <v-col cols="6">
        <CalendarDelivery />
      </v-col>
      <v-col cols="4" align-self="start">
        <PackageList />
        <DeliveryList />
      </v-col>
    </v-row>

    <v-btn fixed dark fab bottom left color="pink">
      <v-icon>mdi-plus</v-icon>
    </v-btn>

  </v-container>
</template>

<script>
  import PackageList from "../components/PackageList"
  import DeliveryList from "../components/DeliveryList"
  import CalendarDelivery from "../components/CalendarDelivery"
  export default {
    name: 'Client',
    components: {
      PackageList,
      DeliveryList,
      CalendarDelivery
    },
    props: {
      msg: String
    },
    data() {
      return {

      }
    },
    methods: {
      goToHome() {
        this.$router.push('/');
      },
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>