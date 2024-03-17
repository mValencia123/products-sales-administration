package easysalesassistant.api.services;

import easysalesassistant.api.dto.address.AddressDTO;
import easysalesassistant.api.entity.Address;

public interface IAddressService{
    Address createAddress(AddressDTO addressDTO);

    Address updateAddress(Long id, AddressDTO address);
}
