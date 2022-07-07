import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import router from './router'
import axios from 'axios'
import store from "@/store/index";

Vue.config.productionTip = false

Vue.prototype.$eventBus = new Vue();
Vue.prototype.$axios = axios


new Vue({
  router,
  vuetify,
  render: h => h(App),
  store
}).$mount('#app')
