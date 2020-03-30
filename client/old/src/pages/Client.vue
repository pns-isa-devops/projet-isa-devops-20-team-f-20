<template>
  <v-container fluid>

    <v-app-bar app color="purple darken-2" dark>
      <div class="d-flex align-center" @click="goToHome()">
        <v-img alt="Livrair'" class="shrink mr-2" contain src="../assets/drone_cam.png"
          transition="scale-transition" width="40" />
      </div>
      <v-toolbar-title class="headline text-upper" @click="goToHome()">
        <span>LIVRAIR</span>
        <span class="font-weight-light"> - SERVICE CLIENT</span>
      </v-toolbar-title>

      <v-spacer></v-spacer>

      <v-btn href="https://github.com/pns-isa-devops/projet-isa-devops-20-team-f-20" target="_blank" text>
        <span class="mr-2">Git Repo</span>
        <v-icon>mdi-open-in-new</v-icon>
      </v-btn>
    </v-app-bar>


    <v-row align="center" justify="center">
      <v-col cols="12">
    <PackageList/>
    <PackageList/>
      </v-col>
    </v-row>

  </v-container>
</template>

<script>
  import PackageList from "../components/PackageList"
  export default {
    name: 'Client',
    components: {
      PackageList
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