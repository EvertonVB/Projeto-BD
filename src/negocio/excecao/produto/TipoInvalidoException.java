package negocio.excecao.produto;

public class TipoInvalidoException extends ProdutoInvalidoException {
    
    public TipoInvalidoException() {
        super("O tipo n�o � v�lido");
    }
    
}
