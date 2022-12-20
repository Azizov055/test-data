package com.example.data.mapper;

import com.example.data.dto.AccountDto;
import com.example.data.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public abstract class AccountMapper {

    public static final AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mapping(source = "balance", target = "aznBalance", qualifiedByName = "convertBalance")
    abstract AccountDto mapEntityToDto(Account account);

    public abstract List<AccountDto> mapEntityListToDtoList(List<Account> accounts);

    @Named("convertBalance")
    public static BigDecimal convertBalanceToAzn(BigDecimal balance) {
        return balance.multiply(BigDecimal.valueOf(1.7));
    }

}
