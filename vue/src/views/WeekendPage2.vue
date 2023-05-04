<template>
    <div class="com-page">
        <!-- <div class="chart" style="height: 700px; width: 50%">
            <Weekend1></Weekend1>
        </div> -->
       <!-- <div class="test">
        获取数据{{kind_data}}
       </div> -->
    </div>
</template>

<script>
import Weekend2 from '@/components/menus/Weekend2.vue';

export default {
    name: 'TrafficKindpage',
    components:{
        Weekend2 },

    data() {
        return {
            //kind_data:[]
        };
    },

    mounted() {
        this.getData()
    },

    methods: {
    
     async  getData(){
         
     }
    },
};
</script>

<style lang="less" scoped>
// .chart {
//    //
//   float: left;
// }

</style>