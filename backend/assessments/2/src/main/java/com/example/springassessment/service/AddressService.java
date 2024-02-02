package com.example.springassessment.service;


import com.example.springassessment.entity.Address;
import com.example.springassessment.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {


    private AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository)
    {
        this.addressRepository = addressRepository;
    }

    public Address createAddress(Address address)
    {
        return addressRepository.save(address);
    }
}
