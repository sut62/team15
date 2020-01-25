import Vue from 'vue'
import VueRouter from 'vue-router'

import MainLogin from '../components/MainLogin.vue' 
import UserLogin from '../components/UserLogin.vue'
import HomeAdmin from "../components/HomeAdmin.vue"
import SingInAdmin from "../components/SingInAdmin.vue"

import CreateInsurance from '../views/CreateInsurance.vue'
import Hospital from '../views/Hospital.vue'
import RenewInsurance from '../views/RenewInsurance.vue'
import Promotion from '../views/Promotion.vue'
import PayInsurance from '../views/PayInsurance.vue'
import User from '../views/User.vue'

import Home from '../views/Home.vue'
import RegisterInsurance from '../views/RegisterInsurance.vue'
import PrivilegeInsurance from '../views/PrivilegeInsurance.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'MainLogin',
    component: MainLogin
  },
  {
    path: '/UserLogin',
    name: 'UserLogin',
    component: UserLogin
  },
  {
    path: '/CreateInsurance',
    name: 'CreateInsurance',
    component: CreateInsurance
  },
  {
    path: '/Hospital',
    name: 'Hospital',
    component: Hospital
  },
  {
    path: '/PayInsurance',
    name: 'PayInsurance',
    component: PayInsurance
  },
  {
    path: '/Promotion',
    name: 'Promotion',
    component: Promotion
    
  },
  {
    path: '/RenewInsurance',
    name: 'RenewInsurance',
    component: RenewInsurance
  },
  {
    path: '/User',
    name: 'User',
    component: User
  }, 
  {
    path: '/Home',
    name: 'Home',
    component: Home
  },
  {
    path: '/RegisterInsurance',
    name: 'RegisterInsurance',
    component: RegisterInsurance
  }, 
  {
    path: '/PrivilegeInsurance',
    name: 'PrivilegeInsurance',
    component: PrivilegeInsurance
  },
  {
    path: '/HomeAdmin',
    name: 'HomeAdmin',
    component: HomeAdmin
  },

  {
    path: '/SingInAdmin',
    name: 'SingInAdmin',
    component: SingInAdmin
  }
  
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
