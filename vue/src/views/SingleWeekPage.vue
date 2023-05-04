<template>
  <div class="com-page">
    <div class="chart1"><SingleWeek></SingleWeek></div>
   
  </div>
</template>

<script>
import SingleWeek from "@/components/menus/SingleWeek.vue";
import SingleWeek1 from "@/components/menus/SingleWeek1.vue";
export default {
  components: {
    SingleWeek,
    SingleWeek1,
  },
  data() {
    return {
      //kind_data:[]
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
.div::-webkit-scrollbar {
  display: none;
}
.chart1 {
  width: 100%;
  height: 800px;
  // float: left;
}
// .chart2 {
//   float: right;
// }
</style>