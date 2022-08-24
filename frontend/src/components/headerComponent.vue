<template>
    <header class="position-relative shadow-sm top-0 w-100 py-2 py-md-3" style="z-index:4">


    <nav class="navbar navbar-expand-lg flex-nowrap">

    <div class="container-xxl">

    <router-link class="navbar-brand" to="/">
        <img src="<?=__BASEPATH__?>img/brand.webp" alt="Página inicial" data-aos="fade-down"/>
    </router-link>

    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#menu" aria-controls="menu" aria-expanded="false" aria-label="Alternar navegação">
        <span class="navbar-toggler-icon"></span>
        <span class="navbar-toggler-icon"></span>
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="menu">

        <div class="navbar-nav ms-auto align-items-md-center gap-lg-2 gap-xxl-4" data-aos="fade-in">

        <a href="javascript:;" data-bs-toggle="collapse" data-bs-target="#menu" aria-controls="menu" class="p-5 fs-1 text-white position-absolute top-0 end-0 d-lg-none">&times;</a>
        
        <router-link to="Ator" class="nav-link active">Ator</router-link>
        
        <app-dropdown title="Ola mundo">
            <dropdown-item target="_blank" link="Ola">Item 1</dropdown-item>
            <dropdown-item target="_blank" link="Ola">Item 1</dropdown-item>
            <dropdown-item target="_blank" link="Ola">Item 1</dropdown-item>
        </app-dropdown>
        <AppDropdown />


        </div>

    </div>

    </div>

    </nav>
        

    </header>

</template>

<script lang="ts">
import { defineComponent, ref, reactive } from 'vue'
// import AppDropdown from './dropdown/dropdownComponent.vue';
export default defineComponent({
    name:"AppHeader",
    // components:{ AppDropdown },
    setup() {
        return {}
    },
})
</script>
