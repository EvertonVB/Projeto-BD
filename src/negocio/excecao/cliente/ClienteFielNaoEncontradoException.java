package negocio.excecao.cliente;

public class ClienteFielNaoEncontradoException extends Exception {
    
    public ClienteFielNaoEncontradoException() {
        super("N�o h� nenhum cliente fiel no momento");
    }
}
