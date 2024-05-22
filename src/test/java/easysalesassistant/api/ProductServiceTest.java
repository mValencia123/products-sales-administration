package easysalesassistant.api;

import easysalesassistant.api.context.UserContext;
import easysalesassistant.api.dao.ICategoryDAO;
import easysalesassistant.api.dao.IProductDAO;
import easysalesassistant.api.dao.IProviderDAO;
import easysalesassistant.api.dao.IUserDAO;
import easysalesassistant.api.dto.product.ProductDTO;
import easysalesassistant.api.entity.Product;
import easysalesassistant.api.entity.SystemUser;
import easysalesassistant.api.enums.TypeUser;
import easysalesassistant.api.mappers.ProductMapper;
import easysalesassistant.api.services.IProductService;
import easysalesassistant.api.services.IProductServiceImp;
import easysalesassistant.api.services.IStockService;
import easysalesassistant.api.services.ISystemUserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private IProductServiceImp productService;

    @Mock
    private IProductDAO productDAO;

    @Mock
    private IUserDAO userDAO;


    @Test
    public void ProductService_WhenCreateProduct_ReturnProductDTO(){
        Product product = Product.builder()
                .id(1L)
                .barCode("123456789")
                .itemCode("1234-1234")
                .name("PRODUCT TEST")
                .description("PRODUCT DESCRIPTION")
                .cost(190.90F)
                .hasDiscount(true)
                .createdAt(new Date())
                .piecesBox(20)
                .retailPrice(195.90F)
                .wholesalePrice(185.90F)
                .build();

        ProductDTO productDTO = ProductDTO.builder()
                .id(1L)
                .barCode("123456789")
                .itemCode("1234-1234")
                .name("PRODUCT TEST")
                .description("PRODUCT DESCRIPTION")
                .cost(190.90F)
                .hasDiscount(true)
                .createdAt(new Date())
                .piecesBox(20)
                .retailPrice(195.90F)
                .wholesalePrice(185.90F)
                .idCategory(1L)
                .idProvider(1L)
                .build();

        SystemUser systemUser = SystemUser.builder()
                .typeUser(TypeUser.EMPLOYEE)
                .userName("max")
                .id(1L)
                .email("max").build();

        UserContext.setCurrentUser("max");

        Mockito.when(productDAO.save(Mockito.any(Product.class))).thenReturn(product);
        Mockito.when(userDAO.findByUserName(Mockito.anyString())).thenReturn(systemUser);

        ProductDTO responseProductDTO = productService.saveProduct(productDTO);

        Assertions.assertThat(responseProductDTO).isNotNull();
    }
}
