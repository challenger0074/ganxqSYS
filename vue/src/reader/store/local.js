// local.js
const localEvent = {
  StorageGetter(key) {
    return JSON.parse(localStorage.getItem(key));
  },
  StorageSetter(key, val) {
    localStorage.setItem(key, JSON.stringify(val));
  },


  activeEmployees: state => state.employees.filter(emp => emp.status === 'active'),
};

export default localEvent;
