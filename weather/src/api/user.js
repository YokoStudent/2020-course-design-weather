export function register(data){
	return axios({
		url:'/user/register',
		data:data,
		method:'POST'
	})
}

export function login(data){
	return axios({
		url:'/user/login',
		data:data,
		method:'POST'
	})
}

export function logout(){
	return axios({
		url:'/user/logout',
		method:'GET'
	})
}