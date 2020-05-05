import Home from './pages/Home.vue';
import Client from './pages/Client.vue';
import Manu from './pages/Manu.vue';
import Vue from 'vue';
import VueRouter from 'vue-router';
Vue.use(VueRouter);

const routes = [
    {
        path: '/home',
        name: 'Home',
        component: Home,
        alias : '/'
    },
    {
        path: '/client',
        name: 'Client',
        component: Client
    },
    {
        path: '/manu',
        name: 'Manu',
        component: Manu
    },
]

export const router = new VueRouter({
    mode : 'history',
    base: process.env.BASE_URL,
    routes : routes,
})