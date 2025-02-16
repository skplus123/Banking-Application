package com.Banking.Application.controller;

import com.Banking.Application.entity.Account;
import com.Banking.Application.exception.AccountNotFoundException;
import com.Banking.Application.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @GetMapping("/{id}")
    public Account getAccount(@PathVariable Long id) throws AccountNotFoundException {
        return accountService.getAccount(id).orElseThrow(() -> new AccountNotFoundException("Account with ID " + id + " not found"));
    }

    @PostMapping("/{id}/deposit")
    public Account deposit(@PathVariable Long id, @RequestBody Map<String, Double> request) throws AccountNotFoundException {
        Double amount = request.get("amount");
        return accountService.deposit(id, amount);
    }

    @PostMapping("/{id}/withdraw")
    public Account withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request) throws AccountNotFoundException {
        Double amount = request.get("amount");
        return accountService.withdraw(id, amount);
    }
}
