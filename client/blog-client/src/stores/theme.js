import { defineStore } from "pinia";
import { ref, onMounted } from "vue";

export const useThemeStore = defineStore('theme', () => {
  const isDark = ref(false);

  onMounted(() => {
    isDark.value = localStorage.theme === 'dark'||
    (!('theme' in localStorage) &&
    window.matchMedia('(perfers-color-scheme: dark)').matches);
    updateTheme()
  })

  const toggleTheme = () => {
    isDark.value = !isDark.value;
    updateTheme();
  }

  const updateTheme = () => {
    if (isDark.value) {
      document.documentElement.classList.add('dark');
      localStorage.setItem('theme','dark');
    } else {
      document.documentElement.classList.remove('dark');
      localStorage.setItem('theme','light');
    }
  }
  return { isDark, toggleTheme };
});