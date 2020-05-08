<template>
  <v-container fluid>

    <v-app-bar app color="purple darken-2" dark>
      <div class="d-flex align-center" @click="goToHome()">
        <v-img alt="Livrair'" class="shrink mr-2" contain src="../assets/drone_cam.png" transition="scale-transition"
          width="40" />
      </div>
      <v-toolbar-title class="headline text-upper" @click="goToHome()">
        <span class="font-weight-light">LIVRAIR - </span>
        <span data-cy="client_title">SERVICE CLIENT</span>
      </v-toolbar-title>

      <v-spacer></v-spacer>

      <v-btn href="https://github.com/pns-isa-devops/projet-isa-devops-20-team-f-20" target="_blank" text>
        <span class="mr-2">Git Repo</span>
        <v-icon>mdi-open-in-new</v-icon>
      </v-btn>
    </v-app-bar>


    <v-row align="center" justify="space-around">
      <v-col cols="6" align-self="start">
        <CalendarDelivery mode='week'/>
      </v-col>
      <v-col cols="4" align-self="center">
        <AddDelivery/>
      </v-col>
    </v-row>

    <v-row align="start" justify="space-around">
      <v-col cols="4">
        <DeliveryList ref="deliveryList" mode="full"/>
      </v-col>
      <v-col cols="4">
        <PackageList ref="packageList" mode="client"/>
      </v-col>
    </v-row>

    <v-btn data-cy="client_retreive_package" fixed dark fab bottom left color="purple" @click="retreiveIncomingPackage">
      <v-icon>mdi-archive-arrow-down-outline</v-icon>
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
        xmlhttp: new XMLHttpRequest(),
      }
    },
    methods: {
      retreiveIncomingPackage(){
        console.log(process.env)


        this.xmlhttp.open('POST', 'http://' + process.env.VUE_APP_BACKEND +
          ':8080/scheduler/webservices/LogisticWS?wsdl', true);

        // build SOAP request
        var sr =
          `<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
                <Body>
                    <retrieveIncomingPackages xmlns="http://www.polytech.unice.fr/si/4a/isa/drone-delivery/logistic"/>
                </Body>
            </Envelope>`

        let context = this

        this.xmlhttp.onreadystatechange = function () {
          if (context.xmlhttp.readyState == 4) {
            if (context.xmlhttp.status == 200) {
              let respXML = context.xmlhttp.responseXML;
              console.log(respXML);
            }
          }
        }
        // Send the POST request
        this.xmlhttp.setRequestHeader('Content-Type', 'text/xml');
        this.xmlhttp.send(sr);
      },
      goToHome() {
        this.$router.push('/home');
      },
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>