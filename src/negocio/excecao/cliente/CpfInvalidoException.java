package negocio.excecao.cliente;

public class CpfInvalidoException extends DadosInvalidosException {

    public CpfInvalidoException(String cpf) {
        super(cpf + " n�o � um CPF v�lido.");
    }
}
