package jpabook.jpasjop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Embeddable
@Getter
@Setter
public class Address {
    private String city;
    private String zipCode;
    private String street;

    protected Address() {
    }

    public Address(String city, String zipCode, String street) {
        this.city = city;
        this.zipCode = zipCode;
        this.street = street;
    }
}
