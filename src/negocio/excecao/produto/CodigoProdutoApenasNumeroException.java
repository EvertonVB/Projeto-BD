package negocio.excecao.produto;

public class CodigoProdutoApenasNumeroException extends ProdutoInvalidoException { // retira Produto?
    
    public CodigoProdutoApenasNumeroException() {
        super("O c�digo s� deve conter numeros");
    }
}
