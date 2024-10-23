<script setup>
import TopBar from './components/topBar/index.vue';
import FootBar from './components/footBar.vue';
</script>

<template>
  <div id="main" class=" h-screen flex flex-col">
    <div class="">
      <top-bar></top-bar>
    </div>
    <div class=" flex justify-center grow ">
      <div class=" flex flex-col items-center justify-center w-2/3">
      <router-view></router-view>
      </div>
    </div>
    <div class="">
      <foot-bar></foot-bar>
    </div>
  </div>
</template>

<style scoped>
#main {
  background-color: #6f9986;
}
</style>
