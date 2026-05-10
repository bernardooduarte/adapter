package com.bernardoduarte.facade;

public class DolarAmericano extends TaxaCambio {

	public DolarAmericano(double valorEmReais, FormatadorValor formatador) {
		super("USD", valorEmReais, formatador);
	}
}