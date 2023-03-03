
import Vue from 'vue'
import VueRouter from 'vue-router'


const tclass = () => import('../components/example/tClass.vue')
const tstudent = () => import('../components/example/tStudent.vue')
const routes = [
    { path: '', redirect: '/' },
    { path: '/student', component: tstudent },
    { path: '/class', component: tclass },
]
const router = new VueRouter({
    routes
})

export default router