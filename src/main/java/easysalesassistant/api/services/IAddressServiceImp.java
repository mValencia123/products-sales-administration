package easysalesassistant.api.services;

import easysalesassistant.api.dao.IAddressDAO;
import easysalesassistant.api.dao.ICityDAO;
import easysalesassistant.api.dao.IStateDAO;
import easysalesassistant.api.dto.address.AddressDTO;
import easysalesassistant.api.entity.Address;
import easysalesassistant.api.entity.City;
import easysalesassistant.api.entity.State;
import easysalesassistant.api.exceptions.NotFoundAddressException;
import easysalesassistant.api.exceptions.NotFoundCityException;
import easysalesassistant.api.exceptions.NotFoundStateException;
import org.springframework.stereotype.Service;

@Service
public class IAddressServiceImp implements IAddressService{
    IAddressDAO addressDAO;
    IStateDAO stateDAO;
    ICityDAO cityDAO;

    public IAddressServiceImp(IAddressDAO addressDAO,IStateDAO stateDAO,ICityDAO cityDAO){
        this.addressDAO = addressDAO;
        this.stateDAO = stateDAO;
        this.cityDAO = cityDAO;
    }

    @Override
    public Address createAddress(AddressDTO addressDTO) {
        Address address = new Address();
        State state = stateDAO.findById(addressDTO.getIdState()).orElseThrow(() -> new NotFoundStateException(404,"State's ID doens't exists."));
        City city = cityDAO.findById(addressDTO.getIdCity()).orElseThrow(() -> new NotFoundCityException(404,"City's ID doens't exists."));

        address.setIdState(state);
        address.setIdCity(city);
        address.setStreet(addressDTO.getStreet());
        address.setNumber(addressDTO.getNumber());
        address.setSuburb(addressDTO.getSuburb());
        address.setPostalCode(address.getPostalCode());

        return address;
    }

    @Override
    public Address updateAddress(Long id, AddressDTO addressDTO) {
        Address address = addressDAO.findById(id).orElseThrow(() -> new NotFoundAddressException(404,"Address's ID provider doesn't exists."));
        State state = stateDAO.findById(addressDTO.getIdState()).orElseThrow(() -> new NotFoundStateException(404,"State's ID doens't exists."));
        City city = cityDAO.findById(addressDTO.getIdCity()).orElseThrow(() -> new NotFoundCityException(404,"City's ID doens't exists."));

        address.setIdState(state);
        address.setIdCity(city);
        address.setStreet(addressDTO.getStreet());
        address.setNumber(addressDTO.getNumber());
        address.setSuburb(addressDTO.getSuburb());
        address.setPostalCode(addressDTO.getPostalCode());

        addressDAO.save(address);

        return address;
    }
}
