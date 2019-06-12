package interfaces;

import negocio.entidade.Cliente;
import negocio.entidade.Funcionario;
import negocio.entidade.Venda;
import negocio.entidade.produto.Produto;

import java.util.ArrayList;

public interface IRepositorioVenda {


    /**
     * M�todo que adiciona uma venda
     * @param venda
     */
    void adicionarVenda(Venda venda);

    /**
     * M�todo para ver o lucro mensal
     * @param mes
     * @return retorna o lucro mensal
     */
    double verLucroMensal(int mes);

    /**
     * M�todo para ver o lucro anual
     * @param ano
     * @return retorna o lucro anual
     */
    double verLucroAnual(int ano);

    /**
     * M�todo que determina o funcion?rio do m?s
     * @param mes
     * @return retorna o funcion?rio do m?s
     */
    Funcionario determinarFuncionarioDoMes(int mes);

    /**
     * M�todo que determina o cliente fiel
     * @param mes
     * @return retorna o cliente fiel
     */
    Cliente determinarClienteFiel(int mes);

    /**
     * M�todo que busca os cliente que possuem pend?ncias
     * @return retorna um ArrayList com todos os clientes com pend?ncia
     */
    ArrayList<Cliente> buscarClientesComPendencia();

    /**
     * M�todo que busca os produtos mais vendidos em um determinado m?s
     * @param mes
     * @return retorna um ArrayList com os produtos mais vendidos
     */
    ArrayList<Produto> buscarProdutosMaisVendidos(int mes);

    /**
     * M�todo para salvar os dados da classe repositorioVenda
     */
    void salvarDados();

    /**
     * M�todo que faz a leitura dos dados da classe repositorioVenda
     */
    void lerDados();

}
