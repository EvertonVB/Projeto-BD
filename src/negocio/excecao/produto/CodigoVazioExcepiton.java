package negocio.excecao.produto;

public class CodigoVazioExcepiton extends ProdutoInvalidoException {
    
    public CodigoVazioExcepiton() {
        super("O c�digo n�o pode ficar vazio");
    }
    
}
