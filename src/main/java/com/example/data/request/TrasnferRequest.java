package com.example.data.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrasnferRequest {

    private Long accountId;
    private Long beneficiaryAccountId;
    private BigDecimal amount;

}
