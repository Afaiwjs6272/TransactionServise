package ru.java.ukhanov.t1.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.java.ukhanov.t1.model.Client;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ClientRowMapper implements RowMapper<Client> {
    @Override
    public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
        Client client = new Client();
        client.setClientId(rs.getString("client_id"));
        client.setClientName(rs.getString("client_name"));
        client.setAge(rs.getInt("age"));
        client.setDepartmentId((Integer) rs.getObject("department_id"));
        client.setUpdatedAt(LocalDateTime.from(rs.getTimestamp("updated_at").toInstant()));
        client.setCreatedAt(LocalDateTime.from(rs.getTimestamp("created_at").toInstant()));
        return client;
    }
}