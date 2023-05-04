<template>
  <div class="com-page">
    <div class="kind-result" style="height: 350px; width: 40%">
      <div class="kind-table">
        <div class="table-head">
          <h4>┃ 各类型站点高峰特征 ┃</h4>
        </div>
        <table class="table">
          <tr>
            <th>颜色</th>
            <th>类型</th>
            <th>早高峰</th>
            <th>晚高峰</th>
            <th>进站高峰对比</th>
            <th>出站高峰对比</th>
          </tr>
          <tr>
            <th>
              <button style="background-color: rgb(10, 129, 107)">/</button>
            </th>
            <th>居住导向型</th>
            <th>进站客流为主</th>
            <th>出站客流为主</th>
            <th>早>>晚</th>
            <th>早<<晚</th>
          </tr>
          <tr>
            <th>
              <button style="background-color: rgb(202, 158, 177)">/</button>
            </th>
            <th>就业导向型</th>
            <th>出站客流为主</th>
            <th>进站客流为主</th>
            <th>早<<晚</th>
            <th>早>>晚</th>
          </tr>
          <tr>
            <th>
              <button style="background-color: rgb(170, 94, 57)">/</button>
            </th>
            <th>职住交错型</th>
            <th>进、出站明显</th>
            <th>进、出站明显</th>
            <th>早～晚</th>
            <th>早～晚</th>
          </tr>
          <!-- <tr>
            <th>景区型</th>
            <th>出站客流为主</th>
            <th>无</th>
            <th>仅1个高峰</th>
            <th>仅1个高峰</th>
          </tr> -->
          <tr>
            <th>
              <button style="background-color: rgb(199, 198, 203)">/</button>
            </th>
            <th>交通枢纽型</th>
            <th>全日多高峰</th>
            <th>全日多高峰</th>
            <th>多峰值接近</th>
            <th>多峰值接近</th>
          </tr>
          <tr>
            <th>
              <button style="background-color: rgb(63, 145, 175)">/</button>
            </th>
            <th>交错偏居住型</th>
            <th>进站客流为主</th>
            <th>出站客流为主</th>
            <th>早>晚</th>
            <th>早<晚</th>
          </tr>
          <tr>
            <th><button id="b2">/</button></th>
            <th>交错偏就业型</th>
            <th>出站客流为主</th>
            <th>进站客流为主</th>
            <th>早<晚</th>
            <th>早>晚</th>
          </tr>
        </table>
      </div>
      <div class="kind-chart" style="height: 350px; width: 100%">
        <KindRu></KindRu>
      </div>
    </div>
    <div class="kind-prossesing" style="height: 700px; width: 60%">
      <Month></Month>
    </div>
  </div>
</template>

<script>
import Kind from "@/components/menus/Kind.vue";
import Month from "@/components/menus/Month.vue";
import KindRu from "@/components/menus/KindRu.vue";

export default {
  name: "TrafficKindpage",
  components: {
    Kind,
    Month,
    KindRu,
  },

  data() {
    return {
      kind_data: [],
    };
  },

  mounted() {
    this.getData();
  },

  methods: {
    async getData() {},
  },
};
</script>

<style lang="less" scoped>
.com-page {
  overflow-x: hidden;
  overflow-y: scroll;
}
// .div::-webkit-scrollbar {
//   display: none;
// }
.kind-result {
  float: right;
  // background-color: red;
  .kind-table {
    padding: 100px;
    text-align: center;
    color: white;
    height: 500px;
    width: 100%;
    .table {
      color: white;
      .button {
        width: 10px;
        height: 20px;
      }
    }
  }
  // .kind-chart {
  //   float: left;
  //   background-color: rgb(0, 255, 85);
  // }
}

.kind-processing {
  float: left;
}
#b2 {
  background-color: rgb(171, 76, 91);
}
</style>