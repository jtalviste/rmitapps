<style scoped>
  label {
      display: inline-block;
      width: 80px;
      text-align: end;
  }
</style>
<template>
  <h1>Add Application</h1>
  <form @submit.prevent="addApplication">
    <label for="code">Code:</label>
    <input type="text" id="code" v-model="application.code" required>

    <br>

    <label for="name">Name:</label>
    <input type="text" id="name" v-model="application.name" required>

    <br>

    <label for="group">Group:</label>
    <input type="text" id="group" v-model="application.group" required>

    <br>

    <label for="type">Type:</label>
    <input type="text" id="type" v-model="application.type" required>

    <br>

    <label for="description">Description:</label>
    <textarea id="description" v-model="application.description" required></textarea>

    <br>

    <label for="cost">Cost:</label>
    <input type="number" id="cost" v-model.number="application.cost" required>

    <br>

    <button type="submit">Add Application</button>
  </form>
</template>

<script>
import { defineComponent, ref } from "vue";
import server from "@/server/server";

export default defineComponent({
  setup() {
    const exampleApplicationAddRequest = {
      code: "APP123",
      name: "Example Application",
      group: "Example Group",
      type: "Example Type",
      description: "This is an example application",
      cost: 100.0
    };
    const application = ref(exampleApplicationAddRequest);

    async function addApplication() {
      await server.addApplication(application.value);
    }

    return {
      addApplication,
      application
    };
  }
});
</script>

