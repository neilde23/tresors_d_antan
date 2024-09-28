import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/App.vue'
import About from '@/views/About.vue'

const routes = [
    {
        path: '/',
        name: 'Visitor',
        component: Home
    },
    {
        path: '/about',
        name: 'About',
        component: About
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
})
export default router;