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
      let enveloppe =
        '<?xml version="1.0"?><soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:cus="http://www.polytech.unice.fr/si/4a/isa/tcf/customer-care">' +
        '<soapenv:Header/>' +
        '<soapenv:Body>' +
        '<cus:listAllRecipes/>' +
        '</soapenv:Body>' +
        '</soapenv:Envelope>'

      this.axios
        .post('http://localhost:8080/tcf-backend/webservices/CustomerCareWS',
          enveloppe, {
            headers: {
              'Accept': 'application/xml',
              'Content-Type': 'application/xml',
              'Access-Control-Allow-Origin': '*'
            }
          }).then(result => {

          console.log(result.data)
          this.info = result.data
        }).catch(err => {
          console.log(err.response)
        })

    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>