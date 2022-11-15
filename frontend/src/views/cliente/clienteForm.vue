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

                    <div class="col-sm-12 d-flex align-items-center justify-content-center mb-5">
                        <div>
                            <input type="radio" class="btn-check" name="checkType" id="checkSocio" @change="toggleSocio" autocomplete="off">
                            <label class="btn btn-outline-primary" for="checkSocio">Sou um sócio</label><br>
                        </div>
                        <div>
                            <input type="radio" class="btn-check" name="checkType" id="checkDependente" @change="toggleDependente" autocomplete="off">
                            <label class="btn btn-outline-primary" for="checkDependente">Sou um dependente</label><br>
                        </div>
                    </div>

                    <div class="mb-5 col-sm-12">
                        <fieldset id="socioInformation" class="d-none col-sm-12 d-flex flex-wrap gap-3">
                            <legend class="small">Preencha também estas informações</legend>
                            <div class="col-md-5 form-floating mb-3">
                                <input type="text" name="cpf" id="cpf" class="form-control" v-model="entity.cpf" placeholder="cpf" >
                                <label for="cpf" class="form-label">CPF</label>
                            </div>

                            <div class="col-md-5 form-floating mb-3">
                                <input type="tel" name="telefone" id="telefone" class="form-control" v-model="entity.telefone" placeholder="telefone" >
                                <label for="telefone" class="form-label">Telefone</label>
                            </div>

                            <div class="col-sm-12 form-floating mb-3">
                                <input type="text" name="endereco" id="endereco" class="form-control" v-model="entity.endereco" placeholder="endereco" >
                                <label for="endereco" class="form-label">Endereço</label>
                            </div>


                        </fieldset>
                        
                        <fieldset id="dependenteInformation" class="d-none col-sm-12">
                            <legend class="small mb-5">Preencha também estas informações</legend>
                            <label for="selectSocio">Selecione um sócio</label>
                            <SocioSelect @changeSelect="addSocio" :value="entity.socio?.id"/>
                        </fieldset>
                    </div>

                </modal-body>
                <modal-footer>
                    <button class="btn btn-outline-danger" type="button" @click="closeModal">Cancelar</button>
                    <button class="btn btn-primary" type="submit">Salvar</button>
                </modal-footer>
            </form>
        </my-modal>
    </transition>
</template>

<script lang="ts">
import {defineComponent, ref, watch } from 'vue';

import useRequests from '@/composables/requests';
import Swal from 'sweetalert2';
import Cliente from '@/models/cliente';
import Socio from '@/models/cliente';
import SocioSelect from '@/components/socioSelect/socioSelectComponent.vue';
import Dependente from '@/models/dependente';
export default defineComponent({
    name: "ClienteForm",
    props: {
        open: {
            type: Boolean,
            required: true
        },
        editedClient: {
            type: Object,
            default: new Socio()
        }
    },
    components: { SocioSelect },
    emits: ["saved", "close"],
    setup(props, { emit }) {

        const { erros, save, getById, entity, resetEntity } = useRequests(
            props.editedClient.constructor.name === "Socio" ? Socio : Dependente
        );

        const classificacao = ref();

        const openModalCliente = ref(false);

        const modalDependente = ref(false);

        const toggleSocio = () => {

            const checkSocio = document.getElementById("checkSocio");

            const dependenteInformation = document.getElementById("dependenteInformation");
            const socioInformation = document.getElementById("socioInformation");

            if(checkSocio?.checked){
                if(!dependenteInformation?.classList.contains("d-none")) dependenteInformation?.classList.add("d-none");
                socioInformation?.classList.toggle("d-none");
            }
            else socioInformation?.classList.add("d-none");
        }

        const toggleDependente = () => {

            const checkDependente = document.getElementById("checkDependente");

            const dependenteInformation = document.getElementById("dependenteInformation");
            const socioInformation = document.getElementById("socioInformation");

            if(checkDependente?.checked){
                socioInformation?.classList.add("d-none");
                dependenteInformation?.classList.remove("d-none");
            }

            else dependenteInformation?.classList.add("d-none");

        }

        const closeModal = () =>{
            resetEntity();
            emit('close');
        }

        const addSocio = (socio:any) => {
            entity.value.socio = socio;
        }

        const formSave = async () => {
            document.querySelectorAll("input[type=radio]:checked").checked = false;
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
            addSocio,
            toggleSocio,
            toggleDependente
        };
    },
})
</script>
