package com.bernardoduarte.visitor;

public class LibraEsterlinaFactory extends TaxaCambioFactory {
	@Override
	public TaxaCambio criarTaxa(double valorEmReais) {
		return new LibraEsterlina(valorEmReais, criarFormatadorValor());
	}

	@Override
	public FormatadorValor criarFormatadorValor() {
		return new FormatadorLibraEsterlina(new FormatadorBase());
	}
}
