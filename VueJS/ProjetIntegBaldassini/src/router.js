import { createRouter, createWebHistory } from 'vue-router';
import Home from './views/Home.vue';
import Rapport from './views/Rapport.vue';


const routes = [
  {
    path: '/',
    name: 'Accueil',
    component: Home,
  },
  { path: '/rapport/:id', 
    name: 'Rapport', 
    component: Rapport, 
    props: true }
];

const router = createRouter({
  routes,
  history: createWebHistory(),
  linkActiveClass: 'active',
});

export default router;