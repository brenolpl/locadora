import Flex from "./flex";

export default class Cliente extends Flex{
    numInscricao: number;
    nome:string;
    date: Date;
    sexo:string;
    dependentes:Array<Cliente>;
    ativo:boolean

    constructor() {
        super("/cliente/");
        this.numInscricao = 0,
        this.nome = "",
        this.date = new Date();
        this.sexo = "";
        this.dependentes = new Array<Cliente>();
        this.ativo = true;
    }

}