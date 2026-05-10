package com.bernardoduarte.facade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FacadeApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void deveRegistrarTaxaComFacade() {
		GerenciadorTaxaCambioFacade facade = new GerenciadorTaxaCambioFacade();

		TaxaCambio taxa = facade.registrarTaxa("USD", 5.17);

		assertNotNull(taxa);
		assertEquals("USD", taxa.getMoeda());
		assertEquals(5.17, taxa.getValorEmReais());
		assertEquals(1, facade.listarTaxas().size());
	}

	@Test
	void deveAtualizarTaxaExistente() {
		GerenciadorTaxaCambioFacade facade = new GerenciadorTaxaCambioFacade();
		TaxaCambio taxa = facade.registrarTaxa("GBP", 6.55);

		facade.atualizarTaxa("GBP", 6.90);

		assertEquals(6.90, taxa.getValorEmReais());
	}

	@Test
	void deveInformarQuandoMoedaNaoExiste() {
		GerenciadorTaxaCambioFacade facade = new GerenciadorTaxaCambioFacade();
		ByteArrayOutputStream saida = new ByteArrayOutputStream();
		PrintStream saidaOriginal = System.out;

		try {
			System.setOut(new PrintStream(saida, true));
			facade.atualizarTaxa("JPY", 1.0);
		} finally {
			System.setOut(saidaOriginal);
		}

		assertEquals("Moeda nao encontrada no gerenciador: JPY", saida.toString().trim());
	}

	@Test
	void deveExibirPainelComTaxas() {
		GerenciadorTaxaCambioFacade facade = new GerenciadorTaxaCambioFacade();
		facade.registrarTaxa("USD", 5.17);
		facade.registrarTaxa("EUR", 5.62);

		assertDoesNotThrow(facade::exibirPainel);
		assertEquals(2, facade.listarTaxas().size());
		assertEquals("USD", facade.listarTaxas().get(0).getMoeda());
		assertEquals("EUR", facade.listarTaxas().get(1).getMoeda());
	}

	@Test
	void deveLancarExcecaoParaMoedaNaoSuportada() {
		GerenciadorTaxaCambioFacade facade = new GerenciadorTaxaCambioFacade();

		assertThrows(IllegalArgumentException.class, () -> facade.registrarTaxa("JPY", 1.0));
	}

}