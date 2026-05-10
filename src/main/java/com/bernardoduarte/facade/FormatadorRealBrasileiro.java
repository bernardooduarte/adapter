package com.bernardoduarte.facade;

public class FormatadorRealBrasileiro extends FormatadorEncadeado {

	public FormatadorRealBrasileiro(FormatadorValor formatadorInterno) {
		super(formatadorInterno);
	}

	@Override
	public String formatar(double valor) {
		return "R$ " + formatadorInterno.formatar(valor);
	}
}