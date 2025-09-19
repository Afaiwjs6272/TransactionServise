package ru.java.ukhanov.t1.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.java.ukhanov.t1.dto.AccountDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDtoRowMapper implements RowMapper<AccountDto> {
    @Override
    public AccountDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new AccountDto(
                rs.getString("client_id"),
                rs.getString("client_name"),
                rs.getInt("age"),
                rs.getLong("account_number"),
                rs.getDate("opening_date").toLocalDate(),
                rs.getDate("closing_date") != null ? rs.getDate("closing_date").toLocalDate() : null,
                rs.getBigDecimal("balance")
        );
    }
}