<template>
  <h2>Search Services</h2>
  <div>
    <input type="text" v-model="searchTerm" />
    <input type="submit" value="Search" @click="searchServices()"/>
    <br>
    <br>
    <div>
      <h2>Search results</h2>

      <table>
        <thead>
        <tr>
          <th>Service Code</th>
          <th>Name</th>
          <th>Type</th>
          <th>Subtype</th>
          <th>Description</th>
          <th>Last Modified</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="service in services" :key="service.serviceCode">
          <td>{{ service.serviceCode }}</td>
          <td>{{ service.name }}</td>
          <td>{{ service.type }}</td>
          <td>{{ service.subType }}</td>
          <td>{{ service.description }}</td>
          <td>{{ service.lastModified }}</td>
        </tr>
        </tbody>
      </table>

    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import server from "@/server/server";
import type { AppServiceResponse } from "@/server/dto/appserviceresponse";

export default defineComponent({
  setup() {
    const searchTerm = ref("Example Application");
    const services = ref([] as AppServiceResponse[]);

    async function searchServices() {
      services.value = await server.searchServices(searchTerm.value);
    }

    return {
      searchServices,
      searchTerm,
      services
    };
  },
});
</script>
<style scoped>
table {
    width: 100%;
}
th,td {
  border: 1px solid var(--color-border);
  text-align: left;
  padding: 0;
  margin: 0;
  width: 50%;
}
</style>