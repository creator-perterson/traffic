<template>
  <div class="com-page">
    <div class="chart1">
      <OD></OD>
    </div>

    <div class="list">
      <OD1></OD1>
    </div>

    <!-- <div class="test">
        获取数据{{kind_data}}
       </div> -->
  </div>
</template>

<script>
import OD from "@/components/menus/OD.vue";
import OD1 from "@/components/menus/OD1.vue";
export default {
  components: {
    OD,
    OD1,
  },

  data() {
    return {
      kind_data: [],
      allData: null,
    };
  },

  mounted() {
    this.getData();
  },

  methods: {
    async getData() {
      const that = this;
      await this.$http.get("model").then(
        function (response) {
          console.log(response.data);
          // that.query_age = response.data;
          that.allData = response.data;
        },
        function (err) {
          console.log(err);
        }
      );
    },
  },
};
</script>

<style lang="less" scoped>
.com-page {
  overflow-x: hidden;
  overflow-y: scroll;
}
.div::-webkit-scrollbar {
  display: none;
}
.chart1 {
  // float: top;
  height: 850px;
  width: 70%;
  float: left;
}

.list {
padding: 20px;
  // background-color: green;
  height: 850px;
  width: 30%;
  float:right;
}
</style>