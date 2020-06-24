import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

// axios
import './plugins/axios'

// element-ui
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
Vue.use(ElementUI)

// echarts
import VeLine from 'v-charts/lib/line.common'
Vue.component(VeLine.name ,VeLine)

// notice
import './utils/notice.js'



Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
