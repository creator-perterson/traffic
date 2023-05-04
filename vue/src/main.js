// 导入路由模块,默认加载router的index.js
import router from "@/router";
import axios from "axios";
import * as d3 from "d3";
import * as echarts from "echarts";
import Vue from "vue";
import App from "./App.vue";
// Element-ui引入
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
// 导入样式
import "./assets/css/bootstrap.css";
// 引入全局的样式文件
import "./assets/css/global.less";
// 引入字体的文件
import "./assets/font/iconfont.css";
import "./index.css";
import store from "./store";
import jquery from "jquery";

// 此处为拖拽悬浮组件引入的库
//引入组件库
import jvuewheel from '@jyeontu/jvuewheel'
//引入样式
import '@jyeontu/jvuewheel/lib/jvuewhell.css'
Vue.use(jvuewheel);
// import VScroll from './components/vscroll'
// Vue.use(VScroll);

Vue.prototype.$ = jquery
// 挂载 axios 到Vue 的原型对象上
Vue.prototype.$http = axios;

// 将全局的echarts对象挂载到Vue的原型对象上
Vue.prototype.$echarts = echarts;

axios.defaults.baseURL = 'api'  //关键代码


Vue.prototype.$d3 = d3;

Vue.config.productionTip = false;

new Vue({
  render: (h) => h(App),
  router,
  store,
}).$mount("#app");


