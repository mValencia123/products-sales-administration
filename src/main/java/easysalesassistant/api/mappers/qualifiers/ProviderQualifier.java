package easysalesassistant.api.mappers.qualifiers;

import easysalesassistant.api.dao.ICategoryDAO;
import easysalesassistant.api.dao.IProviderDAO;
import easysalesassistant.api.entity.Category;
import easysalesassistant.api.entity.Provider;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
public class ProviderQualifier {
    IProviderDAO providerDAO;

    public ProviderQualifier(IProviderDAO providerDAO){
        this.providerDAO = providerDAO;
    }

    @Named("longToProvider")
    public Provider longToProvider(Long id) {
        return providerDAO.findById(id).orElse(null);
    }
}
