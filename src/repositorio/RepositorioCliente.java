package repositorio;


import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.ConnectionFactory;
import fachada.FachadaGerente;
import interfaces.IRepositorioCliente;
import negocio.entidade.Cliente;
import negocio.entidade.Contato;
import negocio.entidade.Funcionario;
import negocio.excecao.cliente.DadosInvalidosException;
import negocio.excecao.cliente.contato.ContatoInvalidoException;
import negocio.excecao.cliente.funcionario.FuncionarioJaCadastradoException;
import negocio.excecao.cliente.funcionario.GerenteJaCadastradoException;

/**
 * Esta classe manipula? e armazena objetos do tipo cliente.
 * @author �verton Vieira
 * @version 2.00
 */
public class RepositorioCliente implements IRepositorioCliente, Serializable{

    private static RepositorioCliente repCliente;

    private ArrayList<Cliente> listaClientes;
    private ArrayList<Cliente> clientesFieis;

    /**
     * Construtor RepositorioCliente
     */
    private RepositorioCliente() {
        this.listaClientes = new ArrayList<Cliente>();
        this.clientesFieis = new ArrayList<Cliente>();
    }

    public static RepositorioCliente getInstance(){
        if(repCliente == null){
            return repCliente = new RepositorioCliente();
        }else{
            return repCliente;
        }
    }
    //banco implementado
    @Override
    public int procurarCliente(Cliente cliente) { // verifica se existe um cliente no banco e se eh ativo/// FUNCIONANDO
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try{
            stmt = conexao.prepareStatement("SELECT * FROM cliente WHERE cpf = ? ");
            stmt.setString(1,cliente.getCpf());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                if (rs.getInt("ativo") == 1) {
                    return 1;
                }
            }
        }catch (SQLException e){
            e.getMessage();
            e.printStackTrace();
        }finally {
            ConnectionFactory.closeConnection(conexao,stmt);
        }
        return -1;
    }

    // banco implementado
    @Override
    public void adicionarCliente(Cliente cliente) {// adiciona cliente no banco // funcionando
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement("INSERT INTO contato VALUES (?, ?, ?)");
            stmt.setString(1, cliente.getContato().getTelefonePrincipal());
            stmt.setString(2, cliente.getContato().getTelefoneAlternativo());
            stmt.setString(3, cliente.getContato().getEmail());

            stmt.executeUpdate();

            stmt = conexao.prepareStatement("INSERT INTO cliente VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, cliente.getCpf());
            stmt.setString(2, cliente.getNome());
            stmt.setBoolean(3, cliente.getFiel());
            stmt.setBoolean(4, cliente.getAtivo());
            stmt.setString(5,cliente.getContato().getEmail());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally{
            ConnectionFactory.closeConnection(conexao, stmt);
        }
    }
    // banco implementado
    @Override
    public void adicionarClienteFiel(Cliente cliente) { // Atualiza status de fiel em um cliente // FUNCIONANDO
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try{
            stmt = conexao.prepareStatement("UPDATE cliente SET fiel = ? WHERE cpf = ?");
            stmt.setInt(1,1);
            stmt.setString(2,cliente.getCpf());

            stmt.executeUpdate();

        }catch (SQLException e){
            e.getMessage();
            e.printStackTrace();
        }finally {
            ConnectionFactory.closeConnection(conexao,stmt);
        }
    }
    // banco implementado
    @Override
    public Cliente buscarPorCpf(String cpf) { // busca um cliente no banco pelo cpf // FUNCIONANDO
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try{
            stmt = conexao.prepareStatement("SELECT * FROM cliente cli join contato cont on cli.contato = cont.email WHERE cli.cpf = ?");
            stmt.setString(1,cpf);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Contato contatoBD = new Contato (rs.getString("telefone_principal"),rs.getString("telefone_alternativo"),rs.getString("email"));
                Cliente clienteBD = new Cliente(rs.getString("nome"),rs.getString("cpf"),contatoBD);
                return clienteBD;
            }

        }catch (SQLException e){
            e.getMessage();
            e.printStackTrace();
        }finally {
            ConnectionFactory.closeConnection(conexao,stmt);
        }
        return null;
    }

    @Override
    public Funcionario buscarPorCpfFunc(String cpf){
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try{
            stmt = conexao.prepareStatement("SELECT * FROM funcionario func join contato cont on func.contato = cont.email WHERE func.cpf = ?");
            stmt.setString(1,cpf);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Contato contatoBD = new Contato (rs.getString("telefone_principal"),rs.getString("telefone_alternativo"),rs.getString("email"));
                Funcionario funcEncontrado = new Funcionario(rs.getString("nome"),rs.getString("cpf"),contatoBD,rs.getString("senha"),rs.getDouble("salario"),rs.getBoolean("cargo_gerente"));
                return funcEncontrado;
            }

        }catch (SQLException e){
            e.getMessage();
            e.printStackTrace();
        }finally {
            ConnectionFactory.closeConnection(conexao,stmt);
        }
        return null;
    }

    //banco implementado
    @Override
    public ArrayList<Cliente> listarPorNomeCliente(String nome) { // lista cliente com o nome passado // FUNCIONANDO

        ArrayList<Cliente> clientesEncontrados = new ArrayList<Cliente>();
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try{
            stmt = conexao.prepareStatement("SELECT * FROM cliente cli join contato cont on cli.contato = cont.email WHERE cli.nome = ?");
            stmt.setString(1,nome);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Contato contatoBD = new Contato (rs.getString("telefone_principal"),rs.getString("telefone_alternativo"),rs.getString("email"));
                Cliente clienteBD = new Cliente(rs.getString("nome"),rs.getString("cpf"),contatoBD,rs.getBoolean("fiel"));
                clientesEncontrados.add(clienteBD);
            }
            return clientesEncontrados;
        }catch (SQLException e){
            e.getMessage();
            e.printStackTrace();
        }finally {
            ConnectionFactory.closeConnection(conexao,stmt);
        }

        return null;
    }
    //banco implementado
    @Override
    public ArrayList<Cliente> listarPorNomeFuncionario(String nome){ // lista funcionarios por nome // FUNCIONANDO

        ArrayList<Cliente> funcionariosEncontrados = new ArrayList<Cliente>();
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try{
            stmt = conexao.prepareStatement("SELECT * FROM funcionario func join contato cont on func.contato = cont.email WHERE func.nome = ?");
            stmt.setString(1,nome);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Contato contatoBD = new Contato (rs.getString("telefone_principal"),rs.getString("telefone_alternativo"),rs.getString("email"));
                Funcionario clienteBD = new Funcionario(rs.getString("nome"),rs.getString("cpf"),contatoBD,rs.getString("senha"),rs.getDouble("salario"),rs.getBoolean("cargo_gerente"));
                funcionariosEncontrados.add(clienteBD);
            }
            return funcionariosEncontrados;
        }catch (SQLException e){
            e.getMessage();
            e.printStackTrace();
        }finally {
            ConnectionFactory.closeConnection(conexao,stmt);
        }
        return null;

