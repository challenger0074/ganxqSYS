<template>
  <div class="playlist-container">
    <h1>播放列表</h1>
    <ul v-if="playlist.length" class="playlist-list">
      <li v-for="music in playlist" :key="music.id" class="playlist-item">
        <span class="music-name">{{ music.musicName }}</span>
        <span class="upload-user">- {{ music.uploadUser }}</span>
      </li>
    </ul>
    <p v-else class="empty-playlist">播放列表为空。</p>
  </div>
</template>

<script setup lang="ts">
import { computed } from 'vue';
import { useStore } from 'vuex';

const store = useStore();
const playlist = computed(() => store.getters.getPlaylist);
</script>

<style scoped>
.playlist-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.playlist-list {
  list-style-type: none;
  padding: 0;
}

.playlist-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px;
  border-bottom: 1px solid #ddd;
}

.playlist-item:last-child {
  border-bottom: none;
}

.music-name {
  font-weight: bold;
}

.upload-user {
  color: #666;
}

.empty-playlist {
  text-align: center;
  color: #aaa;
}
</style>



