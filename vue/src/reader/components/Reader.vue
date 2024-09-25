<template>
  <div id="reader">
    <TopNav />
    <div class="read-container" :bg="bg_color" :night="bg_night" ref="fz_size">
      <h4>{{ title }}</h4>
      <div class="chapterContent" v-if="!loading">
        <p v-for="(c, i) in content" :key="i">{{ c }}</p>
      </div>
      <div class="btn-bar" v-if="!loading">
        <ul class="btn-tab">
          <li class="prev-btn" @click="prevChapter">上一章</li>
          <li class="next-btn" @click="nextChapter">下一章</li>
        </ul>
      </div>
    </div>
    <div class="page-up" @click="pageUp()"></div>
    <div class="click-mask" @click="clickBar"></div>
    <div class="page-down" @click="pageDown()"></div>
    <div class="top-nav-pannel-bk font-container" v-if="font_panel"></div>
    <FontNav />
    <BottomNav />
    <Cover :class="{ hide: !list_panel }" />
    <ListPanel :class="{ show: list_panel }" :bookId="$route.params.id" />
    <Loading v-if="loading" />
  </div>
</template>

<script setup>
import { ref, computed, watch, onMounted } from 'vue';
import { useStore } from 'vuex';
import axios from 'axios';
import localEvent from '../store/local';
import TopNav from './TopNav/TopNav.vue';
import BottomNav from './BottomNav/BottomNav.vue';
import FontNav from './FontNav/FontNav.vue';
import ListPanel from './ListPanel/ListPanel.vue';
import Cover from './Cover.vue';
import Loading from './Loading/Loading.vue';

const store = useStore();

const bar = ref(false);
const title = ref('');
const content = ref([]);
const loading = ref(false);
const booksReadInfo = ref({});
const fz_size = computed(() => store.state.fz_size);
const bg_color = computed(() => store.state.bg_color);
const bg_night = computed(() => store.state.bg_night);
const list_panel = computed(() => store.state.list_panel);
const curChapter = computed(() => store.state.curChapter);

const getData = async (id, chapter) => {
  loading.value = true;
  try {
    const { data } = await axios.get(`${store.state.api}/book?book=${id}&id=${chapter}`);
    title.value = data.title;
    content.value = data.content.split('-');
  } finally {
    loading.value = false;
  }
};

const saveBooksInfo = () => {
  const id = store.state.id; // 假设ID存储在 Vuex 中
  booksReadInfo.value[id] = {
    book: id,
    chapter: curChapter.value,
  };
  localEvent.StorageSetter('bookreaderinfo', booksReadInfo.value);
};

const prevChapter = () => {
  store.dispatch('prevChapter');
  saveBooksInfo();
  setTimeout(() => { document.body.scrollTop = 0; }, 300);
};

const nextChapter = () => {
  store.dispatch('nextChapter', 50);
  saveBooksInfo();
  setTimeout(() => { document.body.scrollTop = 0; }, 300);
};

onMounted(() => {
  const id = store.state.id; // 假设ID存储在 Vuex 中
  const localBookReaderInfo = localEvent.StorageGetter('bookreaderinfo');
  if (localBookReaderInfo && localBookReaderInfo[id]) {
    booksReadInfo.value = localBookReaderInfo;
    getData(id, booksReadInfo.value[id].chapter);
    store.dispatch('curChapter', booksReadInfo.value[id].chapter);
  } else {
    booksReadInfo.value[id] = { book: id, chapter: 1 };
    getData(id, 1);
    store.dispatch('curChapter', 1);
  }
});

watch(curChapter, (val) => {
  localEvent.StorageSetter('cur_chapter', val);
  saveBooksInfo();
  getData(store.state.id, val);
});
</script>

<style lang="less" scoped>
/* 样式部分保持不变 */
.read-container {
  font-size: 16px;
  color: #555;
  line-height: 31px;
  min-height: 600px;
  padding: 15px;
  h4 {
    position: fixed;
    top: 0;
    left: 15px;
    right: 15px;
    height: 50px;
    line-height: 50px;
    font-size: 20px;
    color: #736357;
    margin: 0 0 1em 0;
    letter-spacing: 2px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }
  p {
    text-indent: 2em;
    margin: 0.5em 0;
    text-align: justify;
    line-height: 1.5;
  }
  /* 其他样式 */
}
</style>
