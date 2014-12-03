package com.jambler.bfm.services.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public abstract class AbstractJdbcTemplateDao<E> implements GenericDao<E> {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Autowired
    public AbstractJdbcTemplateDao(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    protected JdbcTemplate getJdbcTemplateObject() {
        return jdbcTemplateObject;
    }
}