<template>
    <div class="com-page">
      <div class="com-chart"> 
      <UserMark1></UserMark1>
      </div>
  </div>
</template>

<script>
import UserMark1 from "@/components/menus/UserMark1.vue";
export default {
  components: {
    usermark: UserMark1,
  },
  data() {
    return {
      //  data_line: [],
      //   data_sta: [],
    };
  },
  //  mounted() {
  //   this.getListData();

  // },
  methods: {
    //   getListData() {
    //     this.data_line = this.$store.state.query_time_morning.map((item) => {
    //       return [item[1], item[2]];
    //     });
    //     this.data_sta = this.$store.state.query_time_night.map((item) => {
    //       return [item[1], item[2]];
    //     });
    //     console.log(this.data_line)
    //  }
  },
};
</script>

<style lang="less" scoped>
</style>