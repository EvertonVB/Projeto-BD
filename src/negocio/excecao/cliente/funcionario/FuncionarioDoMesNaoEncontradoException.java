package negocio.excecao.cliente.funcionario;

public class FuncionarioDoMesNaoEncontradoException extends Exception {
    public FuncionarioDoMesNaoEncontradoException(){
        super("Esse m�s ainda n�o possui um funcion�rio do m�s");
    }
}
