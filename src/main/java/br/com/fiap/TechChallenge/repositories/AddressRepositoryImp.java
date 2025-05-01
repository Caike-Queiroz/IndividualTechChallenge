package br.com.fiap.TechChallenge.repositories;

import br.com.fiap.TechChallenge.entities.Address;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AddressRepositoryImp implements AddressRepository {

    private final JdbcClient jdbcClient;

    public AddressRepositoryImp(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Address> findById(Long id) {
        return this.jdbcClient
                .sql("SELECT * FROM address WHERE id = :id")
                .param("id", id)
                .query(Address.class)
                .optional();
    }

    @Override
    public Optional<Address> findByStreetAndNum(String street, Integer num) {
        return this.jdbcClient
                .sql("SELECT * FROM address WHERE street = :street AND num = :num")
                .param("street", street)
                .param("num", num)
                .query(Address.class)
                .optional();
    }

    @Override
    public Integer save(Address address) {

        return this.jdbcClient
                .sql("INSERT INTO address (street, num, complement, country, city, state, zipcode) " +
                        "VALUES (:street, :num, :complement, :country, :city, :state, :zipcode)")
                .param("street", address.getStreet())
                .param("num", address.getNum())
                .param("complement", address.getComplement())
                .param("country", address.getCountry())
                .param("city", address.getCity())
                .param("state", address.getState())
                .param("zipcode", address.getZipcode())
                .update();
    }
}