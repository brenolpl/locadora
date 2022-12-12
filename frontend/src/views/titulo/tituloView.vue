<template>
    <main class="d-flex align-items-center justify-content-center shadow">
        <div class="container">
            <div class="d-flex align-items-center gap-3 mb-5">
                <div class="form-floating col-sm-6">
                    <input type="text" class="form-control" id="filtro" @input="changeList()" ref="filtro">
                    <label for="filtro" class="form-label">Filtro</label>
                </div>
                <div class="d-flex align-items-center">
                    <div>
                        <input type="radio" class="btn-check" name="options" id="options-nome" autocomplete="off" checked @change="changeList()" >
                        <label class="btn btn-outline-primary" for="options-nome">Nome</label>
                    </div>
                    <div>
                        <input type="radio" class="btn-check" name="options" id="options-categoria" autocomplete="off" @change="changeList()">
                        <label class="btn btn-outline-primary" for="options-categoria">Categoria</label>
                    </div>
                    <div>
                        <input type="radio" class="btn-check" name="options" id="options-ator" autocomplete="off" @change="changeList()">
                        <label class="btn btn-outline-primary" for="options-ator">Ator</label>
                    </div>
                </div>
            </div>
            <header class="d-flex align-items-center justify-content-between mb-5">
                <h1 class="text-dark">Lista de Títulos</h1>
                <button class="btn btn-primary fw-bold" @click="isOpen = true">Adicionar</button>
            </header>
            <my-table>
                <my-thead>
                    <th class="col-sm-4">Nome</th>
                    <th class="col-sm-2">Ano</th>
                    <th class="col-sm-4">Diretor</th>
                </my-thead>
                <tbody>
                    <my-tr v-for="(titulo, i) in filteredList" :key="i" :id="titulo.id" :table="'classe'" @delete="deleteElement(titulo.id)" @edit="showFormEdit(titulo.id)">
                        <td>{{titulo.nome}}</td>
                        <td>{{titulo.ano}}</td>
                        <td>{{titulo.diretor?.nome || ''}}</td>
                    </my-tr>
                </tbody>
            </my-table>
        </div>
        <TituloForm :open="isOpen" @close="cancelChange" @saved="refreshList" :editedId="editedId"/>
    </main>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref, watch } from 'vue'
import TituloForm from './tituloForm.vue';
import useRequests from '@/composables/requests';
import Titulo from '@/models/titulo';

export default defineComponent({
    name:'TituloView',
    components: { TituloForm },
    setup() {

        const { entities, getAll, destroy, resetEntity } = useRequests(Titulo);
        const isOpen = ref(false);
        const editedId = ref(0);
        const filteredList = ref([]);
        const filtro = ref(null);

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

        const changeList = () => {
            const filterValue = filtro.value?.value;
            if(filterValue !== ""){
                if(document.getElementById("options-nome")?.checked){
                    filteredList.value = entities.value.filter(titulo => titulo.nome.toLowerCase().includes(filterValue.toLowerCase()));
                }
                else if(document.getElementById("options-categoria")?.checked){
                    filteredList.value = entities.value.filter(titulo => titulo.categoria.toLowerCase().includes(filterValue.toLowerCase()));
                }
                else if(document.getElementById("options-ator")?.checked){
                    filteredList.value = entities.value.filter((titulo) => {
                        const atores = titulo.atores;
                        const atoresFiltrados = atores.filter((ator) => {
                            return ator.nome.toLowerCase().includes(filterValue.toLowerCase())
                        })
                        return atores.includes(atoresFiltrados[0]);
                    });
                }
            }
            else filteredList.value = entities.value;
        }

        watch(() => entities.value, (newVal) => {
            if(newVal != null) filteredList.value = entities.value;
        });

        const cancelChange = () => {
            isOpen.value = !isOpen.value;
            editedId.value = 0;
        }

        onMounted(getAll);

        return {isOpen, entities, getAll, refreshList, deleteElement, editedId, showFormEdit, cancelChange, filteredList, changeList, filtro}
    },
})
</script>
