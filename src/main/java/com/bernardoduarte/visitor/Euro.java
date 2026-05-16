package com.bernardoduarte.visitor;

public class Euro extends TaxaCambio {

	public Euro(double valorEmReais, FormatadorValor formatador) {
		super("EUR", valorEmReais, formatador);
	}

	@Override
	public String aceitar(TaxaCambioVisitor visitor) {
		return visitor.visitar(this);
	}
}
