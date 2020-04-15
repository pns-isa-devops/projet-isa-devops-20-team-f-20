<template>
  <v-row>
    <v-col>
      <v-row align="center" justify="center">
        <span class="title text-upper" color="" dark>
          <span style="margin-right: 5px;" >PACKAGE</span>
          <span class="font-weight-light">LIST</span>
        </span>
        <v-btn color="purple darken-2" icon @click="this.getAllPackages">
          <v-icon>mdi-cached</v-icon>
        </v-btn>
      </v-row>
      <v-row align="center" justify="center">
        <v-data-table :headers="headers" :items="packages" class="elevation-4">
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
    name: 'PackageList',
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
            text: 'Status',
            align: 'center',
            value: 'status'
          },
          {
            text: 'Address',
            value: 'address'
          },
        ],
        packages: [
          // {
          //   id: '874512',
          //   status: 'ASSIGNED',
          //   address: '53 rue de la porte',
          // },
          // {
          //   id: '654465',
          //   status: 'ASSIGNED',
          //   address: '18 avenue de chalaise',
          // },
          // {
          //   id: '515845',
          //   status: 'WAITING',
          //   address: '74 boulerd garnier',
          // }
        ],

      }
    },
    methods: {
      getColor(status) {
        if (status == 'WAITING') return 'red'
        else if (status == 'REGISTERED') return 'orange'
        else if (status == 'ASSIGNED') return 'green'
        else return 'grey'
      },
      getAllPackages() {
        this.xmlhttp.open('POST', 'http://localhost:8080/delivery/webservices/DeliveryWS?wsdl', true);

        // build SOAP request
        var sr =
          `<?xml version="1.0" encoding="utf-8"?>
          <Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
              <Body>
                  <getAllPackages xmlns="http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery"/>
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
              //console.log(respXML)

              let packages = respXML.getElementsByTagName('package')
              //console.log(packages)

              context.packages = []
              for (let pack of packages) {
                //console.log(pack)
                let respPackage = {
                  id: pack.getElementsByTagName('id')[0].innerHTML,
                  status: pack.getElementsByTagName('packageStatus')[0].innerHTML,
                  address: pack.getElementsByTagName('address')[0].innerHTML,
                }
                context.packages.push(respPackage);
              }
            }
          }
        }
        // Send the POST request
        this.xmlhttp.setRequestHeader('Content-Type', 'text/xml');
        this.xmlhttp.send(sr);
      }
    },
    mounted() {
      this.getAllPackages()
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>