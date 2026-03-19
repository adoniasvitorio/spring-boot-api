package com.cora.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cora.api.exception.DuplicateAccountException;
import com.cora.api.model.Account;
import com.cora.api.repository.AccountRepository;
import com.cora.api.util.CPFValidator;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(String name, String cpf) {
        if (!CPFValidator.isValidCPF(cpf)) {
            throw new IllegalArgumentException("Invalid CPF");
        }

        if (accountRepository.existsByCpf(cpf)) {
            throw new DuplicateAccountException(cpf);
        }

        Account account = new Account(name, cpf);
        return accountRepository.save(account);
    }

    public List<Account> listAccounts() {
        return accountRepository.findAll();
    }
}
