package com.bernardoduarte.visitor;

public class ExibicaoTaxaCambioVisitor implements TaxaCambioVisitor {

	@Override
	public String visitar(DolarAmericano dolarAmericano) {
		return "Moeda: " + dolarAmericano.getMoeda() + " | Taxa: " + dolarAmericano.getValorFormatado();
	}

	@Override
	public String visitar(Euro euro) {
		return "Moeda: " + euro.getMoeda() + " | Taxa: " + euro.getValorFormatado();
	}

	@Override
	public String visitar(LibraEsterlina libraEsterlina) {
		return "Moeda: " + libraEsterlina.getMoeda() + " | Taxa: " + libraEsterlina.getValorFormatado();
	}
}
