package com.bernardoduarte.visitor;

public interface TaxaCambioVisitor {

	String visitar(DolarAmericano dolarAmericano);

	String visitar(Euro euro);

	String visitar(LibraEsterlina libraEsterlina);
}
