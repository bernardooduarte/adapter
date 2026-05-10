package com.bernardoduarte.facade;

public abstract class TaxaCambioFactory {
	public abstract TaxaCambio criarTaxa(double valorEmReais);

	public abstract FormatadorValor criarFormatadorValor();
}