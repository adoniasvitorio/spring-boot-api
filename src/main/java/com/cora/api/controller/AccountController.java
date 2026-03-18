package com.cora.api.controller;

import com.cora.api.model.Account;
import com.cora.api.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/accounts")
public class AccountController {
  private final AccountService accountService;

  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Account createAccount(@RequestBody Account account) {
    return accountService.createAccount(account.getName(), account.getCPF());
  }

  @GetMapping
  public List<Account> listAccounts() {
    return accountService.listAccounts();
  }

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Map<String, String> handleIllegalArgument(IllegalArgumentException ex) {
    return Map.of("message", ex.getMessage());
  }
}
