package aulas.Julho._18.Generics.Exercicio;

class Par<T, V> {
    private T primeiro;
    private V segundo;   

    public Par(T primeiro, V segundo) {
        this.primeiro = primeiro;
        this.segundo = segundo;
    }

    public T getPrimeiro() {
        return primeiro;
    }

    public V getSegundo() {
        return segundo;
    }

    


} 