<template>
    <select class="col-md-5 col-xl-4 form-select mb-3" aria-label="Selecione um diretor" @change="onChange" id="selectDiretor">
        <option selected>Selecione um diretor</option>
        <option v-for="(diretor, i) in entities" :key="i" :value="diretor.id">{{diretor.nome}}</option>
    </select>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref } from 'vue'
import useRequests from '@/composables/requests';
import Diretor from '@/models/diretor';

export default defineComponent({
    name:'DiretorSelect',
    emits: ["changeSelect"],
    setup(props, {emit}) {

        const { entities, getAll } = useRequests(Diretor);
        const isOpen = ref(false);
        const selecionado = ref(0);

        const refreshList = async () => {
            isOpen.value = false;
            getAll();
        }

        const onChange = () => {
            const select = document.getElementById("selectDiretor");
            selecionado.value = select.options[select.selectedIndex].value;
            emit("changeSelect", {
                id:selecionado.value,
            });
        }

        onMounted(getAll);

        return {isOpen, entities, getAll, refreshList, onChange}
    },
})
</script>
