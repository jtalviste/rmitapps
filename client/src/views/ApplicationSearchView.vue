<template>
  <h2>Search Application</h2>
  <div>
    <input type="text" v-model="searchTerm" />
    <input type="submit" value="Search" @click="searchApplication()" />
    <br />
    <br />
    <div>
      <h2>Search results</h2>

      <table>
        <thead>
        <tr>
          <th>Application Code</th>
          <th>Name</th>
          <th>Type</th>
          <th>Group</th>
          <th>Description</th>
          <th>Cost</th>
          <th>Last Modified</th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <td>{{ application.applicationCode }}</td>
          <td>{{ application.name }}</td>
          <td>{{ application.type }}</td>
          <td>{{ application.group }}</td>
          <td>{{ application.description }}</td>
          <td>{{ application.cost }}</td>
          <td>{{ application.lastModified }}</td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import server from "@/server/server";
import type { ApplicationResponse } from "@/server/dto/applicationresponse";

export default defineComponent({
  setup() {
    const searchTerm = ref("Example Service");

    const exampleApplicationResponse: ApplicationResponse = {
      applicationCode: "",
      name: "",
      type: "",
      group: "",
      description: "",
      cost: 0,
      lastModified: ""
    };

    const application = ref(exampleApplicationResponse);

    async function searchApplication() {
      application.value = await server.searchApplication(searchTerm.value) || exampleApplicationResponse;
    }

    return {
      searchApplication,
      searchTerm,
      application
    };
  },
});
</script>

<style scoped>
table {
    width: 100%;
}

th,
td {
    border: 1px solid var(--color-border);
    text-align: left;
    padding: 0;
    margin: 0;
    width: 50%;
}
</style>
