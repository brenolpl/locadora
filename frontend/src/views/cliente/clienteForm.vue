<template>
    <transition>
        <my-modal v-show="open">
            <form v-on:submit.prevent="formSave">
                <modal-header>
                    Inserir Cliente
                    <button type="button" class="btn-close" @click="closeModal"></button>
                </modal-header>
                <modal-body class="d-flex align-items-center flex-wrap gap-3">
                    <div class="col-md-7 col-xxl-12 form-floating mb-3">
                        <input type="text" name="nome" id="nome" class="form-control" v-model="entity.nome" placeholder="nome" >
                        <label for="nome" class="form-label">Nome</label>
                    </div>
                    <div class="col-md-5 col-xxl-5 form-floating mb-3">
                        <input type="text" name="inscricao" id="inscricao" class="form-control" v-model="entity.numInscricao" >
                        <label for="inscricao" class="form-label">N° Inscrição</label>
                    </div>
                    <div class="form-floating mb-3 col-xl-5">
                        <input type="date" name="nascimento" id="nascimento" class="form-control" v-model="entity.date" placeholder="xx/xx/xxxx" required>
                        <label for="nome" class="form-label">Nascimento</label>
                    </div>
                    <div class="mb-3 col-xl-5">
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="sexo" id="sexoM" value="masculino"  v-model="entity.sexo">
                            <label class="form-check-label" for="sexoM">
                                Masculino
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="sexo" id="sexoF" value="feminino" v-model="entity.sexo">
                            <label class="form-check-label" for="sexoF">
                                Feminino
                            </label>
                        </div>
                    </div>

                    <div class="col-sm-12">
                        <a class="btn btn-primary fw-bold" @click="modalDependente = true">Adicionar Dependentes</a>
                    </div>

                </modal-body>
                <modal-footer>
                    <button class="btn btn-outline-danger" type="button" @click="closeModal">Cancelar</button>
                    <button class="btn btn-primary" type="submit">Salvar</button>
                </modal-footer>
            </form>
        </my-modal>
    </transition>
    <DependenteModalComponent :open="modalDependente" @close="modalDependente = !modalDependente" @saved="addDependente"/>
</template>

<script lang="ts">
import {defineComponent, provide, ref, watch } from 'vue';

import useRequests from '@/composables/requests';
import DependenteModalComponent from '../../components/dependenteModal/dependenteModalComponent.vue';
import Swal from 'sweetalert2';
import Cliente from '@/models/cliente';
export default defineComponent({
    name: "ClienteForm",
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
    components: { DependenteModalComponent },
    emits: ["saved", "close"],
    setup(props, { emit }) {

        const { erros, save, getById, entity, resetEntity } = useRequests(Cliente);

        const classificacao = ref();

        const openModalCliente = ref(false);

        const modalDependente = ref(false);

        const closeModal = () =>{
            resetEntity();
            emit('close');
        }

        provide("selectedDependents", entity.value);

        const addDependente = (dependentesList:any) => {
            entity.value.dependentes = dependentesList;
            modalDependente.value = !modalDependente.value;
        }

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

        watch(() => props.editedId, (newVal) => {
            if(newVal != 0) getById(newVal);
        });

        return {
            entity,
            erros,
            formSave,
            classificacao,
            openModalCliente,
            modalDependente,
            closeModal,
            addDependente
        };
    },
})
</script>
