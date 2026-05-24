package com.bernardoduarte.adapter;

public class TaxaCambioAdapter extends TaxaCambio {

	private final TaxaCambioExterna taxaExterna;

	public TaxaCambioAdapter(TaxaCambioExterna taxaExterna) {
		super(taxaExterna.getMoeda(), converterValor(taxaExterna.getValorEmReais()), new FormatadorBase());
		this.taxaExterna = taxaExterna;
	}

	public TaxaCambioExterna getTaxaExterna() {
		return taxaExterna;
	}

	private static double converterValor(String valor) {
		return Double.parseDouble(valor.replace(',', '.'));
	}
}