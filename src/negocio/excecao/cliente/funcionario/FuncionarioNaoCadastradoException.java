package negocio.excecao.cliente.funcionario;

public class FuncionarioNaoCadastradoException extends Exception {
    public FuncionarioNaoCadastradoException(){
        super("Funcion�rio n�o cadastrado.");
    }
}
