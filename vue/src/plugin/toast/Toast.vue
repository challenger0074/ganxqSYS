<template>
  <div>
    <div class="toast" v-show="isShow">
      <p>{{ message }}</p>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const message = ref('');
const isShow = ref(false);

const show = (msg = '默认文本', duration = 1000) => {
  message.value = msg;
  isShow.value = true;

  // 指定时间后弹框消失
  setTimeout(() => {
    isShow.value = false;
  }, duration);
};
</script>

<style lang="less" scoped>
.toast {
  position: fixed;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  width: 140px;
  height: 40px;
  background-color: black;
  opacity: 0.7;
  border-radius: 4px;
  text-align: center;
  line-height: 40px;
  color: white;
  z-index: 100;
}
</style>
