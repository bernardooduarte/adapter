package com.bernardoduarte.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GerenciadorTaxaCambioFacade {

	private final List<TaxaCambio> taxas = new ArrayList<>();
	private final Map<String, TaxaCambioFactory> fabricas = new HashMap<>();

	public GerenciadorTaxaCambioFacade() {
		fabricas.put("USD", new DolarAmericanoFactory());
		fabricas.put("EUR", new EuroFactory());
		fabricas.put("GBP", new LibraEsterlinaFactory());
	}

	public TaxaCambio registrarTaxa(String moeda, double valorEmReais) {
		TaxaCambio taxa = criarTaxa(moeda, valorEmReais);
		taxas.add(taxa);
		return taxa;
	}

	public void atualizarTaxa(String moeda, double novoValorEmReais) {
		for (TaxaCambio taxa : taxas) {
			if (taxa.getMoeda().equalsIgnoreCase(moeda)) {
				taxa.setValorEmReais(novoValorEmReais);
				return;
			}
		}

		System.out.println("Moeda nao encontrada no gerenciador: " + moeda);
	}

	public List<TaxaCambio> listarTaxas() {
		return List.copyOf(taxas);
	}

	public void exibirPainel() {
		System.out.println("=== Gerenciador de Taxa de Cambio (Facade) ===");
		for (TaxaCambio taxa : taxas) {
			taxa.exibirInfo();
		}
		System.out.println("=============================================");
	}

	private TaxaCambio criarTaxa(String moeda, double valorEmReais) {
		TaxaCambioFactory fabrica = fabricas.get(moeda.toUpperCase());
		if (fabrica == null) {
			throw new IllegalArgumentException("Moeda nao suportada: " + moeda);
		}
		return fabrica.criarTaxa(valorEmReais);
	}
}