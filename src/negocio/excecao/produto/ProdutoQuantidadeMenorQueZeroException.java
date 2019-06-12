package negocio.excecao.produto;

public class ProdutoQuantidadeMenorQueZeroException extends ProdutoInvalidoException {
    
    public ProdutoQuantidadeMenorQueZeroException() {
        super("A quantidade n�o pode ser menor que 1");
    }
}
