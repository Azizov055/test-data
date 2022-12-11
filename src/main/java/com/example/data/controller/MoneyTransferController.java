package com.example.data.controller;

import com.example.data.request.TrasnferRequest;
import com.example.data.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/money")
@RequiredArgsConstructor
public class MoneyTransferController {

    private final TransferService transferService;

    @PostMapping("/transfer")
    @ResponseStatus(HttpStatus.OK)
    public void transfer(@RequestBody TrasnferRequest trasnferRequest) throws SQLException {
        transferService.transferMoney(
                trasnferRequest.getAccountId(),
                trasnferRequest.getBeneficiaryAccountId(),
                trasnferRequest.getAmount());
    }

}
