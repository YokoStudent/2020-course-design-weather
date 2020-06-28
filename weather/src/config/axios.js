import '../plugins/axios'

axios.defaults.baseURL = "/api/v1";
axios.defaults.timeout = 5000 ;

// 请求拦截器
axios.interceptors.request.use(function(config){
	
	// token
	let token = localStorage.getItem("accessToken") || "";
	if("" != token){
		config.headers.accessToken = token ;
	}
	
	if(config.method === "post"){
		config.data = JSON.stringify(config.data) ;
		// json
		config.headers = {
			'Content-Type': 'application/json'
		}
	}	
	
	console.log("request-data:"+config.data);
	
	return config ;
},function(error){
	return Promise.reject(error) ;
})

// 响应拦截器
axios.interceptors.response.use(function(response){
	// 如果返回的状态码为200，说明接口请求成功，可以正常拿到数据
	if(response.status === 200){
		return Promise.resolve(response) ;
	}else {
		return Promise.reject(response) ;
	}
},function(error){
	if (error.response.status) {
		switch (error.response.status) {
			// 401: 未登录
			// 未登录则跳转登录页面，并携带当前页面的路径
			// 在登录成功后返回当前页面，这一步需要在登录页操作。
			// case 401:
			// 	router.replace({
			// 		path: '/login',
			// 		query: {
			// 			redirect: router.currentRoute.fullPath
			// 		}
			// 	});
			// 	break;

			// 403 token过期
			// 登录过期对用户进行提示
			// 清除本地token和清空vuex中token对象
			// 跳转登录页面
			// case 403:
			// 	// 清除token
			// 	localStorage.removeItem('token');
			// 	// store.commit('loginSuccess', null);
			// 	// 跳转登录页面，并将要浏览的页面fullPath传过去，登录成功后跳转需要访问的页面
			// 	setTimeout(() => {
			// 		router.replace({
			// 			path: '/login',
			// 			query: {
			// 				redirect: router.currentRoute.fullPath
			// 			}
			// 		});
			// 	}, 1000);
			// 	break;
		}
		return Promise.reject(error.response);
	}
})

export default axios;