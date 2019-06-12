package negocio.excecao.cliente.funcionario;

import negocio.excecao.cliente.DadosInvalidosException;

public class SalarioNegativoException extends DadosInvalidosException {
    public SalarioNegativoException(){
        super("O sal�rio n�o pode ser um n�mero negativo");
    }
}
