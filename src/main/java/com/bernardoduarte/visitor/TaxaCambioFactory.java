package com.bernardoduarte.visitor;

public abstract class TaxaCambioFactory {
	public abstract TaxaCambio criarTaxa(double valorEmReais);

	public abstract FormatadorValor criarFormatadorValor();
}
