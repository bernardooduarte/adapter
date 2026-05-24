package com.bernardoduarte.adapter;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdapterApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void deveRegistrarTaxaInterna() {
		GerenciadorTaxaCambio gerenciador = new GerenciadorTaxaCambio();

		TaxaCambio taxa = gerenciador.registrarTaxa("USD", 5.17);

		assertNotNull(taxa);
		assertEquals("USD", taxa.getMoeda());
		assertEquals(1, gerenciador.listarTaxas().size());
	}

	@Test
	void deveAdaptarTaxaExterna() {
		GerenciadorTaxaCambio gerenciador = new GerenciadorTaxaCambio();
		TaxaCambio taxa = gerenciador.adicionarTaxaExterna(new TaxaCambioExterna() {
			@Override
			public String getMoeda() {
				return "JPY";
			}

			@Override
			public String getValorEmReais() {
				return "1,23";
			}
		});

		assertEquals("JPY", taxa.getMoeda());
		assertEquals(1.23, taxa.getValorEmReais());
		assertEquals("1.23", taxa.getValorFormatado());
	}

	@Test
	void deveExibirPainelComTaxasAdaptadas() {
		GerenciadorTaxaCambio gerenciador = new GerenciadorTaxaCambio();
		gerenciador.registrarTaxa("USD", 5.17);
		gerenciador.adicionarTaxaExterna(new TaxaCambioExterna() {
			@Override
			public String getMoeda() {
				return "JPY";
			}

			@Override
			public String getValorEmReais() {
				return "1,23";
			}
		});

		ByteArrayOutputStream saida = new ByteArrayOutputStream();
		PrintStream saidaOriginal = System.out;

		try {
			System.setOut(new PrintStream(saida, true));
			assertDoesNotThrow(() -> System.out.println(gerenciador.exibirPainel()));
		} finally {
			System.setOut(saidaOriginal);
		}

		String painel = saida.toString();
		assertTrue(painel.contains("Gerenciador de Taxa de Cambio (Adapter)"));
		assertTrue(painel.contains("Moeda: USD | Taxa: US$ 5.17"));
		assertTrue(painel.contains("Moeda: JPY | Taxa: 1.23"));
	}

	@Test
	void deveLancarExcecaoParaMoedaNaoSuportada() {
		GerenciadorTaxaCambio gerenciador = new GerenciadorTaxaCambio();

		assertThrows(IllegalArgumentException.class, () -> gerenciador.registrarTaxa("ABC", 1.0));
	}
}