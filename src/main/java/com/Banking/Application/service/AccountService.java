package com.Banking.Application.service;

import com.Banking.Application.entity.Account;
import com.Banking.Application.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    public Account deposite(Long id, double amount) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) {
            Account account1 = account.get();
            account1.setBalance(account1.getBalance() + amount);
            return accountRepository.save(account1);
        }
        return null;
    }

    public Account withdraw(Long id, double amount) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) {
            Account account1 = account.get();
            account1.setBalance(account1.getBalance() - amount);
            return accountRepository.save(account1);
        }
        return null;
    }
}
