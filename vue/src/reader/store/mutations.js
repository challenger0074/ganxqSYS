// mutations.js
import * as types from './mutations-types';

export default {
  [types.TOGGLE_BAR](state) {
    state.bar = !state.bar;
  },
  [types.SHOW_LIST_PANEL](state) {
    state.list_panel = !state.list_panel;
  },
  [types.SHOW_FONT_PANEL](state) {
    state.font_panel = !state.font_panel;
  },
  [types.FZ_SIZE_ADD](state) {
    state.fz_size = Math.min(state.fz_size + 1, 24);
  },
  [types.FZ_SIZE_SUB](state) {
    state.fz_size = Math.max(state.fz_size - 1, 14);
  },
  [types.SWITCH_NIGHT](state) {
    state.bg_night = !state.bg_night;
  },
  [types.PREV_CHAPTER](state) {
    if (state.curChapter > 1) state.curChapter--;
  },
  [types.NEXT_CHAPTER](state, maxChapter) {
    if (state.curChapter < maxChapter) state.curChapter++;
  },
  [types.CHOOSE_BOOK](state, id) {
    state.curBookDetailId = id;
  },
  [types.SHOW_BOOK_DETAIL](state, obj) {
    state.bookDetail = obj;
  },
  [types.CUR_CHAPTER](state, num) {
    state.curChapter = num;
  },




  ADD_EMPLOYEE(state, employee) {
    state.employees.push({ ...employee, status: 'active' });
  },
  UPDATE_EMPLOYEE(state, updatedEmployee) {
    const index = state.employees.findIndex(emp => emp.employeeId === updatedEmployee.employeeId);
    if (index !== -1) {
      // Direct mutation is fine here, no need for Vue.set in Vuex 4
      state.employees[index] = { ...updatedEmployee };
    }
  },
  DELETE_EMPLOYEE(state, employeeId) {
    const index = state.employees.findIndex(emp => emp.employeeId === employeeId);
    if (index !== -1) {
      state.employees[index] = { ...state.employees[index], status: 'deleted' };
    }
  },
};