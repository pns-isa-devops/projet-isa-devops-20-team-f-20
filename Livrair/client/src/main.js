import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify';
import { router } from './router';
import axios from 'axios'
 
Vue.prototype.$http = axios

Vue.config.productionTip = false

router.push({ path: '/' })

new Vue({
  router,
  vuetify,
  render: h => h(App)
}).$mount('#app')
