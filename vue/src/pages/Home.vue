<template>
    <div class="app">
      
      <!--导航区-->
      <div class="navigate">
       home
      </div>
      <!--展示区-->
      <div class="main-content"></div>
    </div>
    </template>
    
    <script lang="ts" setup>
      import {ref,watch,watchEffect,computed,onMounted} from 'vue'
      import { useRouter } from 'vue-router';
       const router= useRouter()
      onMounted(
        ()=>{
          setTimeout(()=>{
            router.push('/news')
          },3000)
        }
      )
      
    </script>
    <style></style>