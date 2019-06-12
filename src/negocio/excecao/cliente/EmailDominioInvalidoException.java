package negocio.excecao.cliente;

/**
 *
 * @author EVERTON VIEIRA
 */
public class EmailDominioInvalidoException extends Exception {
    
    public EmailDominioInvalidoException(String dominio) {
        super(dominio + " n�o � um dominio v�lido.");
    }
}
