import Vue from 'vue'
import Vuex from 'vuex'
import axios from "axios";


Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    /**
     * Map 页面 属性
     */
    mul_sta: null,      // 多站点时请求接口数据
    query_line: null,    // 多站点返回数据对象
    single_sta: "1",// 单站点请求接口数据
    query_sta: null,     // 单站点返回数据对象

    query_calendar: [], // 日历日数据
    query_radar: [],  // 雷达图数据
    query_age: null,
    query_map: [],
    query_morning: [],
    query_night: [],
    query_time_morning: [],  // 组件Time中的相关数据
    query_time_night: [],
    query_time_sta: [],
    query_predict: [],
    query_user:"user190",
    enter_station:5235,
    out_station:5534,
    male_number:2674,
    female_number:2860,


  },
  // mutations用于操作数据(state)
  mutations: {
    GETSTATION(state, value) {
      console.log("mutations中的GETSTATION被调用了")
      // 单站点逻辑--获取单站点每日客流量
      // console.log(state)


      // radar 数据更新   从echarts实例中获取点击的时间信息 
      // for(var i=0;i<state.query_sta.data.length;i++){
      //   if(state.single_st)
      // }
    },
    // Pie 组件中站点种类统计，所属线路统计
    GETCATEGORY(state, value) {

    },
    GETLIST(value) {
      console.log("mutations中的GETLIST被调用了")
    }

  },
  // 准备 actions    用于响应组件中的动作
  actions: {
    /**
    * 
    * 接口检测----------------------------------------
       随机笑话接口
       响应方式：get
       请求参数：无
       相应内容：随机笑话
   ----------------------------------------------------
    */


    /**
     * 
     * @param {*} context  miniStore 
     * @param {*} b 
     */
    async getStation(context, value) {
      console.log("actions中的getStation被调用了", context, value, "this:", this)
      // 请求单站点参数
      var that = this     // 回调函数中this不一定为VUe对象！！！！
      context.state.single_sta = value.sta_number
      /**
       * 请求年龄结构 Trend 组件数据
       */
      await value._this.$http.get("age/" + context.state.single_sta).then(
        function (response) {
          console.log("This is ageRequire:", response.data);
          that.state.query_age = response.data;
          console.log("This is context:", context)
        },
        function (err) {
          console.log(err);
        }
      );
      console.log("This is new ageData:", this.state.query_age)

      /**
       * 请求日历图组件 数据
       */
      await value._this.$http.get("stationday/" + context.state.single_sta).then(
        function (response) {
          console.log("This is calendar require:", response.data);
          that.state.query_calendar = response.data;
          console.log("This is context:", context)
        },
        function (err) {
          console.log(err);
        }
      );
      await value._this.$http.get("station/" + context.state.single_sta).then(
        function (response) {
          console.log("This is enter_station require:", response.data);
          that.state.enter_station = response.data.enter_station;
          that.state.out_station = response.data.out_station;
          // console.log("This is context:", context)
        },
        function (err) {
          console.log(err);
        }
      );
      // context.dispatch('getDateNum', value)
      context.commit('GETSTATION', value)
    },


    // 搜索线路
    getLine() {
      console.log("searching a line...")

    },
    // 获取高峰站点的数据
    async getStaList(context, value) {

      console.log("action 中的 getList 被调用了,value值为", value)
      await value._this.$http("morningpeek").then(function (response) {
        console.log("早高峰", response.data)
        // context.state.query_morning = response.data
        context.state.query_time_sta = response.data.map(item => {
          return item.sta
        })
        context.state.query_time_morning = response.data.map(item => {
          return [
            item.id,
            item.sta,
            item.num_avg
          ]
        })
        context.state.query_morning = context.state.query_time_morning.sort(function (a, b) {
          return b[1] - a[1]
        }).slice(0, 10)
        console.log(context.state.query_morning)
      }, function (err) {
        console.log(err)
      })
      await value._this.$http("nightpeek").then(function (response) {
        console.log("晚高峰", response.data)
        context.state.query_time_night = response.data.map(item => {
          return [
            item.id,
            item.sta,
            item.num_avg
          ]
        })
        context.state.query_night = context.state.query_time_night.sort(function (a, b) {
          return b[1] - a[1]
        }).slice(0, 10)
        console.log(context.state.query_night)
      }, function (err) {
        console.log(err)
      })

      /**
       * 
       * 处理高峰数据
       */

      // console.log(this.state.query_map)
      context.commit("GETLIST", value)
    }

  },
  // 准备getters---用于将 state 中的数据进行加工  类似于计算属性 新的属性
  getters: {

  },
  modules: {
  }
})
