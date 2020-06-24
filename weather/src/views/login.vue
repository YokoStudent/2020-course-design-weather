<template>
    <div>
		<div class="login-wrap">
			<el-form :model="userinfo" :rules="rules" ref="userinfo" label-width="0px">
			<div class="ms-title">登录</div>
				<el-form-item prop="username">
					<el-input v-model="userinfo.username" placeholder="username"></el-input>
				</el-form-item>
				<el-form-item prop="password">
					<el-input type="password" placeholder="password" v-model="userinfo.password" @keyup.enter.native="submitForm('userinfo')"></el-input>
				</el-form-item>
				<div class="login-btn">
					<el-button type="primary" @click="submitForm('userinfo')">登录</el-button>
					<el-button type="text" @click="navTo('/register')">去注册</el-button>
				</div>
			</el-form>
		</div>
	</div>
</template>

<script>
	import {login} from '../api/user.js'
    export default {
        data: function(){
            return {
                userinfo: {
                    username: '',
                    password: ''
                },
                rules: {
                    username: [
                        {min:3, max:10, required: true, message: '不能为空且长度在3-10区间内', trigger: 'blur' }
                    ],
                    password: [
                        {min:6, max:10, required: true, message: '不能为空且长度在6-10区间内', trigger: 'blur' }
                    ]
                }
            }
        },
		created() {
			this.$msg('登录页面');
		},
        methods: {
            submitForm(formName) {
                const that = this;
                that.$refs[formName].validate((valid) => {
                    if (valid) {
                        that.$load("登录中...") ;
						login(that.userinfo).then(res=>{
							if(res.code === 200){
								that.$msg("登录成功") ;
								that.$router.replace({"path":"/"})
							}else {
								that.$msg("登录失败","error") ;
							}
						}).catch(err=>{
							that.$msg("登录失败","error") ;
						})
						that.$loading().close();
                    } else {
                        this.$msg('输入有误','error');
                        return false;
                    }
                });
            },
			
			
			//去到注册页面
			navTo(url){
				this.$router.push({path:url})
			}
        }
    }
</script>

<style scoped>
    .login-wrap{
        width:100%;
        height:40%;
        -webkit-border-radius: 5px;
        border-radius: 5px;
        -moz-border-radius: 5px;
        background-clip: padding-box;
        margin: 180px auto;
        width: 350px;
        padding: 35px 35px 15px 35px;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
    }
    .ms-title{
        margin: -30px auto 40px auto;
        text-align: center;
        font-size:30px;
        color: #505458;
    }
    .login-btn{
        text-align: center;
    }
    .login-btn button{
        width:48%;
        height:36px;
    }
</style>