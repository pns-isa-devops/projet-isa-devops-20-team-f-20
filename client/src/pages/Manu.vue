<template>
  <v-container fluid>

    <v-app-bar app color="green darken-2" dark>
      <div class="d-flex align-center" @click="goToHome()">
        <v-img alt="Livrair'" class="shrink mr-2" contain src="../assets/drone_package.png"
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

    <v-row>
      <v-col cols="12">
        <v-row align="center" justify="center">
          Manu Page
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
  export default {
    name: 'Manu',
    props: {
      msg: String
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>