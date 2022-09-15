<template>
    <transition>
        <my-modal v-show="open">
            <form v-on:submit.prevent="formSave">
                <modal-header>
                    Inserir Classe
                    <button type="button" class="btn-close" @click="$emit('close')"></button>
                </modal-header>
                <modal-body>
                    <div class="form-floating mb-3">
                        <input type="text" name="nome" id="nome" class="form-control" v-model="variable.nome" placeholder="nome" required>
                        <label for="nome" class="form-label">Nome</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" name="valor" id="valor" class="form-control" v-model="variable.valor" placeholder="R$ 0,00" required>
                        <label for="valor" class="form-label">Valor</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="datetime-local" name="devolucao" id="devolucao" class="form-control" v-model="variable.devolucao" placeholder="xx/xx/xxxx" required>
                        <label for="nome" class="form-label">Data de Devolução</label>
                    </div>
                </modal-body>
                <modal-footer>
                    <button class="btn btn-outline-danger" type="button" @click="$emit('close')">Cancelar</button>
                    <button class="btn btn-primary" type="submit">Salvar</button>
                </modal-footer>
            </form>
        </my-modal>
    </transition>

</template>

<script lang="ts">
import { computed, defineComponent, ref, toRef, toRefs, watch } from 'vue'
import useClasses from '@/composables/classe';
export default defineComponent({
    name:'ClasseForm',
    props:{
        open:{
            type:Boolean,
            required:true
        },
        editedId:{
            type:Number,
            default:0
        }
    },
    emits:['saved', 'close'],
    setup(props,{emit}) {

        const { erros, save, getById, variable } = useClasses();

        const isOpen = ref(false);

        const formSave = async () => {
            await save(variable.value);
            emit('saved');
        }

        watch(()=>props.editedId, (newVal) => {
            getById(newVal);
        });

        return {
            variable,
            erros,
            formSave,
            isOpen
        }     
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
