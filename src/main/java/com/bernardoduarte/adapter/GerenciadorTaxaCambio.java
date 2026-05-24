package com.bernardoduarte.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GerenciadorTaxaCambio {

	private final List<TaxaCambio> taxas = new ArrayList<>();
	private final Map<String, TaxaCambioFactory> fabricas = new HashMap<>();

	public GerenciadorTaxaCambio() {
		fabricas.put("USD", new DolarAmericanoFactory());
		fabricas.put("EUR", new EuroFactory());
		fabricas.put("GBP", new LibraEsterlinaFactory());
	}

	public TaxaCambio registrarTaxa(String moeda, double valorEmReais) {
		TaxaCambio taxa = criarTaxa(moeda, valorEmReais);
		taxas.add(taxa);
		return taxa;
	}

	public TaxaCambio adicionarTaxaExterna(TaxaCambioExterna taxaExterna) {
		TaxaCambio taxa = new TaxaCambioAdapter(taxaExterna);
		taxas.add(taxa);
		return taxa;
	}

	public List<TaxaCambio> listarTaxas() {
		return List.copyOf(taxas);
	}

	public String exibirPainel() {
		StringBuilder painel = new StringBuilder();
		painel.append("=== Gerenciador de Taxa de Cambio (Adapter) ===").append(System.lineSeparator());
		for (TaxaCambio taxa : taxas) {
			painel.append("Moeda: ")
				.append(taxa.getMoeda())
				.append(" | Taxa: ")
				.append(taxa.getValorFormatado())
				.append(System.lineSeparator());
		}
		painel.append("==============================================");
		return painel.toString();
	}

	private TaxaCambio criarTaxa(String moeda, double valorEmReais) {
		TaxaCambioFactory fabrica = fabricas.get(moeda.toUpperCase());
		if (fabrica == null) {
			throw new IllegalArgumentException("Moeda nao suportada: " + moeda);
		}
		return fabrica.criarTaxa(valorEmReais);
	}
}