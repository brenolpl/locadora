import Flex from "./flex";

export default class Classe extends Flex{
    nome: string;
    valor:number;
    prazoDevolucao: Date;

    constructor() {
        super("/classificacao/");
        this.nome = "",
        this.valor = 0,
        this.prazoDevolucao = new Date();
    }

}