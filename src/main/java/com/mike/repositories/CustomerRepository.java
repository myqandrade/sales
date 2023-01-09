package com.mike.repositories;

import com.mike.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerRepository {

    private static String INSERT = "insert into customer (name) values (?) ";

    private static String SELECT_ALL = "select * from customer";

    private static String UPDATE = "update customer set name = ? where id = ?";

    private static String DELETE = "delete from customer where id = ?";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Customer save(Customer customer){
        jdbcTemplate.update(INSERT, new Object[] {customer.getName()});
        return customer;
    }

    public List<Customer> findByName(String name){
       return jdbcTemplate.query(SELECT_ALL.concat(" where name like ? "),
               new Object[]{"%" + name + "%"},
               findCustomerMapper());
    }

    public Customer update(Customer customer){
        jdbcTemplate.update(UPDATE, new Object[]{customer.getName(), customer.getId()});
        return customer;
    }

    public Customer delete(Customer customer){
        jdbcTemplate.update(DELETE, new Object[]{customer.getId()});
        return customer;
    }

    public List<Customer> findAll(){
        return jdbcTemplate.query(SELECT_ALL, findCustomerMapper());
    }

    private RowMapper<Customer> findCustomerMapper() {
        return new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                return new Customer(name, id);
            }
        };
    }
}
