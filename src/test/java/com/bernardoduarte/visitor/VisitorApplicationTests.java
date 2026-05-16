package com.bernardoduarte.visitor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VisitorApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void deveRegistrarTaxaComVisitor() {
		GerenciadorTaxaCambioVisitor visitor = new GerenciadorTaxaCambioVisitor();

		TaxaCambio taxa = visitor.registrarTaxa("USD", 5.17);

		assertNotNull(taxa);
		assertEquals("USD", taxa.getMoeda());
		assertEquals(5.17, taxa.getValorEmReais());
		assertEquals(1, visitor.listarTaxas().size());
	}

	@Test
	void deveAtualizarTaxaExistente() {
		GerenciadorTaxaCambioVisitor visitor = new GerenciadorTaxaCambioVisitor();
		TaxaCambio taxa = visitor.registrarTaxa("GBP", 6.55);

		visitor.atualizarTaxa("GBP", 6.90);

		assertEquals(6.90, taxa.getValorEmReais());
	}

	@Test
	void deveInformarQuandoMoedaNaoExiste() {
		GerenciadorTaxaCambioVisitor visitor = new GerenciadorTaxaCambioVisitor();
		ByteArrayOutputStream saida = new ByteArrayOutputStream();
		PrintStream saidaOriginal = System.out;

		try {
			System.setOut(new PrintStream(saida, true));
			visitor.atualizarTaxa("JPY", 1.0);
		} finally {
			System.setOut(saidaOriginal);
		}

		assertEquals("Moeda nao encontrada no gerenciador: JPY", saida.toString().trim());
	}

	@Test
	void deveExibirPainelComTaxas() {
		GerenciadorTaxaCambioVisitor visitor = new GerenciadorTaxaCambioVisitor();
		visitor.registrarTaxa("USD", 5.17);
		visitor.registrarTaxa("EUR", 5.62);
		ByteArrayOutputStream saida = new ByteArrayOutputStream();
		PrintStream saidaOriginal = System.out;

		try {
			System.setOut(new PrintStream(saida, true));
			assertDoesNotThrow(visitor::exibirPainel);
		} finally {
			System.setOut(saidaOriginal);
		}

		String painel = saida.toString();
		assertEquals(2, visitor.listarTaxas().size());
		assertEquals("USD", visitor.listarTaxas().get(0).getMoeda());
		assertEquals("EUR", visitor.listarTaxas().get(1).getMoeda());
		assertTrue(painel.contains("Gerenciador de Taxa de Cambio (Visitor)"));
		assertTrue(painel.contains("Moeda: USD | Taxa: US$ 5.17"));
		assertTrue(painel.contains("Moeda: EUR | Taxa: EUR 5.62"));
	}

	@Test
	void deveAplicarVisitorNaTaxa() {
		TaxaCambio taxa = new LibraEsterlinaFactory().criarTaxa(6.55);
		ExibicaoTaxaCambioVisitor visitor = new ExibicaoTaxaCambioVisitor();

		assertEquals("Moeda: GBP | Taxa: GBP 6.55", taxa.aceitar(visitor));
	}

	@Test
	void deveLancarExcecaoParaMoedaNaoSuportada() {
		GerenciadorTaxaCambioVisitor visitor = new GerenciadorTaxaCambioVisitor();

		assertThrows(IllegalArgumentException.class, () -> visitor.registrarTaxa("JPY", 1.0));
	}

}
