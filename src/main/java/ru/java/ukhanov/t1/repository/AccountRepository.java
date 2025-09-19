package ru.java.ukhanov.t1.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.java.ukhanov.t1.dto.AccountDto;
import ru.java.ukhanov.t1.mapper.AccountDtoRowMapper;
import ru.java.ukhanov.t1.model.Account;

import java.math.BigDecimal;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AccountRepository {

    private final JdbcTemplate jdbcTemplate;

    public void save(Account account) {
        String sql = """
                INSERT INTO accounts (client_id, number, opening_date, closing_date, balance)
                VALUES (?, ?, ?, ?, ?)
                """;

        jdbcTemplate.update(sql,
                account.getClientId(),
                account.getNumber(),
                account.getOpeningDate(),
                account.getClosingDate(),
                account.getBalance()
        );
    }

    public void deleteByClientId(String clientId) {
        String sql = "DELETE FROM accounts WHERE client_id = ?";
        jdbcTemplate.update(sql, clientId);
    }

    public List<AccountDto> findAccountsByClientId(String clientId, BigDecimal minAmount) {
        StringBuilder sql = new StringBuilder("""
                    SELECT
                        c.client_id,
                        c.client_name,
                        c.age,
                        a.number as account_number,
                        a.opening_date,
                        a.closing_date,
                        a.balance
                    FROM accounts a
                    INNER JOIN clients c ON a.client_id = c.client_id
                    WHERE a.client_id = ?
                """);

        if (minAmount != null) {
            sql.append(" AND a.balance >= ").append(minAmount);
        }

        sql.append(" ORDER BY a.number");

        return jdbcTemplate.query(sql.toString(), new AccountDtoRowMapper(), clientId);
    }
}