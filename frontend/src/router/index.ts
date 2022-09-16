import { createRouter, createWebHistory } from 'vue-router'
import AtorView from '@/views/Ator/AtorView.vue'
import HomeView from '@/views/home/HomeView.vue'
import ClasseView from '@/views/classe/classeView.vue'
import DiretorView from '@/views/diretor/diretorView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/ator',
      name: 'ator',
      component: AtorView
    },
    {
      path:'/classes',
      name:'classes',
      component: ClasseView
    },
    {
      path:'/diretor',
      name:'diretor',
      component: DiretorView
    }
  ]
})

export default router
