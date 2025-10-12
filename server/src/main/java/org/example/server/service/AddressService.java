package org.example.server.service;

import jakarta.validation.Valid;
import org.example.server.dto.AddressCreator;
import org.example.server.dto.ApiResponse;

import java.util.UUID;

public interface AddressService {

    ApiResponse createAddress(@Valid AddressCreator addressCreator);

    ApiResponse getAllAddresses();

    ApiResponse getAddressById(UUID id);

    ApiResponse deleteAddress(UUID id);

    ApiResponse updateAddress(UUID id, AddressCreator addressCreator);
}