<template>
  <div class="main">
    <div class="chart" style="width: 50%; height: 700px">
      <StaChange></StaChange>
    </div>
    <div class="list" style="width: 50%; height: 700px">
      <div class="list-content">
        <table class="table">
          <tr>
            <th>序号</th>
            <th>线路id</th>
            <th>线路名称</th>
            <th>换乘量</th>
            <th>客流量</th>
          </tr>
          <tr v-for="item in allData">
            <th>{{ item.id }}</th>
            <th>{{ item.line }}</th>
            <th>{{ item.line_num }}</th>
            <th>{{ item.transfer_volume }}</th>
            <th>{{ item.passenger_flow }}</th>
          </tr>
        </table>
      </div>
      <div class="small-chart">
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
      </div>
    </div>
  </div>
</template>

<script>
import StaChange from "@/components/menus/StaChange.vue";
import { HappyScroll } from "vue-happy-scroll";
import "vue-happy-scroll/docs/happy-scroll.css";

export default {
  name: "TrafficStachangepage",
  components: {
    StaChange,
    "happy-scroll": HappyScroll,
  },
  data() {
    return {
      allData: [],
    };
  },

  mounted() {
    this.getListData();
    this.getListData1();
  },

  methods: {
    async getListData() {
      const that = this;
      await this.$http.get("transferline").then(
        function (response) {
          that.allData = response.data;
        },
        function (err) {
          console.log(err);
        }
      );
      console.log("已获取到list数据：", this.allData);
    },
  },
};
</script>

<style lang="less" scoped>
.main {
  margin: 0 auto;
  overflow-y: auto;
}

.chart {
  //background-color: rgb(104, 190, 230);
  float: left;
}
.list {
  line-height: 20px;
  text-align: center;
  overflow-y: scroll;

  float: right;
  .list-content {
    //height: 30%;
    width: 570px;
    height: 50%;
  }
  .small-chart {
    margin-top: 0;
    height: 50%;
    width: 570px;
    float: left;
    //
    //     text-align: center;
    //     overflow-y: scroll;
  }
}
.table {
  font: italic;
  letter-spacing: 4px;
  text-align: center;
  float: right;
  font-size: 8px;
  color: aliceblue;
  // overflow-y: scroll;
}
</style>