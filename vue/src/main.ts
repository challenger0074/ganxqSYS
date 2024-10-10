// 引入
import { createPinia } from 'pinia';
import store from './reader/store/index.js';
/*import store from './ganxq/store/index.js';*/
import {createApp} from 'vue';
import App from './App.vue';
import {router}from './router'
import ElementPlus from 'element-plus'

import 'element-plus/dist/index.css'
import showToast from './plugin/toast'
import readerAPI from './reader/assets/js/utils'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

// 创建
const app =createApp(App);
app.use(readerAPI)
// 全局注册el-icon
for (const [name, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(name, component);
}
const pinia=createPinia()
//安装
app.use(router);
app.use(pinia)
app.use(ElementPlus)
app.use(store)
app.use(showToast)

app.mount('#app');
