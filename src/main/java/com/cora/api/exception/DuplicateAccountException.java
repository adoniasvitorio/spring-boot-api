package com.cora.api.exception;

public class DuplicateAccountException extends RuntimeException {
  public DuplicateAccountException(String cpf) {
    super("CPF already exists: " + cpf);
  }
}
