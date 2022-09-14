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
                    <my-tr v-for="(diretor, i) in diretores" :key="i" :id="diretor.id" :table="'diretor'" @deleted="getDiretores" @edit="showFormEdit(diretor.id)">
                        <td>{{diretor.nome}}</td>
                    </my-tr>
                </tbody>
            </my-table>
        </div>
        <DiretorForm :open="isOpen" @close="isOpen = !isOpen" @saved="getDiretores" :editedDiretor="editDiretor"/>
    </main>
</template>

<script lang="ts">
import api from '@/services/api';
import { defineComponent, onMounted, ref } from 'vue'
import DiretorForm from './diretorForm.vue';

export default defineComponent({
    name:'DiretorView',
    components: {DiretorForm},
    setup() {
        const isOpen = ref(false);
        const diretores = ref([]);
        const editDiretor = ref(0);
    
        async function requestApi(){
            return api.get("/diretor/list").then((res) => {
                return res.data;
            }).catch((err) => {
                return false;
            });
        }
        async function getDiretores() {
            const res = ref(await requestApi());
            diretores.value = res.value;
        }

        function showFormEdit(id:any){
            editDiretor.value = id;
            isOpen.value = true;
        }

        onMounted(getDiretores);

        return {isOpen, diretores, getDiretores, editDiretor, showFormEdit}
    },
})
</script>
