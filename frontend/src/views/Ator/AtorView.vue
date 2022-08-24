<template>
    <main class="d-flex align-items-center justify-content-center">
        <button class="btn btn-primary fw-bold" @click="isOpen = true">Adicionar</button>
        <AtorForm :open="isOpen" @close="isOpen = !isOpen"/>
    </main>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue'
import AtorForm from './AtorForm.vue';

export default defineComponent({
    name:'AtorView',
    components: {AtorForm},
    setup() {
        const isOpen = ref(false);
        return {isOpen}
    },
})
</script>
