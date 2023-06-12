<script setup lang="ts">
import { RouterView } from 'vue-router'
</script>
<template>
  <div class="app">
    <h1>
      RMIT Application database
    </h1>
    <nav>
      <RouterLink to="/application">Add Application</RouterLink>
      <RouterLink to="/service">Add Service</RouterLink>
      <RouterLink to="/appsearch">Search Application</RouterLink>
      <RouterLink to="/servicesearch">Search Services</RouterLink>
    </nav>
    <router-view></router-view>
  </div>
</template>

<style scoped>

.app {
  text-align: center;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}

nav a.router-link-exact-active {
  color: var(--color-text);
}

nav a.router-link-exact-active:hover {
  background-color: transparent;
}

nav a {
  display: inline-block;
  padding: 1rem;
  border-left: 1px solid var(--color-border);
}

nav a:first-of-type {
  border: 0;
}
</style>
