package com.example.data.service;

import com.example.data.entity.Account;

import java.math.BigDecimal;
import java.sql.SQLException;

public interface TransferService {

    void transferMoney(Long accountId, Long beneficiaryAccountId, BigDecimal amount) throws SQLException;

}
