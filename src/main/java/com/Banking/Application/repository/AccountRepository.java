package com.Banking.Application.repository;

import com.Banking.Application.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for performing CRUD operations on {@link Account} entities.
 * Extends {@link JpaRepository} to provide standard data access operations.
 */
public interface AccountRepository extends JpaRepository<Account, Long>{
    // We can add custom query methods here if needed.
}
