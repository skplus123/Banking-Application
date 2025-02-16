package com.Banking.Application.controller;

import com.Banking.Application.entity.Account;
import com.Banking.Application.exception.AccountNotFoundException;
import com.Banking.Application.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 *REST controller for managing bank accounts.
 *This controller exposes endpoints for creating accounts, retrieving account details,
 *depositing funds, and withdrawing funds.
 *
 */
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * Creates a new bank account.
     *
     * @param account the account information to be created
     * @return the created {@link Account} instance
     */
    @PostMapping("/create")
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    /**
     * Retrieves an account by its ID.
     *
     * @param id the unique identifier of the account
     * @return the {@link Account} with the specified ID
     * @throws AccountNotFoundException if no account is found with the given ID
     */
    @GetMapping("/{id}")
    public Account getAccount(@PathVariable Long id) throws AccountNotFoundException {
        return accountService.getAccount(id).orElseThrow(() -> new AccountNotFoundException("Account with ID " + id + " not found"));
    }

    /**
     * Deposits funds into an account.
     *
     * @param id      the unique identifier of the account
     * @param request a map containing the deposit amount under the key "amount"
     * @return the updated {@link Account} after the deposit
     * @throws AccountNotFoundException if the account is not found
     */
    @PostMapping("/{id}/deposit")
    public Account deposit(@PathVariable Long id, @RequestBody Map<String, Double> request) throws AccountNotFoundException {
        Double amount = request.get("amount");
        return accountService.deposit(id, amount);
    }

    /**
     * Withdraws funds from an account.
     *
     * @param id      the unique identifier of the account
     * @param request a map containing the withdrawal amount under the key "amount"
     * @return the updated {@link Account} after the withdrawal
     * @throws AccountNotFoundException if the account is not found or if there are insufficient funds
     */
    @PostMapping("/{id}/withdraw")
    public Account withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request) throws AccountNotFoundException {
        Double amount = request.get("amount");
        return accountService.withdraw(id, amount);
    }
}
