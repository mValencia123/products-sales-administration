package easysalesassistant.api.services;

import easysalesassistant.api.dao.IProviderDAO;
import easysalesassistant.api.dto.provider.ProviderDTO;
import easysalesassistant.api.entity.Address;
import easysalesassistant.api.entity.Provider;
import easysalesassistant.api.entity.SystemUser;
import easysalesassistant.api.exceptions.NotFoundProviderException;
import easysalesassistant.api.exceptions.ProviderIsDeletedException;
import easysalesassistant.api.mappers.ProviderMapper;
import easysalesassistant.api.utils.StreamOperation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IProviderServiceImp implements IProviderService {
    IProviderDAO providerDAO;
    IAddressService addressService;
    ISystemUserService userService;

    public IProviderServiceImp(IProviderDAO providerDAO,IAddressService addressService,ISystemUserService userService){
        this.providerDAO = providerDAO;
        this.addressService = addressService;
        this.userService = userService;
    }

    @Override
    @Transactional
    public ProviderDTO saveProvider(ProviderDTO providerDTO) {
        Provider provider = ProviderMapper.INSTANCE.providerDTOToProvider(providerDTO);
        Address address = addressService.createAddress(providerDTO.getAddress());
        provider.setIdAddress(address);
        providerDAO.save(provider);
        return providerDTO;
    }

    @Override
    @Transactional
    public ProviderDTO patchProvider(Long id, ProviderDTO providerDTO) {
        Provider provider = existsProviderById(id);
        this.providerIsDeleted(provider);
        Address address = addressService.updateAddress(provider.getIdAddress().getId(),providerDTO.getAddress());
        provider.setFirstName(providerDTO.getFirstName());
        provider.setLastName(provider.getLastName());
        provider.setRfc(provider.getRfc());
        provider.setIdAddress(address);

        providerDAO.save(provider);
        return providerDTO;
    }

    @Override
    public ProviderDTO getProviderDTOById(Long id) {
        Provider provider = existsProviderById(id);
        this.providerIsDeleted(provider);
        return ProviderMapper.INSTANCE.providerToProviderDTO(provider);
    }
    @Override
    public void deleteProvider(Long id) {
        Provider provider = existsProviderById(id);
        this.providerIsDeleted(provider);
        SystemUser systemUser = userService.getUserByContext();

        provider.setDeleted(true);
        provider.setDeletedAt(new Date());
        provider.setIdUserDeleted(systemUser);

        providerDAO.save(provider);
    }

    @Override
    public Provider existsProviderById(Long id){
        return providerDAO.findById(id).orElseThrow(() -> new NotFoundProviderException(404,"Provider's ID doesn't exists."));
    }

    @Override
    public List<ProviderDTO> getProviders() {
        return StreamOperation.getStreamFromIterable(providerDAO.findAll())
                .filter((p) -> !p.isDeleted())
                .map(ProviderMapper.INSTANCE::providerToProviderDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void providerIsDeleted(Provider provider) {
        if(provider.isDeleted()) throw new ProviderIsDeletedException(403,"The provider is not longer active.");
    }
}
