<template>
	<div>
		<div class="container">
			<el-row>
				<el-col :span="12" :offset="6">
					<div class="grid-content text-center title">天气查询</div>
				</el-col>
			</el-row>
			<el-row>
				<el-col :span="8"><div class="grid-content text-center">Yoko您好</div></el-col>
				<el-col :span="8"><div class="grid-content text-center">今日您已查询2次</div></el-col>
				<el-col :span="8">
					<div class="grid-content text-center">
						<el-dropdown>
							<span class="el-dropdown-link">
								账号操作<i class="el-icon-arrow-down el-icon--right"></i>
							</span>
							<el-dropdown-menu slot="dropdown">
								<el-dropdown-item disabled>登录</el-dropdown-item>
								<el-dropdown-item divided>注册</el-dropdown-item>
								<el-dropdown-item divided>退出</el-dropdown-item>
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
						<el-button type="success" size="small" @click="getWeather">点击查询</el-button>
					</div>
				</el-col>
			</el-row>
			<el-row>
				<el-col :span="24">
					<showWeather></showWeather>
				</el-col>
			</el-row>
		</div>
	</div>
</template>

<script>
	import showWeather from '@/components/showWeather.vue' ;
	import city from '@/components/city.vue' ;
	export default{
		components: {
		  showWeather ,
		  city 
		},
		data(){
			return{
				
			}
		},
		methods:{
			// 获取城市code
			getCityCode(code){
				
			},
			//根据城市code获取天气
			getWeather(){
				
			}
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
			}
		}
	}	
	.grid-content {
		border-radius: 4px;
		min-height: 36px;
	}
</style>
