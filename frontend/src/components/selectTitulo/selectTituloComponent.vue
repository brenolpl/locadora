<template>
  <div>
    <label for="selectTitulo">Selecione um título</label>
      <select class="col-md-5 col-xl-4 form-select mb-3" aria-label="Selecione um titulo" @change="onChange" id="selectTitulo">
          <option v-if="selected != null" selected :value="selected.id">{{selected.nome}}</option>
          <option v-for="(option, i) in options" :key="i" :value="option.id">{{option.nome}}</option>
      </select>
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref } from 'vue'
import useRequests from '@/composables/requests';
import Classe from '@/models/classe';
import Titulo from '@/models/titulo';

export default defineComponent({
  name:'SelectTitulo',
  emits: ["changeSelect"],
  props: {
    options: {
      type: Array,
      required: true,
    },
    selected: {
      type: Titulo,
      required: false,
      default: null,
    },
    tabindex: {
      type: Number,
      required: false,
      default: 0,
    },
  },
  setup(props, {emit}) {

      const selecionado = ref(0);

      // const refreshList = async () => {
      //     isOpen.value = false;
      //     getAll();
      // }

      const onChange = () => {
          const select = document.getElementById("selectTitulo");
          selecionado.value = select.options[select.selectedIndex].value;
          emit("changeSelect", {
              id:selecionado.value,
          });
      }


      return {onChange}
  },
})
</script>