<template>
  <RouterLink to="/home" active-class="xiangying">首页</RouterLink>
  <RouterLink :to="{path:'/news'}" active-class="xiangying">新闻</RouterLink>
  <RouterLink :to="{name:'about1'}" active-class="xiangying">关于</RouterLink>
  <RouterLink :to="{path:'/reader'}" active-class="xiangying">书城</RouterLink>
  <div active-class="xiangying">
    <RouterLink :to="{name:'login'}" active-class="xiangying">登录</RouterLink>
    <span>/</span>
    <RouterLink v-show="state" :to="{name:'login'}" active-class="xiangying">注册</RouterLink>
  </div>
</template>

<script setup>
import {ref} from 'vue';
import {useLoginState} from '@/stores/state.ts'
import {storeToRefs} from 'pinia'
// 您的组件逻辑
const state = storeToRefs(useLoginState()).islogin

</script>

<style scoped>
/* 您的组件样式 */
.xiangying {
  color: #007bff;
  font-weight: bold;
}

</style>
