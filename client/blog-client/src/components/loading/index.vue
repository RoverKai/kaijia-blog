<template>
  <div id="loading" ref="loadingPage">
    <!-- Loading spinner -->
    <svg class="animate-spin h-16 w-16 mb-4" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
      stroke="currentColor">
      <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
    </svg>
    <p>LOADING</p>
  </div>
</template>

<script setup>
import { getCurrentInstance, ref } from 'vue'
const loadingPage = ref()

const instance = getCurrentInstance()

function moveIn(next) {
  loadingPage.value.classList.remove('loading_out')
  setTimeout(() => {
    next()
    instance.emit('checkLoading')
  }, 1000)
}

function moveOut() {
  loadingPage.value.classList.add('loading_out')
}

defineExpose({
  moveIn,
  moveOut
})
</script>

<style scoped>
#loading {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100vh;
  background-color: white;
  /* Change this to match your background color */
  z-index: 1000000;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  transition: 1s ease;
}

#loading p {
  font-family: sans-serif;
  font-size: 2rem;
  font-weight: 900;
  margin-top: 1rem;
  transition: 0.3s ease;
}

/* Add spinner animation */
@keyframes circle_rotate {
  0% {
    transform: rotate(0deg);
    stroke-dashoffset: 160;
  }

  100% {
    transform: rotate(360deg);
    stroke-dashoffset: -160;
  }
}

.loading_out {
  transform: translateY(100%);
}
</style>
