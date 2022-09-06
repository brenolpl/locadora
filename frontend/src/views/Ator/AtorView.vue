<template>
    <main class="d-flex align-items-center justify-content-center shadow">
        <div class="container">
            <header class="d-flex align-items-center justify-content-between mb-5">
                <h1 class="text-dark">Lista de Atores</h1>
                <button class="btn btn-primary fw-bold" @click="isOpen = true">Adicionar</button>
            </header>
            <my-table>
                <my-thead>
                    <th>Nome</th>
                </my-thead>
                <tbody>
                    <my-tr v-for="(actor, i) in actors" :key="i" >
                        <td>{{actor.nome}}</td>
                    </my-tr>
                </tbody>
            </my-table>
        </div>
        <AtorForm :open="isOpen" @close="isOpen = !isOpen"/>
    </main>
</template>

<script lang="ts">
import { defineComponent, reactive, ref } from 'vue'
import AtorForm from './AtorForm.vue';

export default defineComponent({
    name:'AtorView',
    components: {AtorForm},
    setup() {
        const isOpen = ref(false);
        const actors = reactive([
            {
                nome:"Tom Cruise"
            },
            {
                nome: "Will Smith"
            },
            {
                nome: "Angelina Jolie"
            },
            {
                nome: "Brad Pitt"
            },
            {
                nome: "Leonardo DiCaprio"
            },
            {
                nome: "Ben Afleck"
            }
        ])
        return {isOpen, actors}
    },
})
</script>
