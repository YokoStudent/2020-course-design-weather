<template>
	<div>
		<el-cascader
			:options="options"
			v-model="selectedOptions" 
			@change="handleChange"
			filterable
			size="small">
		</el-cascader>
	</div>
</template>

<script>
	import optionsJson from '../assets/json/city.json' ;
	export default{
		data(){
			return{
				selectedOptions: [],
				options:optionsJson,
			}
		},
		methods:{
			handleChange(value) {
				this.$emit("getCityCode",value[2])
			}
		}
	}
</script>

<style>
</style>
