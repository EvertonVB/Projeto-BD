package negocio.excecao.cliente;

public class CpfApenasNumeroException extends DadosInvalidosException{
    
    public CpfApenasNumeroException() {
        super("O CPF s� pode conter n�meros");
    }
    
}
