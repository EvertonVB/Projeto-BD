package negocio.excecao.venda;

public class ClienteNegadoException extends VendaInvalidaException {

    public ClienteNegadoException(){
        super("Um funcion�rio n�o pode vender para ele mesmo");
    }

}
