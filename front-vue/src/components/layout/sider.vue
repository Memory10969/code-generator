<template>
    <div id='sider'>
        <div class="logo" >
            <span> 客服工单</span>
        </div>
        <a-menu theme="dark" mode="inline" :default-selected-keys="['1']">
            <a-menu-item @click="changeContent('/class')" key="1">
                <a-icon type="user" />
                <span>班级</span>
            </a-menu-item>
            <a-menu-item @click="changeContent('/student')" key="2">
                <a-icon type="video-camera" />
                <span>学生</span>
            </a-menu-item>
            <a-menu-item key="3">
                <a-icon type="upload" />
                <span>nav 3</span>
            </a-menu-item>
        </a-menu>
    </div>
</template>

<script>
export default {
    name: 'Sider',
    data(){
      return{
        
      }
    },
    methods:{
        changeContent(path){
          this.$router.push(path)
        }
    }
}
</script>

<style scoped>
.logo{
    text-align: center;
    font-size: 16px;
    font-weight: bold;
    color:aliceblue;
    line-height: 200%;
    overflow: hidden;
}
</style>