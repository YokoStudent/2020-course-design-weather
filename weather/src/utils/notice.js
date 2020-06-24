import Vue from 'vue'
import { Message } from 'element-ui';
import { Loading } from 'element-ui';

// message:提示消息
// type:主题success/warning/info/error
// duration:显示时间, 毫秒。设为 0 则不会自动关闭	
// showClose:是否显示关闭按钮
const msg = (message="",type="",duration=2000,showClose="true") =>{
	Message({
		message,
		type,
		duration,
		showClose
	})
}

const loading = (text="加载中...",lock=true,spinner="el-icon-loading",background="rgba(0, 0, 0, 0.7)") => {
	const loadingInstance = Loading.service({
	  lock ,
	  text ,
	  spinner ,
	  background
	});
}

Vue.prototype.$msg = msg;
Vue.prototype.$load = loading ;