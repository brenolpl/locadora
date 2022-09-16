<template>
    <main class="d-flex align-items-center justify-content-center shadow">
        <div class="container">
            <header class="d-flex align-items-center justify-content-between mb-5">
                <h1 class="text-dark">Lista de Diretores</h1>
                <button class="btn btn-primary fw-bold" @click="isOpen = true">Adicionar</button>
            </header>
            <my-table>
                <my-thead>
                    <th>Nome</th>
                </my-thead>
                <tbody>
                    <my-tr v-for="(diretor, i) in variables" :key="i" :id="diretor.id" :table="'diretor'" @delete="deleteElement(diretor.id)" @edit="showFormEdit(diretor.id)">
                        <td>{{diretor.nome}}</td>
                    </my-tr>
                </tbody>
            </my-table>
        </div>
        <DiretorForm :open="isOpen" @close="isOpen = !isOpen" @saved="refreshList" :editedId="editedId"/>
    </main>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref } from 'vue'
import DiretorForm from './diretorForm.vue';
import useDiretor from '@/composables/diretor';

export default defineComponent({
    name:'DiretorView',
    components: {DiretorForm},
    setup() {

        const { variables, getAll, destroy } = useDiretor();
        const isOpen = ref(false);
        const editedId = ref(0);

        function showFormEdit(id:any){
            editedId.value = id;
            isOpen.value = true;
        }

        const deleteElement = async (id:any) => {
            if(!window.confirm("Tem certeza que deseja excluir o ator?")) return;
            await destroy(id);
            await getAll();
        }

        const refreshList = async () => {
            isOpen.value = false;
            getAll();
        }

        onMounted(getAll);

        return {isOpen, variables, getAll, refreshList, deleteElement, editedId, showFormEdit}
    },
})
</script>
