package negocio.excecao.cliente;

public class ClienteJaCadastradoException extends ClienteException {
    
    public ClienteJaCadastradoException() {
        super("Esse cliente j� possui um cadastro.");
    }
}
