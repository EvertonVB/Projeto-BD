package negocio.excecao.produto;

public class DescricaoTamanhoException extends ProdutoInvalidoException {
    
    public DescricaoTamanhoException() {
        super("A decri��oo n�o pode ser t�o pequena");
    }
}
