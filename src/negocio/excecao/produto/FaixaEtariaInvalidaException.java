package negocio.excecao.produto;

public class FaixaEtariaInvalidaException extends ProdutoInvalidoException {
    
    public FaixaEtariaInvalidaException() {
        super("Faixa et�ria inv�lida");
    }
}
