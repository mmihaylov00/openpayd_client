package com.example.openpayd_client.dto;

public class AddressDTO {
    private String addressLine1;
    private String city;
    private String country;

    public AddressDTO(String addressLine1, String city, String country) {
        this.addressLine1 = addressLine1;
        this.city = city;
        this.country = country;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
