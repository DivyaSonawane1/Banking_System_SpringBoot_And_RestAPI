package com.BankingSystem.bankingsystem.service;

import com.BankingSystem.bankingsystem.util.DBConnectionUtil;
import com.BankingSystem.bankingsystem.util.IdGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.springframework.stereotype.Service;

@Service

public class TransactionService {

    private final AccountService accountService;

    public TransactionService(AccountService accountService) {
        this.accountService = accountService;
    }

    // TRANSFER MONEY 
    public void transfer(String fromAccount, String toAccount, double amount) {

        String debitSql =
                "UPDATE accounts SET balance = balance - ? " +
                "WHERE account_number = ? AND balance >= ?";

        String creditSql =
                "UPDATE accounts SET balance = balance + ? " +
                "WHERE account_number = ?";

        String insertTxnSql =
                "INSERT INTO transactions " +
                "(transaction_id, from_account, to_account, amount, transaction_type) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnectionUtil.getConnection()) {

            // START TRANSACTION
            con.setAutoCommit(false);

            // DEBIT
            try (PreparedStatement debitPs = con.prepareStatement(debitSql)) {
                debitPs.setDouble(1, amount);
                debitPs.setString(2, fromAccount);
                debitPs.setDouble(3, amount);

                int rows = debitPs.executeUpdate();
                if (rows == 0) {
                    throw new RuntimeException("Insufficient balance or account not found");
                }
            }

            // CREDIT
            try (PreparedStatement creditPs = con.prepareStatement(creditSql)) {
                creditPs.setDouble(1, amount);
                creditPs.setString(2, toAccount);

                int rows = creditPs.executeUpdate();
                if (rows == 0) {
                    throw new RuntimeException("Destination account not found");
                }
            }

            // INSERT TRANSACTION RECORD
            try (PreparedStatement txnPs = con.prepareStatement(insertTxnSql)) {
                txnPs.setString(1, IdGenerator.generateTransactionId());
                txnPs.setString(2, fromAccount);
                txnPs.setString(3, toAccount);
                txnPs.setDouble(4, amount);
                txnPs.setString(5, "TRANSFER");

                txnPs.executeUpdate();
            }

            // COMMIT
            con.commit();

        } catch (Exception e) {
            throw new RuntimeException("Transaction failed: " + e.getMessage(), e);
        }
    }
}
