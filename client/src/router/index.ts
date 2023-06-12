import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/appsearch',
      name: 'appsearch',
      component: () => import('@/views/ApplicationSearchView.vue')
    },
    {
      path: '/servicesearch',
      name: 'servicesearch',
      component: () => import('@/views/ServiceSearchView.vue')
    },
    {
      path: '/application',
      name: 'applicationadd',
      component: () => import('@/views/ApplicationAddView.vue')
    },
    {
      path: '/service',
      name: 'serviceadd',
      component: () => import('@/views/ServiceAddView.vue')
    },
  ]
})

export default router
