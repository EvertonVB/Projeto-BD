package negocio.excecao.produto;

public class ProdutoInativoException extends Exception {
    
    public ProdutoInativoException() {
        super("O produto est� in�tivo");
    }
}
