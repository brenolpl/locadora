import {ref} from 'vue'
import api from "@/services/api";

export default function useActors(){

    const path = "/ator/";

    const actors = ref([])

    const getActors = async () => {
        const response = await api.get(path + "list")
        actors.value = response.data;
        console.log("🚀 ~ file: ator.ts ~ line 13 ~ getActors ~ actors.value", actors.value)
    }

    const destroyActor = async (id:any) => {
        const response = await api.delete(path + "delete?id=" + id);
    }

    return {
        actors,
        getActors,
        destroyActor
    }

}