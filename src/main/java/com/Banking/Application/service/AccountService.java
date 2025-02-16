package com.Banking.Application.service;

import com.Banking.Application.entity.Account;
import com.Banking.Application.exception.AccountNotFoundException;
import com.Banking.Application.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * Service class for managing bank accounts.
 * Provides operations to create accounts, retrieve account details,
 * deposit funds, and withdraw funds from an account.
 */
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
    @Cacheable("account")
    public Optional<Account> getAccount(Long id) {
        log.info("Fetching account from the database for id: " + id);
        return accountRepository.findById(id);
    }

    /**
     * Deposits money into the account.
     * @param id   the ID of the account
     * @param amount   the amount to deposit
     * @return   the updated account
     * @throws AccountNotFoundException   if the account is not found
     */
    public Account deposit(Long id, double amount) throws AccountNotFoundException {
        log.info("Deposit request received for Account ID: {} with Amount: {}", id, amount);
        Account account = getAccount(id).orElseThrow(() -> new AccountNotFoundException("Account with ID " + id + " not found"));
        account.setBalance(account.getBalance() + amount);
        log.info("New balance for Account ID {}: {}", id, account.getBalance());
        return accountRepository.save(account);
    }

    /**
     * Withdraws money from the account.
     * @param id    the ID of the account
     * @param amount   amount the amount to withdraw
     * @return   the updated account
     * @throws AccountNotFoundException   if the account is not found or if there are insufficient funds
     */
    public Account withdraw(Long id, double amount) throws AccountNotFoundException {
        log.info("Withdraw request received for Account ID: {} with Amount: {}", id, amount);
        Account account = getAccount(id).orElseThrow(() -> new AccountNotFoundException("Account with ID " + id + " not found"));
        if (account.getBalance() < amount) {
            throw new AccountNotFoundException("Insufficient funds");
        }
        account.setBalance(account.getBalance() - amount);
        log.info("New balance for Account ID {}: {}", id, account.getBalance());
        return accountRepository.save(account);
    }
}