//        for (int i = 0; i < this.listaClientes.size(); i++) {
//            if (this.listaClientes.get(i) instanceof Funcionario) {
//                if (this.listaClientes.get(i).getNome().toLowerCase().startsWith(nome.toLowerCase())) {
//                    funcionariosEncontrados.add(this.listaClientes.get(i));
//                }
//            }
//        }
//        return funcionariosEncontrados;
    }
    //banco implementado
    @Override
    public ArrayList<Cliente> listarTodosOsFuncionarios(){ // lista todos os funcionarios // FUNCIONANDO
        ArrayList<Cliente> funcionarios = new ArrayList<Cliente>();

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try{
            stmt = conexao.prepareStatement("SELECT * FROM funcionario func join contato cont on func.contato = cont.email");
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Contato contatoBD = new Contato (rs.getString("telefone_principal"),rs.getString("telefone_alternativo"),rs.getString("email"));
                Funcionario clienteBD = new Funcionario(rs.getString("nome"),rs.getString("cpf"),contatoBD,rs.getString("senha"),rs.getDouble("salario"),rs.getBoolean("cargo_gerente"));
                funcionarios.add(clienteBD);
            }
            return funcionarios;
        }catch (SQLException e){
            e.getMessage();
            e.printStackTrace();
        }finally {
            ConnectionFactory.closeConnection(conexao,stmt);
        }
        return null;
    }
    //banco implementado
    @Override
    public ArrayList<Cliente> listarClientesFieis() { // lista clientes fieis // FUNCIONANDO
        ArrayList<Cliente> fieis = new ArrayList<Cliente>();

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try{
            stmt = conexao.prepareStatement("SELECT * FROM cliente cli join contato cont on cli.contato = cont.email WHERE fiel = true");
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Contato contatoBD = new Contato (rs.getString("telefone_principal"),rs.getString("telefone_alternativo"),rs.getString("email"));
                Cliente clienteBD = new Cliente(rs.getString("nome"),rs.getString("cpf"),contatoBD,rs.getBoolean("fiel"));
                fieis.add(clienteBD);
            }
            return fieis;
        }catch (SQLException e){
            e.getMessage();
            e.printStackTrace();
        }finally {
            ConnectionFactory.closeConnection(conexao,stmt);
        }
        return null;
    }

    @Override
    public void alterarCliente(Cliente Cliente, int indiceCliente) {
        this.listaClientes.set(indiceCliente, Cliente);
    }

    @Override
    public void desabilitarCliente(int indiceCliente) {
        this.listaClientes.get(indiceCliente).setAtivo(false);
    }

    @Override
    public void habilitarCliente(int indiceCliente) {
        this.listaClientes.get(indiceCliente).setAtivo(true);
    }

    //banco implementado
    @Override
    public boolean verificarGerenteExistente(){
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try{
            stmt = conexao.prepareStatement("SELECT * FROM funcionario WHERE cargo_gerente = true");
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return true;
            }else{
                return false;
            }
        }catch (SQLException e){
            e.getMessage();
            e.printStackTrace();
        }finally {
            ConnectionFactory.closeConnection(conexao,stmt);
        }
        return false;
    }

    // Metodo auxiliar apenas para testes
    public void imprimirListaClientes() {
        for(int i = 0; i < this.listaClientes.size(); i++) {
            System.out.println(this.listaClientes.get(i) + "\n");
        }
    }
    
    @Override
    public void salvarDadosFieis() {
        try{
            FileOutputStream file = new FileOutputStream("listaClientesFieis.dat");
            ObjectOutputStream os = new ObjectOutputStream(file);
            os.writeObject(this.clientesFieis);
            os.close();
        }catch(IOException ioException){

        }

    }
    
    @Override
    public void lerDadosFieis(){
        try{
            FileInputStream file = new FileInputStream("listaClientesFieis.dat");
            ObjectInputStream is = new ObjectInputStream(file);
            ArrayList<Cliente> clientesFieis = (ArrayList<Cliente>) is.readObject();
            this.clientesFieis = clientesFieis;
            is.close();
        }
        catch (FileNotFoundException fileNotFound) {
        }
        catch (IOException ioException) {
        }
        catch (ClassNotFoundException classNotFound) {
        }

    }

    public static void main(String[] args) {
            Contato cont = new Contato("55555555555", "55555555555", "basd123@gmail.com");
            Funcionario func = new Funcionario("Joazim Silva" , "55555555555" , cont ,"123321", 850.0, false);
            FachadaGerente.getInstance().getFachadaGerente().getRepositorioCliente().adicionarFuncionario(func);
    }

    //Implementado no banco
    @Override
    public int procurarFuncionario(Funcionario func){
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try{
            stmt = conexao.prepareStatement("SELECT * FROM funcionario WHERE cpf = ? ");
            stmt.setString(1, func.getCpf());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                if (rs.getInt("ativo") == 1) {
                    return 1;
                }
            }
        }catch (SQLException e){
            e.getMessage();
            e.printStackTrace();
        }finally {
            ConnectionFactory.closeConnection(conexao,stmt);
        }
        return -1;
    }

    //Implementado no banco
    @Override
    public void adicionarFuncionario(Funcionario func){
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement("INSERT INTO contato VALUES (?, ?, ?)");
            stmt.setString(1, func.getContato().getTelefonePrincipal());
            stmt.setString(2, func.getContato().getTelefoneAlternativo());
            stmt.setString(3, func.getContato().getEmail());

            stmt.executeUpdate();

            stmt = conexao.prepareStatement("INSERT INTO funcionario VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setDouble(1, func.getSalarioComBonificacao());
            stmt.setBoolean(2, func.getFuncDoMes());
            stmt.setDouble(3, func.getSalario());
            stmt.setBoolean(4, func.getCargoGerente());
            stmt.setString(5, func.getNome());
            stmt.setString(6, func.getCpf());
//            stmt.setBoolean(6, func.getFiel());
//            stmt.setBoolean(7, func.getAtivo());
            stmt.setString(7, func.getSenha());
            stmt.setString(8, func.getContato().getEmail());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionFactory.closeConnection(conexao, stmt);
        }

    }
}


