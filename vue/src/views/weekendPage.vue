<template>
  <div class="com-page">
    
    <div class="com-chart" style="height: 800px; width: 100%; ">
      <Weekend></Weekend>
    </div>
  </div>
</template>

<script>
import Weekend from "@/components/menus/Weekend.vue";
import Weekend1 from "@/components/menus/Weekend1.vue";
import Weekend2 from "@/components/menus/Weekend2.vue";
export default {
  components: {
    Weekend,
    Weekend1,
    Weekend2,
  },
  data() {
    return {};
  },
  mounted() {},
  methods: {},
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
* {
  box-sizing: border-box;
}
.box {
  float: left;
  width: 50%;
  padding: 50px;
  height: 300px;
}
.clearfix::after {
  content: "";
  clear: both;
  display: table;
}
</style>