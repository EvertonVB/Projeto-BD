package negocio.excecao.cliente.funcionario;

public class FuncionarioJaCadastradoException extends Exception{
    public FuncionarioJaCadastradoException(){
        super("Funcion�rio j� cadastrado.");
    }
}
