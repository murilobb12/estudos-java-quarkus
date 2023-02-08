package aulas.Julho._27solID;

public class ServicoDesenvolvedor {

    Desenvolvedor desenvolvedor;

    public ServicoDesenvolvedor(Desenvolvedor desenvolvedor) {
        this.desenvolvedor = desenvolvedor;
    }

    public void desenvolvedorATrabalhar(){
        desenvolvedor.programar(4);
    }
}
