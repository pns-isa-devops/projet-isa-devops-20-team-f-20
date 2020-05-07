import Home from './pages/Home.vue';
import Client from './pages/Client.vue';
import Manu from './pages/Manu.vue';
import Garage from './pages/Garage.vue';
import Gestio from './pages/Gestio.vue';
import Fail from './pages/404.vue';
import Vue from 'vue';
import VueRouter from 'vue-router';
Vue.use(VueRouter);

const routes = [
    {
        path: '/404',
        name: '404',
        component: Fail,
        alias : '/'
    },
    {
        path: '/home',
        name: 'Home',
        component: Home,
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
    {
        path: '/garage',
        name: 'Garage',
        component: Garage
    },
    {
        path: '/gestio',
        name: 'Gestio',
        component: Gestio
    },
]

export const router = new VueRouter({
    mode : 'history',
    base: process.env.BASE_URL,
    routes : routes,
})