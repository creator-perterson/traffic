<template>
  <div class="com-page">
    <div class="com-chart" style="height: 850px; width: 100%">
      <!-- 下拉复选框 -->
    <!-- <select name="" id="" onchange="changeUser()">
      <option value="user1">user1</option>
      <option value="user16">user16</option>
      <option value="user48">user48</option>
      <option value="user190">user190</option>
      <option value="user625">user625</option>
      <option value="user1570">user1570</option>
    </select> -->
      <UserMark></UserMark> 
    </div>
    <!-- <div class="user-text">

    </div> -->
  </div>

</template>

<script>
import UserMark from "@/components/menus/UserMark.vue";
import UserMark1 from "@/components/menus/UserMark1.vue";

export default {
  // name: "TrafficKindpage",
  components: {
    UserMark,
    UserMark1,
  },

  data() {
    return {};
  },

  methods: {
    changeUser(){
      var vs = $('select option:selected').val();
      console.log(vs)
    }
  },
};
</script>

<style lang="less" scoped>
.com-page {
  // .com-chart {
  //   // background-color: green;
  //   // float: left;
  // }
  // .user-text {
  //   height: 850px;
  //   width: 50%;
  //   background-color: red;
  //   float: right;
  // }
}
</style>