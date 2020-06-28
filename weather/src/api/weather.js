export function getTimes(){
	return axios({
		url:'/weather/getTimes',
		method:'GET'
	})
}

export function getWeather(cityId){
	return axios({
		url:'/weather/getWeather?cityID=' + cityId ,
		method:'GET'
	})
}

export function getRecentCitys(){
	return axios({
		url:'/weather/getRecentCitys',
		method:'GET'
	})
}