package com.bernardoduarte.facade;

public class Euro extends TaxaCambio {

	public Euro(double valorEmReais, FormatadorValor formatador) {
		super("EUR", valorEmReais, formatador);
	}
}