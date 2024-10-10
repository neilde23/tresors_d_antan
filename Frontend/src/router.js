import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/views/Home.vue'
import About from '@/views/About.vue'
import Login from '@/views/Login.vue'
import Register from '@/views/Register.vue'
import Profile from '@/views/Profile.vue'
import Articles from '@/views/Products.vue'
import Checkout from '@/views/Checkout.vue'

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home
    },
    {
        path: '/about',
        name: 'About',
        component: About
    },
    {
        path: '/login',
        name: 'Login',
        component: Login
    },
    {
        path: '/register',
        name: 'Register',
        component: Register
    },
    {
        path: '/profile',
        name: 'Profile',
        component: Profile
    },
    {
        path: '/articles',
        name: 'Articles',
        component: Articles
    },
    {
        path : '/checkout',
        name : 'Checkout',
        component : Checkout
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
})
export default router;