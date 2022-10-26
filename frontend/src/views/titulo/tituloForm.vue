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
                        <textarea name="sinopse" id="sinopse" class="form-control" v-model="entity.sinopse" style="min-height:200px"></textarea>
                        <label for="nome" class="form-label">Sinopse</label>
                    </div>
                    <div class="col-md-5 col-xl-3 mb-3">
                        <label for="selectCategoria" class="form-label">Selecione uma categoria</label>
                        <select class="form-select" aria-label="Selecione uma categoria" id="selectCategoria" v-model="entity.categoria">
                            <option disabled selected>Selecione uma categoria</option>
                            <option value="Romance">Romance</option>
                            <option value="Terror">Terror</option>
                            <option value="Suspense">Supense</option>
                        </select>
                    </div>
                    <div class="col-md-5 col-xl-3 mb-3">
                        <label for="selectClasse" class="form-label">Selecione uma classificação</label>
                        <ClasseSelect @changeSelect="addClasse" :value="entity.classificacao?.id"/>
                    </div>
                    <div class="col-md-5 col-xl-3 mb-3">
                        <label for="selectDiretor" class="form-label">Selecione um diretor</label>
                        <DiretorSelect @changeSelect="addDiretor" :value="entity.diretor?.id" />
                    </div>
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
    <AtorModalComponent :actorsSelected="entity.atores" :open="openModalAtor" @close="openModalAtor = !openModalAtor" @saved="addAtor"/>

</template>

<script lang="ts">
import {defineComponent, ref, watch } from 'vue';

import useRequests from '@/composables/requests';
import Titulo from '@/models/titulo';
import AtorModalComponent from '../../components/atorModal/atorModalComponent.vue';
import DiretorSelect from '../../components/diretorSelect/diretorSelectComponent.vue';
import ClasseSelect from '../../components/classeSelect/classeSelectComponent.vue';
import Swal from 'sweetalert2';
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

        const classificacao = ref();

        const openModalAtor = ref(false);

        const formSave = async () => {
            await save(entity.value);
            Swal.fire({
                icon: 'success',
                title: 'Titulo adicionado com sucesso!',
                showConfirmButton: false,
                timer: 1500
            })
            emit("saved");
        };

        const addAtor = (atoresList:any) => {
            entity.value.atores = atoresList;
            openModalAtor.value = !openModalAtor.value;
        }

        const addDiretor = (director:any) => {
            entity.value.diretor = director;
        }

        const addClasse = (classe:any) => {
            entity.value.classificacao = classe;
        }

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
            classificacao,
            openModalAtor
        };
    },
    components: { AtorModalComponent, DiretorSelect, ClasseSelect }
})
</script>
