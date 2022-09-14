<template>
    <main class="d-flex align-items-center justify-content-center shadow">
        <div class="container">
            <header class="d-flex align-items-center justify-content-between mb-5">
                <h1 class="text-dark">Lista de Classes</h1>
                <button class="btn btn-primary fw-bold" @click="isOpen = true">Adicionar</button>
            </header>
            <my-table>
                <my-thead>
                    <th class="col-sm-4">Nome</th>
                    <th class="col-sm-2">Valor</th>
                    <th class="col-sm-4">Devolução</th>
                </my-thead>
                <tbody>
                    <my-tr v-for="(classe, i) in classes" :key="i" :id="classe.id" :table="'classe'" @deleted="getClass" @edit="showFormEdit(classe.id)">
                        <td>{{classe.nome}}</td>
                        <td>R${{classe.valor}}</td>
                        <td>{{classe.prazoDevolucao}}</td>
                    </my-tr>
                </tbody>
            </my-table>
        </div>
        <ClasseForm :open="isOpen" @close="isOpen = !isOpen" @saved="getClass" :editedClass="editClass"/>
    </main>
</template>

<script lang="ts">
import api from '@/services/api';
import { defineComponent, onMounted, ref } from 'vue'
import ClasseForm from './classeForm.vue';

export default defineComponent({
    name:'ClasseView',
    components: { ClasseForm },
    setup() {
        const isOpen = ref(false);
        const classes = ref([]);
        const editClass = ref(0);
    
        async function requestApi(){
            return api.get("/classificacao/list").then((res) => {
                return res.data;
            }).catch((err) => {
                return false;
            });
        }
        async function getClass() {
            const res = ref(await requestApi());
            classes.value = res.value;
        }

        function showFormEdit(id:any){
            editClass.value = id;
            isOpen.value = true;
        }

        function formatarData(data){
            const d = new Date(data);
            var data = d.toLocaleDateString + d.toLocaleTimeString;
            return data;
        }

        onMounted(getClass);

        return {isOpen, classes, getClass, editClass, showFormEdit, formatarData}
    },
})
</script>
