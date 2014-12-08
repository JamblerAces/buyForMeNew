package com.jambler.bfm.services.dao.impl;

import com.jambler.bfm.domain.Customer;
import com.jambler.bfm.services.dao.AbstractJdbcTemplateDao;
import com.jambler.bfm.services.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@Profile("jdbc")
public class CustomerJdbcDAO extends AbstractJdbcTemplateDao<Customer> implements CustomerDao {

    private static final CustomerRowMapper rowMapper = new CustomerRowMapper();

    private static enum SQL_QUERY {
        create("INSERT INTO customer (name) VALUES (?)"),
        update("UPDATE customer SET name = ? WHERE id = ?"),
        remove("DELETE FROM customer  WHERE id = ? "),
        findById("SELECT * FROM customer WHERE id = ?"),
        findByName("SELECT * FROM customer WHERE name LIKE ?"),
        findAll("SELECT * FROM customer");

        private String query;

        SQL_QUERY(String query) {
            this.query = query;
        }

        public String getQuery() {
            return query;
        }
    }

    @Autowired
    public CustomerJdbcDAO(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void save(Customer entity) {
        getJdbcTemplateObject().update(SQL_QUERY.update.getQuery(), entity.getName(), entity.getId());
    }

    @Override
    public void remove(Customer entity) {
        getJdbcTemplateObject().update(SQL_QUERY.remove.getQuery(), entity.getId());
    }

    @Override
    public Customer find(Long key) {
        return getJdbcTemplateObject().queryForObject(SQL_QUERY.findById.getQuery(), rowMapper, key);
    }

    @Override
    public Customer findByName(String name) {
        return getJdbcTemplateObject().queryForObject(SQL_QUERY.findByName.getQuery(), rowMapper, name);
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        List<Map<String, Object>> rows = getJdbcTemplateObject().queryForList(SQL_QUERY.findAll.getQuery());
        for (Map row : rows) {
            Customer customer = new Customer();
            customer.setId(Long.parseLong(String.valueOf(row.get("ID"))));
            customer.setName((String) row.get("NAME"));
            customers.add(customer);
        }

        return customers;
    }

    private static class CustomerRowMapper implements RowMapper<Customer> {
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer customer = new Customer();
            customer.setId(rs.getLong("ID"));
            customer.setName(rs.getString("NAME"));
            return customer;
        }
    }
}
