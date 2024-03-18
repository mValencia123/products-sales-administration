package easysalesassistant.api.mappers.qualifiers;

import easysalesassistant.api.dao.IAddressDAO;
import easysalesassistant.api.dto.address.AddressDTO;
import easysalesassistant.api.entity.Address;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
public class AddressQualifier {

    IAddressDAO addressDAO;

    AddressQualifier(IAddressDAO addressDAO){
        this.addressDAO = addressDAO;
    }

    @Named("addressDTOToAddress")
    public Address addressDTOToAddress(AddressDTO addressDTO) {
        return addressDAO.findById(addressDTO.getId()).orElse(null);
    }
}
