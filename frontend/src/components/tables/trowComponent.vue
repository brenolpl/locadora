<template>
    <tr>
        <slot></slot>
        <td>
            <action-dropdown>
                <dropdown-item @click="deleteActor()">
                    <trash-icon/>
                    Excluir
                </dropdown-item>
                <dropdown-item @click="$emit('edit')">
                    <edit-icon />
                    Editar
                </dropdown-item>
            </action-dropdown>
        </td>
    </tr>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import api from '@/services/api';
import type { AxiosError, AxiosResponse } from 'axios';

export default defineComponent({
    name:'MyRow',
    props:{
        id:{
            type:Number,
            required:true,
        },
        table:{
            type:String,
            required:true,
        }
    },
    emits:['deleted', 'edit'],
    setup(props, {emit}) {
        
        async function deleteActor(){
            const res = await requestDeleteApi();
            if(res.status === 200){
                emit('deleted');
                return;
            }
            console.log("Erro");
        }

        function requestDeleteApi(){
            console.log("Ola Mundo" + props.table);
            return api.delete("/"+ props.table +"/delete?id=" + props.id).then((response:AxiosResponse)=>{
                return response;
            }).catch((error)=>{
                return (error.response.data || {success:false, message: error.message});
            });
        }

        return {deleteActor}
    },
})
</script>

<style scoped>
    td{
        padding:.75rem;
    }
</style>