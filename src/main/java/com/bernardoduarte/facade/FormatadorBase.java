package com.bernardoduarte.facade;

public class FormatadorBase extends FormatadorValor {

	@Override
	public String formatar(double valor) {
		return String.format("%.2f", valor);
	}
}