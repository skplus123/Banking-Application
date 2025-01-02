package com.Banking.Application.repository;

import com.Banking.Application.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
public interface BankRepository extends JpaRepository<Account, Long>{
}
