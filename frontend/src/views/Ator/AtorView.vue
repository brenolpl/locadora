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
                    <my-tr v-for="(actor, i) in actors" :key="i" :id="actor.id" @deleted="getActors" @edit="showFormEdit(actor.id)">
                        <td>{{actor.nome}}</td>
                    </my-tr>
                </tbody>
            </my-table>
        </div>
        <AtorForm :open="isOpen" @close="isOpen = !isOpen" @saved="getActors" :editedActor="editActor"/>
    </main>
</template>

<script lang="ts">
import api from '@/services/api';
import { defineComponent, onMounted, ref } from 'vue'
import AtorForm from './AtorForm.vue';

export default defineComponent({
    name:'AtorView',
    components: {AtorForm},
    setup() {
        const isOpen = ref(false);
        const actors = ref([]);
        const editActor = ref(0);
    
        async function requestApi(){
            return api.get("/ator/list").then((res) => {
                return res.data;
            }).catch((err) => {
                return false;
            });
        }
        async function getActors() {
        const res = ref(await requestApi());
        actors.value = res.value;
        }

        function showFormEdit(id:any){
            editActor.value = id;
            console.log("🚀 ~ file: AtorView.vue ~ line 35 ~ setup ~ editActor", editActor)
            isOpen.value = true;
        }

        onMounted(getActors);

        return {isOpen, actors, getActors, editActor, showFormEdit}
    },
})
</script>
