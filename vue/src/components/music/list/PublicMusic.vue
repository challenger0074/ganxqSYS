<template>
  <div>

    <el-card>
      <el-table :data="musicList" style="width: 100%">
        <el-table-column prop="musicName" label="Music Name" />
        <el-table-column prop="uploadUser" label="Uploaded By" />
        <el-table-column label="Actions">
          <template #default="{ row }">
            <el-button  type="danger" @click="deleteMusic(row.id)">Delete</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- Pagination Component -->
      <el-pagination
          :current-page="queryInfo.pageNum"
          :page-size="queryInfo.pageSize"
          :page-sizes="[5, 10, 15, 20]"
          :size="size"
          :disabled="disabled"
          :background="background"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      />
    </el-card>

  </div>
</template>

<script setup lang="ts">
import { onMounted, reactive, ref } from 'vue';
import { Search } from '@element-plus/icons-vue';
import service from '@/api/request'; // Assuming you have axios or similar API service
import UpLoad from '@/components/music/UpLoad.vue';

interface UserForm {
  username: string;
}
interface QueryInfo {
  query: string;
  pageNum: number;
  pageSize: number;
}

interface Music {
  id: number;
  musicName: string;
  uploadUser: string;
}

const user = ref<UserForm>();
const input = ref('');
const dialogVisible = ref(false);
const musicList = ref<Music[]>([]);
const total = ref(0);
const queryInfo = reactive<QueryInfo>({
  query: '', // 查询参数
  pageNum: 1, // 页码
  pageSize: 5, // 每页最大数
});
const size = ref<'default' | 'small' | 'medium'>('default');
const disabled = ref(false);
const background = ref(true);
const img =new URL('./img/musicTu.png', import.meta.url)
const activeIndex = ref('1')
const handleSelect = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
// Fetch music list on component mount
const fetchMusicList = async () => {
  try {
    const response = await service.get(`/upload/music/list`, {
      params:  queryInfo, // Adjust query params
    });
    console.log('Response:', response);
    musicList.value = response.records; // Update the music list
  } catch (error) {
    console.error('Error fetching music list:', error);
  }
};
// Initialize the component, get user session, and set uploadUser
const init = async () => {
  console.log("init");
  const res = await service.get('/getSession');
  console.log("session:", res);
  user.value = res.data;
  console.log("user:", user.value?.username);
  fetchMusicList();
}
// Handle pagination change
const handleSizeChange = (newSize: number) => {
  queryInfo.pageSize = newSize;
  fetchMusicList();
};

const handleCurrentChange = (newPage: number) => {
  queryInfo.pageNum = newPage;
  fetchMusicList();
};


// Handle delete music (implement backend delete logic)
const deleteMusic = (id: number) => {
  service.delete(`/upload/music/${id}`).then(() => {
    fetchMusicList(); // Re-fetch music list after deletion
  }).catch(err => {
    console.error('Error deleting music:', err);
  });
};

// Fetch music list on mount
onMounted(() => {
  init();
});
</script>

<style scoped>
.el-breadcrumb {
  margin-bottom: 15px;
  font-size: 12px;
}
</style>
