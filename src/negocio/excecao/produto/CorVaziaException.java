package negocio.excecao.produto;

public class CorVaziaException extends ProdutoInvalidoException{
    
    public CorVaziaException() {
        super("A cor n�o pode ficar vazia");
    }
}
