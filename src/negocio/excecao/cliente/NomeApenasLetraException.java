package negocio.excecao.cliente;

public class NomeApenasLetraException extends DadosInvalidosException {
    
    public NomeApenasLetraException() {
        super("O nome s� pode conter letras");
    }
}
