import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [ 
	{
		path:'/' ,
		name:'Weather' ,
		component:() => import('../views/weather.vue')
	},
	{
		path:'/login' ,
		name:'Login' ,
		component:() => import('../views/login.vue')
	},
	{
		path:'/register' ,
		name:'Register' ,
		component:() => import('../views/register.vue')
	}
]

const router = new VueRouter({
  routes
})

router.afterEach((page)=>{
	let title = page.name ;
	document.title = title ;
})

export default router
