package negocio.excecao.cliente;

public class ClienteAtivoException extends ClienteException {
    
    public ClienteAtivoException() {
        super("O cliente n�o pode ser desativado, pois possui pendencias");
    }
}
