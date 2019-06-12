package negocio.excecao.cliente.contato;

/**
 *
 * @author EVERTON VIEIRA
 */
public class EmailDominioInvalidoException extends ContatoInvalidoException {
    
    public EmailDominioInvalidoException(String dominio) {
        super(dominio + " n�o � um dominio v�lido.");
    }
}
