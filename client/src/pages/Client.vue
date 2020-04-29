<template>
    <v-container fluid>

        <v-app-bar app color="purple darken-2" dark>
            <div @click="goToHome()" class="d-flex align-center">
                <v-img alt="Livrair'" class="shrink mr-2" contain src="../assets/drone_cam.png"
                       transition="scale-transition"
                       width="40"/>
            </div>
            <v-toolbar-title @click="goToHome()" class="headline text-upper">
                <span>LIVRAIR - </span>
                <span class="font-weight-light" data-cy="client_title">SERVICE CLIENT</span>
            </v-toolbar-title>

            <v-spacer></v-spacer>

            <v-btn href="https://github.com/pns-isa-devops/projet-isa-devops-20-team-f-20" target="_blank" text>
                <span class="mr-2">Git Repo</span>
                <v-icon>mdi-open-in-new</v-icon>
            </v-btn>
        </v-app-bar>


        <v-row align="center" justify="space-around">
            <v-col align-self="center" cols="6">
                <CalendarDelivery/>
            </v-col>
            <v-col align-self="center" cols="4">
                <AddDelivery/>
                <PackageList ref="packageList"/>
            </v-col>
        </v-row>

        <v-row align="center" justify="center">
            <v-col md12>
                <DeliveryList ref="deliveryList"/>
            </v-col>
        </v-row>

        <v-btn bottom color="pink" dark fab fixed left>
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
            return {}
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
