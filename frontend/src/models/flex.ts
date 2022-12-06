export default abstract class Flex{
    path:string;
    id:number|null;

    constructor(path:string){
        this.path = path;
        this.id = null;
    }
}