package com.example.Lotto.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

class LottoriviRowMapper implements RowMapper<Lottorivi> {
@Override
public Lottorivi mapRow(ResultSet rs, int rowNum) throws SQLException {
Lottorivi lottorivi = new Lottorivi();
lottorivi.setId(rs.getLong("id"));
lottorivi.setPlayname(rs.getString("playname"));
lottorivi.setRow(rs.getString("row"));
lottorivi.setWins(rs.getLong("wins"));
return lottorivi;
}
}