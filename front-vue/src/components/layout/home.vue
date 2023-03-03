<template>
    <a-layout id="home">
        <a-layout-sider v-model="collapsed" :trigger="null" collapsible>
            <Sider />
        </a-layout-sider>
        <a-layout>
            <Header @toggle="toggle"/>
            <a-layout-content :style="{ margin: '24px 16px', padding: '24px', background: '#fff', minHeight: '280px' }">
                <router-view></router-view>
            </a-layout-content>
        </a-layout>
    </a-layout>
</template>
<script>
import Header from './header.vue'
import Sider from './sider.vue'

export default {
    name:"Home",
    data() {
        return {
            collapsed: false,
        };
    },
    mounted(){
    
    },
    methods:{
            toggle(){
                this.collapsed = !this.collapsed
            }
    },
    components:{
        Header,
        Sider,
    }
};
</script>
<style>
#home{
    width:100%;
    height:100%;
    overflow: scroll;
}
#home .trigger {
    font-size: 18px;
    line-height: 64px;
    padding: 0 24px;
    cursor: pointer;
    transition: color 0.3s;
}

#home .trigger:hover {
    color: #1890ff;
}

#home .logo {
    height: 32px;
    background: rgba(255, 255, 255, 0.2);
    margin: 16px;
}
</style>
  