<template>
	<div>
		<div class="container">
			<el-row>
				<el-col :span="12" :offset="6">
					<div class="grid-content text-center title">天气查询</div>
				</el-col>
			</el-row>
			<el-row>
				
				<el-col :span="8"><div class="grid-content text-center">{{username}}您好</div></el-col>
				
				<el-col :span="8"><div class="grid-content text-center">今日您已查询{{times}}次</div></el-col>
				<el-col :span="8">
					<div class="grid-content text-center">
						<el-dropdown @command="navTo">
							<span class="el-dropdown-link">
								账号操作<i class="el-icon-arrow-down el-icon--right"></i>
							</span>
							<el-dropdown-menu slot="dropdown">
								<el-dropdown-item v-if="username == '游客'" command="/login">登录</el-dropdown-item>
								<el-dropdown-item v-if="username != '游客'" disabled>登录</el-dropdown-item>
								<el-dropdown-item divided command="/register">注册</el-dropdown-item>
								<el-dropdown-item divided v-if="username != '游客'" command="logout">退出</el-dropdown-item>
							</el-dropdown-menu>
						</el-dropdown>
					</div>
				</el-col>
			</el-row>
			<el-row>
				<el-col :span="8">
					<div class="grid-content text-center">选择地区</div>
				</el-col>
				<el-col :span="8">
					<div class="text-center">
						<city @getCityCode="getCityCode"></city>
					</div>
				</el-col>
				<el-col :span="8">
					<div class="text-center">
						<el-button type="success" size="small" @click="clickGetWeather">点击查询</el-button>
					</div>
				</el-col>
			</el-row>
			
			<!-- 天气echarts -->
			<el-row v-if="todayWeather != ''">
				<el-col :span="6"><div class="border-left text-center">日升：{{todayWeather.sunrise}} 日落：{{todayWeather.sunset}}</div></el-col>
				<el-col :span="3"><div class="border-left text-center">{{todayWeather.date}}</div></el-col>
				<el-col :span="3"><div class="border-left text-center">{{todayWeather.type}}</div></el-col>
				<el-col :span="3"><div class="border-left text-center">{{todayWeather.fx}}{{todayWeather.fl}}</div></el-col>
				<el-col :span="3"><div class="border-left text-center">{{todayWeather.week}}</div></el-col>
				<el-col :span="6"><div class="border-left text-center">{{todayWeather.notice}}</div></el-col>
			</el-row>
			<el-row v-if="weatherList.rows != ''">
				<el-col :span="24">
					<ve-line :data="weatherList" :settings="chartSettings"></ve-line>
				</el-col>
			</el-row>
			
			<!-- 最近搜索 -->
			<el-row v-if="recentSearchCitys != ''">
				<el-col :span="3">
					<el-link disabled type="info">为你推荐</el-link>&nbsp;
				</el-col>
				<el-col :span="2" v-for="(item,key) in recentSearchCitys" :key="key">
					<el-link :href="'https://travelsearch.fliggy.com/index.htm?keyword='+item" target="_blank">{{item}}</el-link>&nbsp;
				</el-col>
			</el-row>
		</div>
	</div>
</template>

<script>
	import city from '@/components/city.vue' ;
	import {getTimes} from '@/api/weather.js' ;
	import {getWeather} from '@/api/weather.js' ;
	import {getRecentCitys} from '@/api/weather.js' ;
	
	export default{
		components: {
		  city 
		},
		data(){
			return{
				// 用户名
				username:"" ,
				// 查询次数
				times:0,
				// 查询的天气id
				cityId:'' ,
				
				// echarts图表
				chartSettings : {
					labelMap: {
						high: '最高温' ,
						low: '最低温' ,
					}
				},
				// 未来n天天气列表
				weatherList:{
					columns:['date','high','low'],
					rows:[]
				},
				// 今日天气
				todayWeather:'',
				
				// 最近一周频繁搜索
				recentSearchCitys:[]
			}
		},
		created() {
			this.$msg("欢迎您")
		},
		mounted() {
			// 设置用户名
			this.username = localStorage.getItem("username") || "游客";
			
			// 获取查询次数
			this.getTimes();
			
			// 获取最近一周查询
			if(localStorage.getItem("accessToken")){
				this.getRecentCitys();
			}
		},
		methods:{
			//获取查询次数
			getTimes(){
				var that = this ;
				getTimes().then(res=>{
					if(res.data.status === 200){
						that.times = res.data.times ;
					}
				})
			},
			//根据城市code获取天气
			clickGetWeather(){
				var that = this ;
				that.$load("查询中...") ;
				if(that.cityId != ''){
					getWeather(that.cityId).then(res=>{
						var data = res.data ;
						if(data.status === 200){
							// 设置未来n天天气
							that.weatherList.rows = data.data.forecastList;
							// 设置今日天气
							that.todayWeather = data.data.today;
							// 更新查询次数
							that.getTimes() ;
						}else if(data.status === 403){
							that.$msg(data.msg,"error") ;
						}else {
							that.$msg("系统错误","error") ;
						}
						that.$loading().close();
					})
				}
			},
			//获取最近一周查询
			getRecentCitys(){
				var that = this ;
				getRecentCitys().then(res => {
					var data = res.data ;
					if(data.status === 200){
						that.recentSearchCitys = data.data ;
					}else if(data.status === 403){
						that.$msg(data.msg,"error");
					}else {
						that.$msg("系统错误","error");
					}
				});
			},

			//导航
			navTo(url){
				if("logout" == url){
					url = "/login";
					localStorage.removeItem("accessToken") ;
					localStorage.removeItem("username") ;
				}
				this.$router.push({path:url});
			},

			// 点击获取城市code
			getCityCode(code){
				this.cityId = code ;
			},
		}
	}
</script>

<style lang="scss">
	.container{
		width: 1200px;
		margin: 60px auto;
		border-radius: 5px;
		padding: 35px;
		background: #fff;
		border: 1px solid #eaeaea;
		box-shadow: 0 0 25px #cac6c6;
		.el-row {
			margin-bottom: 20px;
			border-bottom: 1px solid #eaeaea;
			&:last-child {
			  margin-bottom: 0;
			}
			.el-col{
				padding: 6px;
				font-weight: 100;
				.title{
					font-size: 26px;
				}
				.text-center{
					text-align: center;
				}
				.text-rigth{
					text-align: right;
				}
				.text-left{
					text-align: left;
				}
				.border-left{
					border-left: 1px solid #eaeaea;
				}
			}
		}
	}	
	.grid-content {
		border-radius: 4px;
		min-height: 36px;
	}
	.el-col{
		text-align: right;
	}
</style>
