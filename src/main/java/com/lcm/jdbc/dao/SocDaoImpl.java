package com.lcm.jdbc.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class SocDaoImpl implements SocDao {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void out(String vid, int btry_pak_sum) {
        System.out.println("in SocDao out method");
        this.jdbcTemplate.update("update status_soc set btry_pak_sum =? where id =?", btry_pak_sum, vid);
    }

    @Override
    public void in(String vid, int btry_pak_sum) {
        System.out.println("in SocDao in method");
        this.jdbcTemplate.update("update status_soc set btry_pak_sum =? where id =?", btry_pak_sum+100, vid);
    }
}
