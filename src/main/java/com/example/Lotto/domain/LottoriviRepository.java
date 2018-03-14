package com.example.Lotto.domain;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public class LottoriviRepository
{
@Autowired
private JdbcTemplate jdbcTemplate;

@Transactional(readOnly=true)
public List<Lottorivi> findAll() {
return jdbcTemplate.query("select * from lottorivi", new LottoriviRowMapper());
}

@Transactional(readOnly=true)
public List<Lottorivi> findById(Long id) {
return jdbcTemplate.query("select * from lottorivi where id="+id, new LottoriviRowMapper());
}

}