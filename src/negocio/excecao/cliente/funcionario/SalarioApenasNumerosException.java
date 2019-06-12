package negocio.excecao.cliente.funcionario;

import negocio.excecao.cliente.DadosInvalidosException;

public class SalarioApenasNumerosException extends DadosInvalidosException {
    public SalarioApenasNumerosException() {
        super("O s�lario deve conter apenas n�meros.");
    }
}
