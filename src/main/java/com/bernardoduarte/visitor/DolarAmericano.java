package com.bernardoduarte.visitor;

public class DolarAmericano extends TaxaCambio {

	public DolarAmericano(double valorEmReais, FormatadorValor formatador) {
		super("USD", valorEmReais, formatador);
	}

	@Override
	public String aceitar(TaxaCambioVisitor visitor) {
		return visitor.visitar(this);
	}
}
