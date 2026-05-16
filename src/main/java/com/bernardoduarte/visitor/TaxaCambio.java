package com.bernardoduarte.visitor;

public abstract class TaxaCambio {

	protected String moeda;
	protected double valorEmReais;
	protected FormatadorValor formatador;

	public TaxaCambio(String moeda, double valorEmReais, FormatadorValor formatador) {
		this.moeda = moeda;
		this.valorEmReais = valorEmReais;
		this.formatador = formatador;
	}

	public String getMoeda() {
		return moeda;
	}

	public double getValorEmReais() {
		return valorEmReais;
	}

	public void setValorEmReais(double valorEmReais) {
		this.valorEmReais = valorEmReais;
	}

	public void setFormatador(FormatadorValor formatador) {
		this.formatador = formatador;
	}

	public String getValorFormatado() {
		return formatador.formatar(valorEmReais);
	}

	public abstract String aceitar(TaxaCambioVisitor visitor);
}
