<template>
    <div id="header">
        <a-layout-header style="background: #fff; padding: 0">
            <a-icon class="trigger" :type="collapsed ? 'menu-unfold' : 'menu-fold'"
                @click="toggle" />
        </a-layout-header>
    </div>
</template>

<script>
export default {
    name:'Header',
    data(){
       return {

       }
    },
    methods:{
        toggle(){
            this.$emit("toggle")
        }
    },
    props:['collapsed']
}
</script>

<style>

</style>