<template>
    <el-container class="home-container">
      <el-header style="">
        <el-page-header :icon="null" >
          <template #content>
            <div class="flex items-center">
              <el-avatar
                  :size="32"
                  class="mr-3"
                  :src="img"
              />
              <span class="text-large font-600 mr-3"> Title </span>
              <span class="text-sm mr-2" style="color: var(--el-text-color-regular)">
          Sub title
        </span>
              <el-tag>Default</el-tag>
            </div>
          </template>
          <template #extra>
            <div class="flex items-center">
              <el-button>Print</el-button>
              <el-button type="primary" class="ml-2">Edit</el-button>
            </div>
          </template>
        </el-page-header>
      </el-header>
      <el-container>
        <el-aside width="200px">
          <el-menu
              active-text-color="#ffd04b"
              background-color="#545c64"
              class="el-menu-vertical-demo"
              :default-active="activePath"
              text-color="#fff"
              @open="handleOpen"
              @close="handleClose"
              :router="true"
          >
            <el-sub-menu :index="item.path+''" v-for="item in menuList" :key="item.id">
              <template #title>
                <el-icon><location /></el-icon>
                <span>{{ item.title }}</span>
              </template>
              <el-menu-item-group title="Group One">
                <el-menu-item :index="`${item.id}-1`">Item One</el-menu-item>
                <el-menu-item :index="`${item.id}-2`">Item Two</el-menu-item>
              </el-menu-item-group>
              <el-menu-item-group title="Group Two">
                <el-menu-item :index="`${item.id}-3`">Item Three</el-menu-item>
              </el-menu-item-group>

              <el-menu-item
                  v-for="it in item.slist"
                  :key="it.id"
                  :index="'/home'+it.path"
                  @click="saveState(it.path)"
              >
                <template #title>
                  <el-icon><location /></el-icon>
                  <span>{{ it.title }}</span>
                </template>
              </el-menu-item>

              <el-sub-menu :index="`${item.id}-4`">
                <template #title>Item Four</template>
                <el-menu-item :index="`${item.id}-4-1`">Item One</el-menu-item>
              </el-sub-menu>
            </el-sub-menu>
            <el-menu-item :index="String(20)">
              <el-icon><icon-menu /></el-icon>
              <span>Navigator Two</span>
            </el-menu-item>
            <el-menu-item :index="String(30)" disabled>
              <el-icon><document /></el-icon>
              <span>Navigator Three</span>
            </el-menu-item>
            <el-menu-item :index="String(40)">
              <el-icon><setting /></el-icon>
              <span>Navigator Four</span>
            </el-menu-item>
          </el-menu>
        </el-aside>
        <el-main><router-view></router-view></el-main>
      </el-container>
    </el-container>
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

const img = new URL(`@/assets/img.png`, import.meta.url).href
const activePath = ref('/welcome');
const menuList = ref([]);
const router = useRouter();
const message = inject("$toast");
const saveState = (path: string) => {
  activePath.value = path;
  window.sessionStorage.setItem('activePath', path)
}
onMounted(() => {
  getMenuList();
  message.show("成功加载", 2000);
  activePath.value=window.sessionStorage.getItem('activePath')
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
  console.log("menulist[6] slist", menuList.value[6].slist);
  console.log("menulist[6] slist id", menuList.value[6].id);
}
</script>

<style scoped>
.el-header{
  background-color:#373d41 ;
  color:#fff;
}
.el-main{
  background-color: #eaedf1;
}
.home-container{
  height: 100%;
}
.el-aside {
  background-color: #333744;
  .el-menu{
    border-right: none;/*// 对其右边框 */
  }
}
</style>
