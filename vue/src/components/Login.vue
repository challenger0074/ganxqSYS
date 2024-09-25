<template>
    <div class="box">
        <div class="login-container">
      <h2>登录</h2>
      <form @submit.prevent="handleLogin">
        <input type="text" v-model="username" placeholder="用户名" />
        <input type="password" v-model="password" placeholder="密码" />
        <button type="submit" @click="login">登录</button>
      </form>
    </div>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  import {useLoginState} from '@/stores/state.ts'
  const username = ref('');
  const password = ref('');
  
  const login=useLoginState().change
  const handleLogin = async () => {
    // 在此处添加登录逻辑，例如发送请求到服务器
    console.log('用户名:', username.value, '密码:', password.value);
  };
  </script>
  
  <style scoped>
  
  .login-container {
    width: 300px;
  
   position: absolute;
   top: 50%;
   left: 50%;
   transform: translate(-50%, -50%);
   padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    background-color: #f8f9fa;
  }
  
  h2 {
    margin-bottom: 20px;
  }
  
  input {
    width: 100%;
    padding: 10px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 3px;
  }
  
  button {
    width: 100%;
    padding: 10px;
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 3px;
    cursor: pointer;
  }
  
 
button:hover {
  background-color: #0056b3;
}
</style>