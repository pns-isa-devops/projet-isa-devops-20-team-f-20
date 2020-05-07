<template>
  <v-app>
    <v-content>
      ERROR 404
    </v-content>
  </v-app>
</template>

<script>
  export default {
    name: '404',

    data: () => ({
      //
    }),
    methods : {
      //
    },
    beforeRouteEnter(to, from, next) {
      next({ path: '/' })
    },
  };
</script>