<style scoped>
label {
    display: inline-block;
    width: 120px;
    text-align: end;
}
</style>
<template>
  <h2>Add Service</h2>
  <form @submit.prevent="addService">
    <label for="code">Code:</label>
    <input type="text" id="code" v-model="service.code" required>

    <br>

    <label for="name">Name:</label>
    <input type="text" id="name" v-model="service.name" required>

    <br>

    <label for="description">Description:</label>
    <textarea id="description" v-model="service.description" required></textarea>

    <br>

    <label for="applicationCode">Application Code:</label>
    <input type="text" id="applicationCode" v-model="service.applicationCode" required>

    <br>

    <label for="type">Type:</label>
    <select id="type" v-model="service.type" required>
      <option value="HTTP">HTTP</option>
      <option value="SAML">SAML</option>
      <option value="SSH">SSH</option>
      <option value="JDBC">JDBC</option>
      <option value="ODBC">ODBC</option>
    </select>

    <br>

    <label for="subType">Subtype:</label>
    <select id="subType" v-model="service.subType" required>
      <option value="REST">REST</option>
      <option value="SOAP">SOAP</option>
    </select>

    <br>

    <button type="submit">Add Service</button>
  </form>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import server from "@/server/server";
import { AppServiceType } from "@/server/dto/appservicetype";
import { AppServiceSubType } from "@/server/dto/appservicesubtype";
import type { AppServiceAddRequest } from "@/server/dto/appserviceaddrequest";

export default defineComponent({
  setup() {

    const exampleAppServiceAddRequest: AppServiceAddRequest = {
      code: "ABC123",
      name: "Example Service",
      description: "This is an example service",
      applicationCode: "APP123",
      type: AppServiceType.HTTP,
      subType: AppServiceSubType.REST
    };
    const service = ref(exampleAppServiceAddRequest);

    async function addService() {
      await server.addService(service.value);
    }

    return {
      addService,
      service
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