<template>

  <div class="app">

    <!--导航区-->
    <div class="navigate">
     <Navigate/>
    </div>
    <!--展示区-->
    <div class="main-content">
     <RouterView></RouterView>
    </div>

  </div>
  </template>

  <script lang="ts" setup>
    import {ref,watch,watchEffect,computed} from 'vue'
    import { RouterView,RouterLink } from 'vue-router';
    import {useLoginState} from'./stores/state.ts'
    import Navigate from './components/Navigate.vue';
    const state=useLoginState()

    console.log(useLoginState().islogin)

  </script>
 <style scoped>
 .app {


 }

 .navigate {
   background-color: #f8f9fa;
   padding: 10px;
   display: flex;
   justify-content: space-around;
 }


 .main-content {
  flex: 1;

 }

 </style>
