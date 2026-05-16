package com.bernardoduarte.visitor;

public class EuroFactory extends TaxaCambioFactory {
	@Override
	public TaxaCambio criarTaxa(double valorEmReais) {
		return new Euro(valorEmReais, criarFormatadorValor());
	}

	@Override
	public FormatadorValor criarFormatadorValor() {
		return new FormatadorEuro(new FormatadorBase());
	}
}
