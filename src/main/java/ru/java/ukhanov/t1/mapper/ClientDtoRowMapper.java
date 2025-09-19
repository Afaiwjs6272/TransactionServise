package ru.java.ukhanov.t1.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.java.ukhanov.t1.dto.ClientDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDtoRowMapper implements RowMapper<ClientDto> {
    @Override
    public ClientDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ClientDto(
                rs.getString("client_id"),
                rs.getString("client_name"),
                rs.getInt("age"),
                rs.getBigDecimal("total_balance")
        );
    }
}