<template>
    <transition>
        <my-modal v-show="open">
            <form v-on:submit.prevent="createActor">
                <modal-header>
                    Inserir Ator
                    <button type="button" class="btn-close" @click="$emit('close')"></button>
                </modal-header>
                <modal-body>
                    <div class="form-floating mb-3">
                        <input type="text" name="nome" id="nome" class="form-control" v-model="actor.nome" placeholder="nome" required>
                        <label for="nome" class="form-label">Nome</label>
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
import api from "@/services/api"
import type { AxiosResponse } from 'axios';

export default defineComponent({
    name:'AtorForm',
    props:{
        open:{
            type:Boolean,
            required:true
        },
        editedActor:{
            type:Number,
            default:0
        }
    },
    emits:['saved', 'close'],
    setup(props,{emit}) {

        const isOpen = ref(false);

        // const editedActor = toRefs(props).editedActor;
        // console.log("🚀 ~ file: AtorForm.vue ~ line 48 ~ setup ~ editedActor", editedActor.value)

        const actor = ref({
            nome:"",
            id:0
        });

        const resetForm = () => {
            actor.value = {
                nome:"",
                id:0
            }
        }

        watch(()=>props.editedActor, (newVal) => {
            findActor(newVal);
        });

        async function findActor(id:any){
            const res = await requestFindApi(id);
            if(res.status === 200){
                actor.value = res.data
                return;
            }
            console.log("Erro");
        }

        async function requestFindApi(id:any){
            return api.get("/ator/" + id).then((response:AxiosResponse)=>{
                return response;
            }).catch((error)=>{
                return (error.response.data || {success:false, message: error.message});
            });
        }

        async function createActor(){
            if(actor.value.nome.length == 0) return;

            if(actor.value.id == 0){
                const res = await requestCreateApi({nome:actor.value.nome});
                if(res.status === 200){
                    resetForm;
                    emit('close');
                    emit('saved');
                    return;
                }
                console.log("Erro");
            }
            else{
                const res = await requestCreateApi({id:actor.value.id,nome:actor.value.nome});
                if(res.status === 200){
                    resetForm;
                    emit('close');
                    emit('saved');
                    return;
                }
                console.log("Erro");
            }
        }

        async function requestCreateApi(data:string){
            return api.post("/ator/save", data).then((response:AxiosResponse)=>{
                return response;
            }).catch((error)=>{
                return (error.response.data || {success:false, message: error.message});
            });
        }

        return {
            actor,
            createActor,
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
