<template>
    <main class="d-flex align-items-center justify-content-center shadow">
        <div class="container">
            <header class="d-flex align-items-center justify-content-between mb-5">
                <h1 class="text-dark">Lista de Clientes</h1>
                <button class="btn btn-primary fw-bold" @click="isOpen = true">Adicionar</button>
            </header>
            <my-table>
                <my-thead>
                    <th class="col-sm-4">Nome</th>
                    <th class="col-sm-2">N° Inscrição</th>
                    <th class="col-sm-4">Data Nascimento</th>
                </my-thead>
                <tbody>
                    <my-tr v-for="(cliente, i) in entities" :key="i" :id="cliente.id" :table="'classe'" @delete="deleteElement(cliente.id)" @edit="showFormEdit(cliente.id)">
                        <td>{{cliente.nome}}</td>
                        <td>{{cliente.numInscricao}}</td>
                        <td>{{cliente.date || ''}}</td>
                    </my-tr>
                </tbody>
            </my-table>
        </div>
        <ClienteForm :open="isOpen" @close="cancelChange" @saved="refreshList" :editedId="editedId"/>
    </main>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref } from 'vue'
import ClienteForm from './clienteForm.vue';
import useRequests from '@/composables/requests';
import Cliente from '@/models/cliente';

export default defineComponent({
    name:'ClienteView',
    components: { ClienteForm },
    setup() {

        const { entities, getAll, destroy, resetEntity } = useRequests(Cliente);
        const isOpen = ref(false);
        const editedId = ref(0);

        function showFormEdit(id:any){
            editedId.value = id;
            isOpen.value = true;
        }

        const deleteElement = async (id:any) => {
            if(!window.confirm("Tem certeza que deseja excluir o cliente?")) return;
            await destroy(id);
            await getAll();
        }

        const refreshList = async () => {
            isOpen.value = false;
            getAll();
        }

        const cancelChange = () => {
            isOpen.value = !isOpen.value;
            editedId.value = 0;
        }

        onMounted(getAll);

        return {isOpen, entities, getAll, refreshList, deleteElement, editedId, showFormEdit, cancelChange}
    },
})
</script>
