import { createStore } from 'vuex';
import moduleA from '../ganxq/store/index.js';
import moduleB from '../reader/store/index.js';

const store = createStore({
    modules: {
        a: moduleA,
        b: moduleB,
    },
});

export default store;
