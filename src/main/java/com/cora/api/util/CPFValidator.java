package com.cora.api.util;

public class CPFValidator {
	public static boolean isValidCPF(String cpf) {
		if (cpf == null || cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
			return false;
		}

		for (int i = 0; i < cpf.length(); i++) {
			if (!Character.isDigit(cpf.charAt(i))) {
				return false;
			}
		}

		/** Outras validações podem ser adicionadas aqui */
		return true;
	}
}