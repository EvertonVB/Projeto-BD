package negocio.excecao.produto;

public class CorApenasLetraException extends ProdutoInvalidoException {
    
    public CorApenasLetraException() {
        super("Cor n�o pode haver n�meros");
    }
}
