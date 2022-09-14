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
                    <my-tr v-for="(actor, i) in actors" :key="i" :id="actor.id" @delete="deleteActor(actor.id)" @edit="showFormEdit(actor.id)">
                        <td>{{actor.nome}}</td>
                    </my-tr>
                </tbody>
            </my-table>
        </div>
        <AtorForm :open="isOpen" @close="isOpen = !isOpen" @saved="getActors" :editedActor="editActor"/>
    </main>
</template>

<script lang="ts">

// import api from '@/services/api';
import useActors from '@/composables/ator';
import { defineComponent, onMounted, ref } from 'vue'
import AtorForm from './AtorForm.vue';

export default defineComponent({
    name:'AtorView',
    components: {AtorForm},
    setup() {

        const { actors, getActors, destroyActor } = useActors();
        const isOpen = ref(false);
        const editActor = ref(0);

        function showFormEdit(id:any){
            editActor.value = id;
            isOpen.value = true;
        }

        const deleteActor = async (id:any) => {
            if(!window.confirm("Tem certeza que deseja excluir o ator?")) return;
            await destroyActor(id);
            await getActors();
        }

        onMounted(getActors);

        return {isOpen, actors, getActors, deleteActor, editActor, showFormEdit}
    },
})
</script>
