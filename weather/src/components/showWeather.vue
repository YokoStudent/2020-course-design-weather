<template>
	<div>
		<ve-line :data="list" :settings="chartSettings"></ve-line>
	</div>
</template>

<script>
	export default{
		props:{
			weatherList:[]
		},
		data(){
			return{
				chartSettings : {
					labelMap: {
						high: '最高温' ,
						low: '最低温' ,
					}
				},
				list:{
					columns:['ymd','high','low'],
					rows:[{
					"date": "22",
					"high": "36",
					"low": "28",
					"ymd": "2020-06-22",
					"week": "星期一",
					"sunrise": "05:40",
					"sunset": "19:29",
					"aqi": 22,
					"fx": "西南风",
					"fl": "2级",
					"type": "阴",
					"notice": "不要被阴云遮挡住好心情"
				},
				{
					"date": "23",
					"high": "35",
					"low": "28",
					"ymd": "2020-06-23",
					"week": "星期二",
					"sunrise": "05:41",
					"sunset": "19:29",
					"aqi": 19,
					"fx": "西南风",
					"fl": "2级",
					"type": "小雨",
					"notice": "雨虽小，注意保暖别感冒"
				},
				{
					"date": "24",
					"high": "32",
					"low": "27",
					"ymd": "2020-06-24",
					"week": "星期三",
					"sunrise": "05:41",
					"sunset": "19:29",
					"aqi": 21,
					"fx": "南风",
					"fl": "1级",
					"type": "大雨",
					"notice": "出门最好穿雨衣，勿挡视线"
				},
				{
					"date": "25",
					"high": "27",
					"low": "24",
					"ymd": "2020-06-25",
					"week": "星期四",
					"sunrise": "05:41",
					"sunset": "19:29",
					"aqi": 22,
					"fx": "西南风",
					"fl": "1级",
					"type": "大雨",
					"notice": "出门最好穿雨衣，勿挡视线"
				},
				{
					"date": "26",
					"high": "32",
					"low": "25",
					"ymd": "2020-06-26",
					"week": "星期五",
					"sunrise": "05:42",
					"sunset": "19:30",
					"aqi": 24,
					"fx": "南风",
					"fl": "1级",
					"type": "中雨",
					"notice": "记得随身携带雨伞哦"
				},
				{
					"date": "27",
					"high": "33",
					"low": "26",
					"ymd": "2020-06-27",
					"week": "星期六",
					"sunrise": "05:42",
					"sunset": "19:30",
					"aqi": 30,
					"fx": "西南风",
					"fl": "2级",
					"type": "小雨",
					"notice": "雨虽小，注意保暖别感冒"
				},
				{
					"date": "28",
					"high": "33",
					"low": "26",
					"ymd": "2020-06-28",
					"week": "星期日",
					"sunrise": "05:42",
					"sunset": "19:30",
					"aqi": 30,
					"fx": "西南风",
					"fl": "2级",
					"type": "小雨",
					"notice": "雨虽小，注意保暖别感冒"
				}]
				}
			}
		}
	}
</script>

<style>
	.el-col{
		text-align: right;
	}
</style>
