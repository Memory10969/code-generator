import Vue from 'vue'
import App from './App.vue'

import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css'
Vue.use(Antd)
Vue.config.productionTip = false

import Vuex from 'vuex'
Vue.use(Vuex)

import VueRouter from 'vue-router'

Vue.use(VueRouter)

import router from './router/router'

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
