<template>
  <div class="bg-cover" @touchmove.prevent @click="hideListPanel">
  </div>
</template>

<script setup>
import { useStore } from 'vuex'

const store = useStore()

function hideListPanel() {
  store.commit('setListPanel', false)
}
</script>

<style lang="less" scoped>
.bg-cover {
  position: fixed;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  opacity: 1;
  z-index: 10;
  background-color: rgba(0,0,0,.5);
  transition: all .5s;
  &.hide {
    position: static;
    opacity: 0;
  }
}
</style>
