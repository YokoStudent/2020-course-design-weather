export function register(data){
	// return axios({
	// 	url:'',
	// 	data:data,
	// 	method:'POST'
	// })
	return new Promise(function(resolve,reject){
		setTimeout(function(){
			var num = Math.ceil(Math.random()*10) ;
			if(num<=5){
				return resolve({"code":200})
			}else {
				return reject({"code":100})
			}
		},1000) ;
	});
}

export function login(data){
	// return axios({
	// 	url:'',
	// 	data:data,
	// 	method:'POST'
	// })
	return new Promise(function(resolve,reject){
		setTimeout(function(){
			var num = Math.ceil(Math.random()*10) ;
			if(num<=5){
				return resolve({"code":200})
			}else {
				return reject({"code":100})
			}
		},1000) ;
	});
}