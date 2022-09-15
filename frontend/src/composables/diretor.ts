import {ref} from 'vue'
import api from "@/services/api";
import Diretor from '@/models/diretor';

export default function useDiretor(){

    const path = "/diretor/";
    const variables = ref([]);
    const variable = ref(new Diretor());
    const erros = ref('');

    const getAll = async () => {
        const response = await api.get(path + "list")
        variables.value = response.data;
    }

    const getById = async (id:any) => {
        const response = await api.get(path + id );
        variable.value = response.data;
    }

    const destroy = async (id:any) => {
        const response = await api.delete(path + "delete?id=" + id);
    }

    const save = async (data:any) => {
        erros.value = '';
        try{
            await api.post(path + "save", data);
            variable.value = new Diretor();
        } catch(e:any){
            if(e.response.status === 422){
                for(const key in e.response.data.errors){
                    erros.value += e.response.data.errors[key][0] + ' ';
                }
            }
        }
    }

    return {
        variables,
        variable,
        erros,
        getAll,
        getById,
        destroy,
        save
    }

}