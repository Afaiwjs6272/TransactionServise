package ru.java.ukhanov.t1.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.java.ukhanov.t1.dto.ClientDto;
import ru.java.ukhanov.t1.mapper.ClientDtoRowMapper;
import ru.java.ukhanov.t1.model.Client;

import java.util.List;

@Repository
public class ClientRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(Client client) {
        String sql = """
                INSERT INTO clients (client_id, client_name, age, department_id, updated_at)
                VALUES (?, ?, ?, ?, ?)
                """;

        jdbcTemplate.update(sql,
                client.getClientId(),
                client.getClientName(),
                client.getAge(),
                client.getDepartmentId(),
                client.getUpdatedAt()
        );
    }

    public List<ClientDto> findClientsWithPagination(int page, int size, String clientName,
                                                     Integer minAge, Integer maxAge) {
        StringBuilder sql = new StringBuilder("""
                    SELECT
                        c.client_id,
                        c.client_name,
                        c.age,
                        SUM(a.balance) as total_balance
                    FROM clients c
                    LEFT JOIN accounts a ON c.client_id = a.client_id
                """);

        if (clientName != null && !clientName.trim().isEmpty()) {
            sql.append(" AND c.client_name LIKE '%").append(clientName).append("%'");
        }
        if (minAge != null) {
            sql.append(" AND c.age >= ").append(minAge);
        }
        if (maxAge != null) {
            sql.append(" AND c.age <= ").append(maxAge);
        }

        sql.append(" GROUP BY c.client_id, c.client_name, c.age");
        sql.append(" ORDER BY c.client_name");
        sql.append(" LIMIT ").append(size);
        sql.append(" OFFSET ").append(page * size);

        return jdbcTemplate.query(sql.toString(), new ClientDtoRowMapper());
    }

    public long countClients(String clientName, Integer minAge, Integer maxAge) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM clients WHERE 1=1");

        if (clientName != null && !clientName.trim().isEmpty()) {
            sql.append(" AND client_name LIKE '%").append(clientName).append("%'");
        }
        if (minAge != null) {
            sql.append(" AND age >= ").append(minAge);
        }
        if (maxAge != null) {
            sql.append(" AND age <= ").append(maxAge);
        }

        return jdbcTemplate.queryForObject(sql.toString(), Long.class);
    }
}