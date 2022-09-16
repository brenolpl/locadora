import {ref} from 'vue'
import api from "@/services/api";
import Ator from '@/models/ator';

export default function useActors(){

    const path = "/ator/";
    const actors = ref([]);
    const actor = ref(new Ator());
    const erros = ref('');

    const getActors = async () => {
        const response = await api.get(path + "list")
        actors.value = response.data;
    }

    const getActor = async (id:any) => {
        const response = await api.get(path + id );
        actor.value = response.data;
    }

    const destroyActor = async (id:any) => {
        const response = await api.delete(path + "delete?id=" + id);
    }

    const saveActor = async (data:any) => {
        erros.value = '';
        try{
            await api.post(path + "save", data);
            actor.value = new Ator();
        } catch(e:any){
            if(e.response.status === 422){
                for(const key in e.response.data.errors){
                    erros.value += e.response.data.errors[key][0] + ' ';
                }
            }
        }
    }

    return {
        actors,
        actor,
        erros,
        getActors,
        getActor,
        destroyActor,
        saveActor
    }

}