<!--
针对sellerpage 这条路径而显示出来的
在这个组件中，通过组件注册的方式，要显示出Seller.vue这个组件
-->
<template>
  <div class="com-page">
     <Calendar></Calendar>
  </div>
</template>

<script>

import Calendar from '@/components/menus/Calendar.vue'
export default {
  components: {
    Calendar
  },
  data () {
    return {}
  },
  methods: {}
}
</script>

<style lang="less" scoped>
</style>
