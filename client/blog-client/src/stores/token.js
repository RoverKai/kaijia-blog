import { defineStore } from 'pinia';
import { ref } from 'vue';

export const useAuthStore = defineStore('auth', () => {
  const token = ref(null);
  const id = ref(null)

  const setToken = (newToken) => {
    token.value = newToken;
  };

  const clearToken = () => {
    token.value = null;
  };

  const setId = (newId) => {
    id.value = newId;
  };

  const clearId = () => {
    id.value = null;
  };

  return { token, id, setToken, clearToken, setId, clearId };
},{
  persist: true
});