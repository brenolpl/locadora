<template>
    <transition>
        <my-modal v-show="open">
            <form v-on:submit.prevent="formSave">
                <modal-header>
                    Inserir Titulo
                    <button type="button" class="btn-close" @click="$emit('close')"></button>
                </modal-header>
                <modal-body class="d-flex align-items-center flex-wrap gap-3">
                    <div class="col-md-7 col-xxl-9 form-floating mb-3">
                        <input type="text" name="nome" id="nome" class="form-control" v-model="entity.nome" placeholder="nome" >
                        <label for="nome" class="form-label">Nome</label>
                    </div>
                    <div class="col-md-5 col-xxl-2 form-floating mb-3">
                        <input type="text" name="ano" id="ano" class="form-control" v-model="entity.ano" >
                        <label for="valor" class="form-label">Ano</label>
                    </div>
                    <div class="col-sm-12 col form-floating mb-3">
                        <textarea name="sinopse" id="sinopse" cols="30" rows="100" class="form-control" v-model="entity.sinopse"></textarea>
                        <label for="nome" class="form-label">Sinopse</label>
                    </div>
                    <select class="col-md-5 col-xl-4 form-select mb-3" aria-label="Selecione uma categoria">
                        <option selected>Selecione uma categoria</option>
                        <option value="1">Romance</option>
                        <option value="2">Terror</option>
                        <option value="3">Supense</option>
                    </select>
                    <ClasseSelect @changeSelect="addClasse"/>
                    <DiretorSelect @changeSelect="addDiretor" />
                    <div class="col-sm-6">
                        <a class="btn btn-primary fw-bold" @click="openModalAtor = true">Adicionar Atores</a>
                    </div>
                </modal-body>
                <modal-footer>
                    <button class="btn btn-outline-danger" type="button" @click="$emit('close')">Cancelar</button>
                    <button class="btn btn-primary" type="submit">Salvar</button>
                </modal-footer>
            </form>
        </my-modal>
    </transition>
    <AtorModalComponent :open="openModalAtor" @close="openModalAtor = !openModalAtor" @saved="addAtor"/>

</template>

<script lang="ts">
import {defineComponent, ref, watch } from 'vue';

import useRequests from '@/composables/requests';
import Titulo from '@/models/titulo';
import AtorModalComponent from '../../components/atorModal/atorModalComponent.vue';
import DiretorSelect from '../../components/diretorSelect/diretorSelectComponent.vue';
import ClasseSelect from '../../components/classeSelect/classeSelectComponent.vue';
export default defineComponent({
    name: "TituloForm",
    props: {
        open: {
            type: Boolean,
            required: true
        },
        editedId: {
            type: Number,
            default: 0
        }
    },
    emits: ["saved", "close"],
    setup(props, { emit }) {

        const { erros, save, getById, entity } = useRequests(Titulo);

        const openModalAtor = ref(false);
        const diretor = ref();
        const classe = ref();

        const formSave = async () => {
            await save(entity.value);
            emit("saved");
        };

        const addAtor = (atoresList:any) => {
            entity.atores = atoresList;
            console.log("🚀 ~ file: tituloForm.vue ~ line 82 ~ addAtor ~ entity.atores", entity.atores)
            openModalAtor.value = !openModalAtor.value;
        }

        const addDiretor = (director:any) => {
            entity.diretor = director;
            console.log("🚀 ~ file: tituloForm.vue ~ line 87 ~ addDiretor ~ entity.diretor", entity.diretor)        
        }

        const addClasse = (classe:any) => {
            entity.classificacao = classe;
            console.log("🚀 ~ file: tituloForm.vue ~ line 92 ~ addClasse ~ entity.classificacao", entity.classificacao)        }

        watch(() => props.editedId, (newVal) => {
            getById(newVal);
        });

        return {
            entity,
            erros,
            formSave,
            addAtor,
            addDiretor,
            addClasse,
            openModalAtor
        };
    },
    components: { AtorModalComponent, DiretorSelect, ClasseSelect }
})
</script>
