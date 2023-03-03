const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave:false, //关闭语法检查
  devServer:{
    proxy:{
      '/api': {
        target: 'http://localhost:8088/',
        ws: true,
        changeOrigin: true,
        pathRewrite: {
          '^/api': ''//重写,
        }
      },
    }
  }
})
