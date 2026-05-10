package com.bernardoduarte.facade;

public class LibraEsterlina extends TaxaCambio {

	public LibraEsterlina(double valorEmReais, FormatadorValor formatador) {
		super("GBP", valorEmReais, formatador);
	}
}