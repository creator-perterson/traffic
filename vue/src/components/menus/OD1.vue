<template>
  <div class="com-container">
    <div class="list" style="width: 100%; height: 450px">
      <div class="list-content">
        <h4>各类模型预测结果比较</h4>
        <table class="table">
          
          <tr>
            <th>序号</th>
            <th>模型名称</th>
            <th>MAE</th>
            <th>RMSE</th>
            <th>MAPE</th>
          </tr>
          <tr v-for="item in allData">
            <th>{{ item.id }}</th>
            <th>{{ item.model }}</th>
            <th>{{ item.mae }}</th>
            <th>{{ item.rmse }}</th>
            <th>{{ item.mape }}</th>
          </tr>
        </table>
      </div>
      <!-- <div class="small-chart">
        <table class="table">
          <tr>
            <th>序号</th>
            <th>线路id</th>
            <th>线路名称</th>
            <th>换乘量比例</th>
            <th>客流量比例</th>
          </tr>
          <tr v-for="item in allData">
            <th>{{ item.id }}</th>
            <th>{{ item.line }}</th>
            <th>{{ item.line_num }}</th>
            <th>
              {{ (item.transfer_volume / item.passenger_flow).toFixed(3) }}
            </th>
            <th>{{ (item.passenger_flow / 792178).toFixed(3) }}</th>
          </tr>
        </table>
      </div> -->
    </div>
  </div>
</template>

<script >
// import { number } from 'echarts'
export default {
  name: "OD1",
  data() {
    return {
      allData: [], // 从服务器中获取的所有数据
      titleFontSize: 0, // 知名标题的字体大小
    };
  },
  mounted() {
    this.getData();
  },

  methods: {
    async getData() {
      // 获取服务器的数据，对this.allData进行赋值之后，调用updateChart方法更新图表
      const that = this;
      await this.$http.get("finalscore").then(
        function (response) {
          // console.log(response.data);
          // that.query_age = response.data;
          that.allData = response.data;
        },
        function (err) {
          console.log(err);
        }
      );
      console.log(this.allData);
    },
  },
};
</script>
<style lang="less" scoped>
.list-content{
  text-align: center;
  color: white;
  .table{
    color: white;
  }
}
</style>

