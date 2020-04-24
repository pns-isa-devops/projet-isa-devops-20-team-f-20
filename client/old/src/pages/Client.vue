<template>
  <v-container fluid>

    <v-app-bar app color="purple darken-2" dark>
      <div class="d-flex align-center" @click="goToHome()">
        <v-img alt="Livrair'" class="shrink mr-2" contain src="../assets/drone_cam.png" transition="scale-transition"
          width="40" />
      </div>
      <v-toolbar-title class="headline text-upper" @click="goToHome()">
        <span>LIVRAIR - </span>
        <span data-cy="client_title" class="font-weight-light">SERVICE CLIENT</span>
      </v-toolbar-title>

      <v-spacer></v-spacer>

      <v-btn href="https://github.com/pns-isa-devops/projet-isa-devops-20-team-f-20" target="_blank" text>
        <span class="mr-2">Git Repo</span>
        <v-icon>mdi-open-in-new</v-icon>
      </v-btn>
    </v-app-bar>


    <v-row align="center" justify="space-around">
      <v-col cols="6" align-self="center">
        <CalendarDelivery mode='week'/>
      </v-col>
      <v-col cols="4" align-self="center">
        <AddDelivery/>
        <PackageList ref="packageList" mode="client"/>
      </v-col>
    </v-row>

    <v-row align="center" justify="center">
      <v-col md12>
        <DeliveryList ref="deliveryList" mode="full"/>
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
  import AddDelivery from "../components/AddDelivery"
  export default {
    name: 'Client',
    components: {
      PackageList,
      DeliveryList,
      CalendarDelivery,
      AddDelivery
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