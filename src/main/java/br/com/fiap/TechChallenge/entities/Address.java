package br.com.fiap.TechChallenge.entities;

import br.com.fiap.TechChallenge.dtos.AddressPostRequestDTO;
import lombok.*;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Address {

    private Long id;
    private String street;
    private Integer num;
    private String complement;
    private String country;
    private String city;
    private String state;
    private String zipcode;

    public Address(AddressPostRequestDTO address) {

        this.street = address.street();
        this.num = address.num();
        this.complement = address.complement();
        this.country = address.country();
        this.city = address.city();
        this.state = address.state();
        this.zipcode = address.zipcode();
    }
}