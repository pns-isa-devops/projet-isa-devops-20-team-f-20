<template>
  <v-row>
    <v-col>
      <v-row align="center" justify="center">
        <span class="title text-upper" color="" dark>
          <span style="margin-right: 5px;">DELIVERY</span>
          <span class="font-weight-light">LIST</span>
        </span>
        <v-btn data-cy="refresh_delivery" color="purple darken-2" icon @click="this.getAllDeliveries">
          <v-icon>mdi-cached</v-icon>
        </v-btn>
      </v-row>
      <v-row align="center" justify="center">
        <v-data-table :headers="headers" :items="deliveries" class="elevation-4">
          <template v-slot:item.status="{ item }">
            <v-chip :color="getColor(item.status)" dark>{{ item.status }}</v-chip>
          </template>
        </v-data-table>
      </v-row>
    </v-col>
  </v-row>
</template>

<script>
  export default {
    name: 'DeliveryList',
    props: {
      msg: String
    },
    data() {
      return {
        xmlhttp: new XMLHttpRequest(),
        headers: [{
            text: 'ID',
            align: 'start',
            sortable: true,
            value: 'id',
          },
          {
            text: 'Package ID',
            value: 'package_id'
          },
          {
            text: 'Status',
            align: 'center',
            value: 'status'
          },
          {
            text: 'Delivery Date',
            value: 'delivery_date'
          },
          {
            text: 'Delivery Time',
            value: 'delivery_time'
          },
          {
            text: 'Drone',
            value: 'drone'
          },
        ],
        deliveries: [
          /* {
            id: '874512',
            status: 'ASSIGNED',
            address: '53 rue de la porte',
          },
          {
            id: '654465',
            status: 'ASSIGNED',
            address: '18 avenue de chalaise',
          },
          {
            id: '515845',
            status: 'WAITING',
            address: '74 boulerd garnier',
          } */
        ],

      }
    },
    methods: {
      getColor(status) {
        if (status == 'FAILED') return 'red'
        else if (status == 'READY') return 'orange'
        else if (status == 'SENT') return 'green'
        else return 'grey'
      },
      delay(ms) {
        return new Promise(resolve => setTimeout(resolve, ms));
      },
      getAllDeliveries() {
        (async () => {
          await this.delay(500);
          this.xmlhttp.open('POST', 'http://'+process.env.VUE_APP_BACKEND+':8080/delivery/webservices/DeliveryWS?wsdl', true);

          // build SOAP request
          var sr =
            `<?xml version="1.0" encoding="utf-8"?>
          <Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
              <Body>
                  <getPlannedDeliveries xmlns="http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery"/>
              </Body>
          </Envelope>`

          let context = this

          this.xmlhttp.onreadystatechange = function () {
            if (context.xmlhttp.readyState == 4) {
              if (context.xmlhttp.status == 200) {

                let respXML = context.xmlhttp.responseXML;
                // let respJSON = require('xml-js').xml2json(context.xmlhttp.response, {
                //   compact: true,
                //   spaces: 4
                // });
                console.log(respXML)

                let deliveries = respXML.getElementsByTagName('planned_packages')
                console.log(deliveries)

                context.deliveries = []
                for (let deli of deliveries) {
                  let respDeli = {
                    id: deli.getElementsByTagName('id')[1].innerHTML,
                    status: deli.getElementsByTagName('status')[1].innerHTML,
                    package_id: deli.getElementsByTagName('aPackage')[0].getElementsByTagName('id')[0]
                      .innerHTML,
                    drone: deli.getElementsByTagName('drone')[0].getElementsByTagName('id')[0].innerHTML,
                    delivery_date: new Date(parseInt(deli.getElementsByTagName('deliveryDate')[0].innerHTML) *
                      1000).toLocaleDateString(),
                    delivery_time: new Date(parseInt(deli.getElementsByTagName('deliveryDate')[0].innerHTML) *
                      1000).toLocaleTimeString(),
                    //delivery_date: deli.getElementsByTagName('deliveryDate')[0].innerHTML,
                  }
                  context.deliveries.push(respDeli);
                }
              }
            }
          }
          // Send the POST request
          this.xmlhttp.setRequestHeader('Content-Type', 'text/xml');
          this.xmlhttp.send(sr);
        })();
      }
    },
    mounted() {
      this.getAllDeliveries()
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
