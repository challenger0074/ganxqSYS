<script setup lang="ts">

</script>

<template>
  <RouterLink :to="{path:'/reader/home'}" active-class="xiangying">书城</RouterLink>
  <RouterView></RouterView>
</template>

<style scoped>
.xiangying {
  color: #007bff;
  font-weight: bold;
}

</style>
