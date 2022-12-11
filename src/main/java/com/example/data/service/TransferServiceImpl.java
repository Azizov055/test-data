package com.example.data.service;

import com.example.data.entity.Account;
import com.example.data.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final AccountRepository accountRepository;

    @Override
    @Transactional(rollbackFor = { SQLException.class })
    public void transferMoney(Long accountId, Long beneficiaryAccountId, BigDecimal amount) throws SQLException {
        Account account = accountRepository.findById(accountId).get();
        Account beneficiaryAccount = accountRepository.findById(beneficiaryAccountId).get();

        account.setBalance(account.getBalance().subtract(amount));

        if (beneficiaryAccount.isDisposal()) {
            throw new SQLException();
        }

        beneficiaryAccount.setBalance(beneficiaryAccount.getBalance().add(amount));
    }

}
