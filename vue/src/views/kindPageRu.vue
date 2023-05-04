<template>
    <div class="com-page">
        <div class="chart" style="height: 700px; width: 50%">
            <KindRu></KindRu>
        </div>
        <div class="chart1" style="width: 50%; height: 700px ;">

        </div>

       <!-- <div class="test">
        获取数据{{kind_data}}
       </div> -->
    </div>
</template>

<script>
import KindRu from '@/components/menus/KindRu.vue';

export default {
    name: 'TrafficKindpage',
    components:{
        KindRu },

    data() {
        return {
            kind_data:[]
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
.chart {
   //
  float: left;
}

</style>