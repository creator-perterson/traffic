import Vue from 'vue'
import VueRouter from 'vue-router'
import pathArr from '@/router/pathArr.js'

// // 导入需要的组件
// import Login from '@/components/MyLogin.vue'
import Home from '@/components/MyHome.vue'
import OD from '@/components/menus/OD.vue'
//import OD1 from '@/components/menus/OD1.vue'
import Calendar from '@/components/menus/Calendar.vue'
import SingleWeek from '@/components/menus/SingleWeek.vue'
import SingleWeek1 from '@/components/menus/SingleWeek1.vue'
import StaChange from '@/components/menus/StaChange.vue'
import Time from '@/components/menus/Time.vue'
//import UserMark from '@/components/menus/UserMark.vue'
import Weekend from '@/components/menus/Weekend.vue'
import Weekend1 from '@/components/menus/Weekend1.vue'
import Map from '@/components/Map.vue'
import MyMap from '@/components/menus/MyMap.vue'
import Month from '@/components/menus/Month.vue'

import Detail from '@/components/user/Detail.vue'
import StationPage from '@/views/StationPage'
import KindPage from '@/views/KindPage.vue'
import TimePage from '@/views/TimePage.vue'
import StaChangePage from '@/views/StaChangePage.vue'
import weekendPage from '@/views/weekendPage.vue'
import SingleWeekPage from '@/views/SingleWeekPage.vue'
import UserMarkPage from '@/views/UserMarkPage.vue'
import ODPage from '@/views/ODPage.vue'
//import UserMarkPage1 from '@/views/UserMarkPage1.vue'

Vue.use(VueRouter)

const router = new VueRouter({
  routes: [
    { path: '/', redirect: '/map' },
    // 后台主页的路由规则
    {
      path: '/home',
      component: Home,
      redirect: '/home/kind',
      children: [
        
        { path: 'od', component: ODPage },
        { path: 'calendar', component:Calendar },
        { path: 'singleweek', component: SingleWeekPage },
        { path: 'stachange', component: StaChangePage },
        { path: 'time', component: TimePage },
        { path: 'usermark',component:UserMarkPage},
        { path: 'weekend', component: weekendPage },
       // { path: 'staChange', component: StaChange },
        { path: 'weekend1', component: Weekend1 },
        { path: 'kind', component: KindPage},
        //{ path: 'usermark1', component: UserMarkPage1 },
        // 用户详情页的路由规则
        { path: 'userinfo/:id', component: Detail, props: true }
      ]
    },
    { path: '/map', component: Map ,children: [
      { path: 'mymap' , component: MyMap},
      {path: 'station' , component: StationPage}
    ]},
  ]
})

// // 全局前置守卫
// router.beforeEach(function(to, from, next) {
//   if (pathArr.indexOf(to.path) !== -1) {
//     const token = localStorage.getItem('token')
//     if (token) {
//       next()
//     } else {
//       next('/login')
//     }
//   } else {
//     next()
//   }
// })

export default router
