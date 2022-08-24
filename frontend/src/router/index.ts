import { createRouter, createWebHistory } from 'vue-router'
import AtorView from '@/views/Ator/AtorView.vue'
import HomeView from '@/views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    // {
    //   path: '/ator',
    //   name: 'ator',
    //   component: () => import('../views/AboutView.vue')
    // }
    {
      path: '/ator',
      name: 'ator',
      component: AtorView
    }
  ]
})

export default router
