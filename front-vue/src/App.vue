<template>
    <div id="app">
      <Home/>
    </div>
</template>

<script>
import zh_CN from 'ant-design-vue/lib/locale-provider/zh_CN'
import Home from './components/layout/home'
export default {
  name: 'app',
  data() {
    return {
      zh_CN,
    }
  },
  components: {
    Home
  }
}
</script>

<style scoped>
#app{
  width:100%;
  height:100%;
  overflow: scroll;
}
</style>
