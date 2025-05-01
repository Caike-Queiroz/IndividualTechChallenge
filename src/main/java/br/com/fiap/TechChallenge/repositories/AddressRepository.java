package br.com.fiap.TechChallenge.repositories;

import br.com.fiap.TechChallenge.entities.Address;

import java.util.Optional;

public interface AddressRepository {

    Optional<Address> findById(Long id);

    Optional<Address> findByStreetAndNum(String street, Integer num);

    Integer save(Address address);
}