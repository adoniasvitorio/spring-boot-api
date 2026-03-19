package com.cora.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cora.api.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByCpf(String cpf);
}
