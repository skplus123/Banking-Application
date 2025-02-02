package com.Banking.Application.service;

import com.Banking.Application.entity.Account;
import com.Banking.Application.exception.AccountNotFoundException;
import com.Banking.Application.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class AccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountRepository accountRepository;

    /**
     * create a new bank account
     * @param account
     * @return account
     */
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    /**
     * retrive account by account id
     * @param id
     * @return account
     */
    public Optional<Account> getAccount(Long id) {
        return accountRepository.findById(id);
    }

    /**
     * deposit the money to bank account
     * @param id
     * @param amount
     * @return account
     */
    public Account deposit(Long id, double amount) throws AccountNotFoundException {
        log.info("Deposit request received for Account ID: {} with Amount: {}", id, amount);
        Account account = getAccount(id).orElseThrow(() -> new AccountNotFoundException("Account with ID " + id + " not found"));
        account.setBalance(account.getBalance() + amount);
        log.info("New balance for Account ID {}: {}", id, account.getBalance());
        return accountRepository.save(account);
    }


    /**
     * winthdraw the money from account
     * @param id
     * @param amount
     * @return account
     */
    public Account withdraw(Long id, double amount) {
        log.info("Withdraw request received for Account ID: {} with Amount: {}", id, amount);
        Account account = getAccount(id).orElseThrow(() -> new RuntimeException("Account not found"));
        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient funds");
        }
        account.setBalance(account.getBalance() - amount);
        log.info("New balance for Account ID {}: {}", id, account.getBalance());
        return accountRepository.save(account);
    }
}
