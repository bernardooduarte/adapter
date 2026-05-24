package com.bernardoduarte.adapter;

public abstract class FormatadorEncadeado extends FormatadorValor {

	protected final FormatadorValor formatadorInterno;

	protected FormatadorEncadeado(FormatadorValor formatadorInterno) {
		this.formatadorInterno = formatadorInterno;
	}
}

