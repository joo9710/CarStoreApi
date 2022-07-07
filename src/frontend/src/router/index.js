import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Car from "@/views/Car";
import Login from "@/views/Login";
import CarWrite from "@/views/CarWrite";

Vue.use(VueRouter)

const routes = [
  {
    path: '/home',
    name: 'Home',
    component: Home
  },
  {
    path: '/Car',
    name: 'Car',
    component: Car
  },
  {
    path: '/Login',
    name: 'Login',
    component: Login
  },
  {
    path: '/CarWrite',
    name: 'CarWrite',
    component: CarWrite
  }

]

const router = new VueRouter({
  routes
})

export default router
