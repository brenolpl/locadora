export default class Classe{
    id:number;
    nome: string;
    valor:number;
    prazoDevolucao: Date;

    constructor() {
        this.id = 0;
        this.nome = "",
        this.valor = 0,
        this.prazoDevolucao = new Date();

    }

}