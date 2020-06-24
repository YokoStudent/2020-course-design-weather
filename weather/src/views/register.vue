<template>
    <div>
		<div class="login-wrap">
			<el-form :model="userinfo" :rules="rules" ref="userinfo" label-width="100px">
				<div class="ms-title">注册</div>
				<el-form-item label="姓名" prop="username">
					<el-input v-model.trim="userinfo.username"></el-input>
				</el-form-item>
				<el-form-item label="密码" prop="password">
					<el-input type="password" v-model.trim="userinfo.password"></el-input>
				</el-form-item>
				<el-form-item label="确认密码" prop="password2">
					<el-input type="password" v-model.trim="userinfo.password2"></el-input>
				</el-form-item>
				 <el-form-item label="性别" prop="sex">
					<el-select v-model="userinfo.sex" placeholder="请选择性别">
					  <el-option label="男" value="1"></el-option>
					  <el-option label="女" value="0"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="年龄" prop="age">
				    <el-input v-model.number="userinfo.age"></el-input>
				</el-form-item>
				<div class="login-btn">
					<el-button type="primary" @click="submitForm('userinfo')">注册</el-button>
					<el-button type="text" @click="navTo('/login')">去登录</el-button>
				</div>
			</el-form>
		</div>
	</div>
</template>

<script>
	import {register} from "../api/user.js"
    export default {
        data(){
			// 验证年龄
			var checkAge = (rule, value, callback) => {
				if (!value) {
				  return callback(new Error('年龄不能为空'));
				}
				setTimeout(() => {
				  if (!Number.isInteger(value)) {
					callback(new Error('请输入数字值'));
				  } else {
					if (value < 0 || value>150) {
					  callback(new Error('输入合适的年龄'));
					} else {
					  callback();
					}
				  }
				}, 1000);
			};
			// 验证密码是否相同
			var validatePass2 = (rule, value, callback) => {
				if (value === '') {
				  callback(new Error('请再次输入密码'));
				} else if (value !== this.userinfo.password) {
				  callback(new Error('两次输入密码不一致!'));
				} else {
				  callback();
				}
			};
            return {
                userinfo: {
                    username: '',
                    password: '',
					password2:'',
					sex:'',
					age:'',
                },
                rules: {
                    username: [
                        {min:3, max:10, required: true, message: '不能为空且长度在3-10区间内', trigger: 'blur' }
                    ],
                    password: [
                        {min:6, max:10, required: true, message: '不能为空且长度在6-10区间内', trigger: 'blur' }
                    ],
					password2:[
						{validator: validatePass2, trigger: 'blur'}
					],
					sex:[
						{required:true, message: '请选择性别' ,trigger:'change'}
					],
					age:[
						{validator: checkAge, trigger: 'blur'}
					]
                }
            }
        },
		created() {
			// this.$msg('注册页面');
		},
        methods: {
            submitForm(formName) {
                const that = this;
                that.$refs[formName].validate((valid) => {
                    if (valid) {
                        that.$load("注册中....");
						register(that.userinfo).then(res=>{
							if(res.code === 200){
								that.$msg("注册成功")
								this.$router.replace({path:'/login'})
							}else{
								that.$msg("注册失败",'error')
							}
						}).catch(err=>{
							that.$msg("注册失败",'error')
						})
						that.$loading().close();
                    } else {
                        that.$msg("输入有误",'error');
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
        width: 420px;
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