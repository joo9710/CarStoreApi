import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Car from "@/views/Car";
import Login from "@/views/Login";
import CarWrite from "@/views/CarWrite";
import CarDetail from "@/views/CarDetail";
import CarChange from "@/views/CarChange";
import test from "@/views/test";
import test2 from "@/views/test2";
import SignUp from "@/views/SignUp";
import WishList from "@/views/WishList";
import MyPage from "@/views/MyPage";
import putMember from "@/views/putMember";
import CompanyOfCar from "@/views/CompanyOfCar";

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
  },
  {
    path: '/CarDetail',
    name: 'CarDetail',
    component: CarDetail
  },
  {
    path: '/CarChange',
    name: 'CarChange',
    component: CarChange
  },
  {
    path: '/test',
    name: 'test',
    component: test
  },
  {
    path: '/test2',
    name: 'test2',
    component: test2
  },
  {
    path: '/SignUp',
    name: 'SignUp',
    component: SignUp
  },

  {
    path: '/WishList',
    name: 'WishList',
    component: WishList
  },

  {
    path: '/MyPage',
    name: 'MyPage',
    component: MyPage
  },
  {
    path: '/putMember',
    name: 'putMember',
    component: putMember
  },

  {
    path: '/CompanyOfCar',
    name:'CompanyOfCar',
    component: CompanyOfCar
  }

]

const router = new VueRouter({
  routes
})

export default router
