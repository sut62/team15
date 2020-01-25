import Vue from 'vue'
import VueRouter from 'vue-router'

import MainLogin from '../components/MainLogin.vue' 
import UserLogin from '../components/UserLogin.vue'
import HomeAdmin from "../components/HomeAdmin.vue"
import SingInAdmin from "../components/SingInAdmin.vue"

import CreateInsurance from '../views/CreateInsurance.vue'
import Hospital from '../views/Hospital.vue'
import PayInsurance from '../views/PayInsurance.vue'
import User from '../views/User.vue'
import UserSearch from '../views/UserSearch.vue'

import Home from '../views/Home.vue'
import RegisterInsurance from '../views/RegisterInsurance.vue'
import ChangeRegister from '../views/ChangeRegister.vue'
import PrivilegeInsurance from '../views/PrivilegeInsurance.vue'
import CancelPrivilege from '../views/CancelPrivilege.vue'

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
  },
  {
    path: '/CancelPrivilege',
    name: 'CancelPrivilege',
    component: CancelPrivilege
  },
  {
    path: '/ChangeRegister',
    name: 'ChangeRegister',
    component: ChangeRegister
  },
  {
    path: '/UserSearch',
    name: 'UserSearch',
    component: UserSearch
  },
  
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
