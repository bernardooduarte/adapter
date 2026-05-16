package com.bernardoduarte.visitor;

public class LibraEsterlina extends TaxaCambio {

	public LibraEsterlina(double valorEmReais, FormatadorValor formatador) {
		super("GBP", valorEmReais, formatador);
	}

	@Override
	public String aceitar(TaxaCambioVisitor visitor) {
		return visitor.visitar(this);
	}
}
