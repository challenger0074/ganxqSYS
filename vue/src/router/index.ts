
import { createRouter, createWebHistory } from "vue-router";

import About from '@/pages/About.vue'
import Home from '@/pages/Home.vue'
import News from '@/pages/News.vue'
import Detail from '@/pages/Detail.vue'
import Login from '@/components/Login.vue'
import Element from '@/pages/Element.vue'
import EmployeeManager from "@/ganxq/Manager.vue"
import Reader from  "@/reader/Reader.vue"
import ReaderHome from "@/reader/components/Home.vue"
  export const router= createRouter({
    history:createWebHistory(),//路由的工作模式
    routes:[//一个一个的路由规则
        {
            path:'/',
            redirect:'/login'
        },//默认打开重定向到login
        {
            name:'login',
            path:'/login',
            component:Login
        },
        {
            name:'reader',
            path:'/reader',
            component:Reader,
            children:[
               /* {
                    path: '/',
                    redirect:'/home'   无效
                },*/
                {
                    name:'home',
                    path:'home',
                    component:ReaderHome,
                    props:true
                }
            ]
        },
        {
            name:'employee',
            path:'/employee',
            component:EmployeeManager
        },
        {
            name:'element',
            path:'/element',
            component:Element
        },
        {
        name:'home1',
        path:'/home',
        component:Home
        },
    {
        name:'about1',
        path:'/about',
        component:About
    },
    {
        name:'news1',
        path:'/news',
        component:News,
        children:[
            {
                name:'detail',
                path:'detail/:id/:title/:content?',
                component:Detail,
                props:true
            }
        ]
    }
    ]}
 )