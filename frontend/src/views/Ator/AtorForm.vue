<template>
    <transition>
        <div class="my-modal" id="modalAtorForm w-100 h-100" aria-hidden="true" aria-labelledby="modalAtorFormLabel" tabindex="-1" v-show="open">
            <div class="my-modal-inner">
                <form class="modal-content">
                    <div class="my-modal-header">
                        <legend>Inserir novo ator</legend>
                        <button type="button" class="btn-close" @click="$emit('close')"></button>
                    </div>
                    <div class="my-modal-body">
                        <div class="form-floating mb-3">
                            <input type="text" name="nome" id="nome" class="form-control" placeholder="nome" required>
                            <label for="nome" class="form-label">Nome</label>
                        </div>
                    </div>
                    <div class="my-modal-footer">
                        <button class="btn btn-outline-danger" type="button" @click="$emit('close')">Cancelar</button>
                        <button class="btn btn-primary" type="submit">Salvar</button>
                    </div>
                </form>
            </div>
        </div>
    </transition>
</template>

<script lang="ts">
import { defineComponent } from 'vue'

export default defineComponent({
    name:'AtorForm',
    props:{
        open:{
            type:Boolean,
            required:true
        }
    },
    setup() {
     return {}   
    },
})
</script>

<style>
    .my-modal{
        position:fixed;
        top:0;
        left:0;
        height: 100%;
        width: 100%;
        background-color: rgba(0,0,0,0.5);
        display: flex;
        align-items: center;
        justify-content: center;
        z-index: 1000;
    }

    .my-modal-inner{
        background-color: #fff;
        box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;
        padding: 2rem;
    }

    .my-modal-footer{
        display: flex;
        align-items: center;
        justify-content: center;
        gap: 1rem;
    }

    .my-modal-header{
        display: flex;
        align-items: center;
        justify-content: space-between;
        margin-bottom: 1rem;
    }

    .my-modal-body{
        margin-bottom: 1rem;
    }

    .v-enter-active, .v-leave-active {
        transition: opacity 0.3s ease;
    }
    .v-enter-from, .v-leave-to {
        opacity: 0;
    }
</style>
