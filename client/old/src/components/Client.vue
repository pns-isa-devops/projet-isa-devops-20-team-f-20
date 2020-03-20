<template>
  <v-container fluid>
    <v-row>
      <v-col cols="12">
        <v-row align="center" justify="center">
          Package List
          {{ this.info }}
        </v-row>
        <v-row align="center" justify="center">
          <v-data-table :headers="headers" :items="desserts" class="elevation-4">
            <template v-slot:item.status="{ item }">
              <v-chip :color="getColor(item.status)" dark>{{ item.status }}</v-chip>
            </template>
          </v-data-table>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
  export default {
    name: 'Client',
    props: {
      msg: String
    },
    data() {
      return {
        info: '',
        headers: [{
            text: 'ID',
            align: 'start',
            sortable: true,
            value: 'id',
          },
          {
            text: 'Status',
            value: 'status'
          },
          {
            text: 'Suplier',
            value: 'supplier'
          },
        ],
        desserts: [{
            id: 'Frozen Yogurt',
            status: 159,
            supplier: '1%',
          },
          {
            id: 'Ice cream sandwich',
            status: 237,
            supplier: '1%',
          },
          {
            id: 'Eclair',
            status: 262,
            supplier: '7%',
          },
          {
            id: 'Cupcake',
            status: 305,
            supplier: '8%',
          },
          {
            id: 'Gingerbread',
            status: 356,
            supplier: '16%',
          },
          {
            id: 'Jelly bean',
            status: 375,
            supplier: '0%',
          },
          {
            id: 'Lollipop',
            status: 392,
            supplier: '2%',
          },
        ],
      }
    },
    methods: {
      getColor(status) {
        if (status > 300) return 'red'
        else if (status > 200) return 'orange'
        else return 'green'
      },
    },
    mounted() {
      // let enveloppe =
      //   '<?xml version="1.0"?>' +
      //   '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:cus="http://www.polytech.unice.fr/si/4a/isa/tcf/customer-care">' +
      //   '<soapenv:Header/>' +
      //   '<soapenv:Body>' +
      //   '<cus:listAllRecipes/>' +
      //   '</soapenv:Body>' +
      //   '</soapenv:Envelope>';
      //
      // this.axios({
      //     method: 'post',
      //     url: 'http://localhost:8080/delivery/webservices/DeliveryWS',
      //     data: '<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">'
      //       + '<Body>'
      //       + '<getPackageById xmlns="http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery">'
      //       + '<id xmlns="">1</id>'
      //       + '</getPackageById>'
      //       + '</Body>'
      //       + '</Envelope>'
      //   })
      //   .then(function (response) {
      //     console.log(response);
      //   })
      //   .catch(function (error) {
      //     console.log(error);
      //   });

        // this.axios({
        //     method: 'post',
        //     url: 'http://localhost:8080/delivery/webservices/DeliveryWS?wsdl',
        //     data: `<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
        //               <Body>
        //                   <getPackageById xmlns="http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery">
        //                       <id xmlns="">1</id>
        //                   </getPackageById>
        //               </Body>
        //           </Envelope>`
        //   })
        //   let enveloppe =
        //   `<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:del="http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery">
        //       <soapenv:Header/>
        //       <soapenv:Body>
        //           <del:getPackageById>
        //             <!--Optional:-->
        //             <id>1</id>
        //           </del:getPackageById>
        //       </soapenv:Body>
        //     </soapenv:Envelope>`
        // this.axios
        //   .post('http://localhost:8080/delivery/webservices/DeliveryWS?wsdl',
        //     enveloppe, {
        //       headers: {
        //         'Accept': '*/*',
        //         'Content-Type': 'text/xml',
        //         'Access-Control-Allow-Origin': '*',
        //         SOAPAction: 'getPackageById'
        //       }
        //     })
        //   .then(function (response) {
        //     console.log(response);
        //   })
        //   .catch(function (error) {
        //     console.log(error);
        //   });

        var xmlhttp = new XMLHttpRequest();
        xmlhttp.open('POST', 'http://localhost:8080/delivery/webservices/DeliveryWS?wsdl', true);

        // build SOAP request
        var sr =
            `<?xml version="1.0" encoding="utf-8"?>
                <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:del="http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery">
                  <soapenv:Header/>
                  <soapenv:Body>
                      <del:getPackageById>
                        <!--Optional:-->
                        <id>1</id>
                      </del:getPackageById>
                  </soapenv:Body>
                </soapenv:Envelope>`

        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4) {
                if (xmlhttp.status == 200) {
                    console.log(xmlhttp.responseXML.getElementsByTagName('address')[0].innerHTML)

                    var result1 = require('xml-js').xml2json(xmlhttp.response, {
                        compact: true,
                        spaces: 4
                    });

                    console.log(result1)
                    let respPackage = [{
                        id: xmlhttp.responseXML.getElementsByTagName('id')[0].innerHTML,
                        status: xmlhttp.responseXML.getElementsByTagName('packageStatus')[0].innerHTML,
                        address: xmlhttp.responseXML.getElementsByTagName('address')[0].innerHTML,
                    }]

                    this.desserts = respPackage

                    console.log(this.desserts)
                }
            }
        }
        // Send the POST request
        xmlhttp.setRequestHeader('Content-Type', 'text/xml');
        xmlhttp.send(sr);

    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>