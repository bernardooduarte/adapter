package com.bernardoduarte.visitor;

public class FormatadorEuro extends FormatadorEncadeado {

	public FormatadorEuro(FormatadorValor formatadorInterno) {
		super(formatadorInterno);
	}

	@Override
	public String formatar(double valor) {
		return "EUR " + formatadorInterno.formatar(valor);
	}
}
