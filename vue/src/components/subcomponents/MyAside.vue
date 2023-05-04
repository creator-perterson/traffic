<template>
  <div class="layout-aside-container">
    <!-- 左侧边栏列表 -->
    <ul class="user-select-none menu">
      <li class="menu-item">
        <router-link to="/home/kind">站点聚类分析</router-link>
      </li>
      <li class="menu-item">
        <router-link to="/home/weekend">工作/节假日客流对比</router-link>
      </li>
      <li class="menu-item">
        <router-link to="/home/singleweek">单线/总客流分析</router-link>
      </li>
      <li class="menu-item">
        <router-link to="/home/time">客流高峰分析</router-link>
      </li>
      <li class="menu-item">
        <router-link to="/home/stachange">线路换乘分析</router-link>
      </li>

      <li class="menu-item" col>
        <router-link to="/home/usermark">用户画像</router-link>
      </li>
      <li class="menu-item">
        <router-link to="/home/od">客流预测</router-link>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "MyAside",
};
</script>

<style lang="less" scoped>
.layout-aside-container {
  width: 200px;
  height: 100%;
  border-right: 1px solid #eaeaea;
  // background: linear-gradient(#100c2a, #191838);
}

.menu {
  list-style-type: none;
  padding: 1%;
  .menu-item {
    margin-left: 2%;
    height: 100%;
    width: 100%;
    background: url(./infoBg.png) no-repeat center;
    background-size: 100%;
    border-color: transparent;
    // text-align: center;
    font-size: 1.3rem;
    margin-bottom: 2rem;
    line-height: 50px;
    font-weight: bold;
    font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
      Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
    &:hover {
      background-color: #efefef;
      cursor: pointer;
    }
    a {
      display: block;
      color: #ffffff;
      padding-left: 30px;
      &:hover {
        text-decoration: none;
      }
      
    }
  }
}
// .li{
//   color: aliceblue;
// }
// 设置路由高亮效果
.router-link-active {
  box-sizing: border-box;
  position: relative;
  // 伪元素实现路由高亮效果
  &::before {
    content: " ";
    display: block;
    width: 4px;
    height: 100%;
    position: absolute;
    left: 0;
    top: 0;
  }
}
</style>
