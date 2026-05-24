package com.bernardoduarte.adapter;

public class FormatadorDolarAmericano extends FormatadorEncadeado {

	public FormatadorDolarAmericano(FormatadorValor formatadorInterno) {
		super(formatadorInterno);
	}

	@Override
	public String formatar(double valor) {
		return "US$ " + formatadorInterno.formatar(valor);
	}
}

