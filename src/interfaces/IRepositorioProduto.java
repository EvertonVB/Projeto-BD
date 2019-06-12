package interfaces;
import negocio.entidade.produto.Produto;

import java.util.ArrayList;

public interface IRepositorioProduto {

    /**
     * Esse m�todo verifica se o produto existe no reposit�rio.
     * @param produto
     * @return
     */
    int procurarProduto(Produto produto);

    /**
     * Esse m�todo adiciona um produto ao reposit�rio
     * @param produto
     */
    void adicionarProduto(Produto produto);

    Produto buscarProdutoPorCodigo(String codigo);

    /**
     * Lista todos os produtos que come�am com o tipo passado como par�metro
     * @param tipo
     * @return retorna um ArrayList de produtos
     */
    ArrayList<Produto> listarProdutosPorTipo(String tipo);

    /**
     * Lista todos os produtos que come�am com a descricao passada como par�metro
     * @param descricao
     * @return retorna um ArrayList de produtos
     */
    ArrayList<Produto> listarProdutosPorDescricao(String descricao);

    /**
     * Lista todos os produtos que come�am com a faixa et�ria passada como par�metro
     * @param faixaEtaria
     * @return retorna um ArrayList de produtos
     */
    ArrayList<Produto> listarProdutosPorFaixaEtaria(String faixaEtaria);

    /**
     * Lista todos os produtos que come�am com a categoria passada como par�metro
     * @param categoria
     * @return retorna um ArrayList de produtos
     */
    ArrayList<Produto> listarProdutosPorCategoria(String categoria);

    /**
     * Esse m�todo substitui um determinado objeto do tipo Produto por um novo objeto do tipo Produto.
     * @param produto
     * @param indiceProduto
     */
    void alterarProduto(Produto produto, int indiceProduto);

    void desabilitarProduto(int indiceProduto); // "remover"

    void salvarDados();

    void lerDados();
}
