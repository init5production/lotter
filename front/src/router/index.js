import Vue from 'vue'
import Router from 'vue-router'
import Status from '@/components/Status'
import Estimation from '@/components/Estimation'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Status',
      component: Status
    },
    {
      path: '/estimation',
      name: 'Estimation',
      component: Estimation
    }
  ]
})
