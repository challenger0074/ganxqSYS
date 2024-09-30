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
import readerAPI from './reader/assets/js/utils.js'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

// 创建
const app =createApp(App);
// 全局注册el-icon
for (const [name, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(name, component);
}
app.config.globalProperties.$common = {
    defaultImage(e) {
        e.target.src =
            'http://www.zwdu.com/files/article/image/20/20962/20962s.jpg';
    },
    api: '/api',
};
const pinia=createPinia()
//安装
app.use(router);
app.use(pinia)
app.use(ElementPlus)
app.use(store)
app.use(showToast)
app.use(readerAPI)
app.mount('#app');
