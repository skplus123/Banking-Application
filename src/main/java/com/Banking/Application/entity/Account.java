package com.Banking.Application.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents an account in the banking application.
 * This entity holds information about the account holder and the account balance.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class Account {

    /**
     * Unique identifier for the account.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the account holder.
     */
    @Column(name = "account_holder_name")
    private String accountHolderName;

    /**
     * The current balance of the account.
     */
    @Column(name = "balance")
    private double balance;
}
