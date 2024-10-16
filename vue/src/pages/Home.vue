<template>
  <div>
    <el-container>
      <el-header>
        <div class="logo">
          <img src="../assets/logo.png" /> vue3项目
        </div>
      </el-header>
      <el-container>
        <el-aside width="200px">
          <el-menu
              active-text-color="#ffd04b"
              background-color="#545c64"
              class="el-menu-vertical-demo"
              default-active="1"
              text-color="#fff"
              @open="handleOpen"
              @close="handleClose"
          >
            <el-sub-menu :index="item.id" v-for="item in menuList" :key="item.id">
              <template #title>
                <el-icon><location /></el-icon>
                <span>{{ item.title }}</span>
              </template>
              <el-menu-item-group title="Group One">
                <el-menu-item index="1-1">Item One</el-menu-item>
                <el-menu-item index="1-2">Item Two</el-menu-item>
              </el-menu-item-group>
              <el-menu-item-group title="Group Two">
                <el-menu-item index="1-3">Item Three</el-menu-item>
              </el-menu-item-group>

              <el-menu-item
                  v-for="it in item.slist"
                  :key="it.id"
                  :index="`${item.id}-${it.id}`"  
              >
              <template #title>
                <el-icon><location /></el-icon>
                <span>{{ it.title }}</span>
              </template>
              </el-menu-item>

              <el-sub-menu index="1-4">
                <template #title>Item Four</template>
                <el-menu-item index="1-4-1">Item One</el-menu-item>
              </el-sub-menu>
            </el-sub-menu>
            <el-menu-item index="2">
              <el-icon><icon-menu /></el-icon>
              <span>Navigator Two</span>
            </el-menu-item>
            <el-menu-item index="3" disabled>
              <el-icon><document /></el-icon>
              <span>Navigator Three</span>
            </el-menu-item>
            <el-menu-item index="4">
              <el-icon><setting /></el-icon>
              <span>Navigator Four</span>
            </el-menu-item>
          </el-menu>
        </el-aside>
        <el-main>Main</el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script lang="ts" setup>
import { ref, onMounted, inject } from 'vue'
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
} from '@element-plus/icons-vue'
import { useRouter } from 'vue-router';
import service from "@/api/request";

const menuList = ref([]);
const router = useRouter();
const message = inject("$toast");

onMounted(() => {
  getMenuList();
  message.show("成功加载", 20000);
});

const handleOpen = (key: string, keyPath: string[]) => {
  console.log(key, keyPath);
}

const handleClose = (key: string, keyPath: string[]) => {
  console.log(key, keyPath);
}

const logout = () => {
  window.sessionStorage.clear();
  router.push('/login');
}

const getMenuList = async () => {
  const res = await service.get('/menu/find');
  console.log(res);
  menuList.value = res.menus;
  console.log("menulist", menuList.value);
  console.log("menulist slist", menuList.value[6].slist);
}
</script>

<style>
.logo img {
  width: 20px;
  height: 20px;
}
</style>
