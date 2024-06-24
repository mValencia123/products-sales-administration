package easysalesassistant.api.mappers;

import easysalesassistant.api.dao.ICategoryDAO;
import easysalesassistant.api.dao.IProviderDAO;
import easysalesassistant.api.dto.product.ProductDTO;
import easysalesassistant.api.entity.Category;
import easysalesassistant.api.entity.Product;
import easysalesassistant.api.entity.Provider;
import easysalesassistant.api.mappers.qualifiers.CategoryQualifier;
import easysalesassistant.api.mappers.qualifiers.ProviderQualifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

    @InjectMocks
    ProductMapperImpl productMapper;
    @Mock
    IProviderDAO providerDAO;
    @Mock
    ICategoryDAO categoryDAO;
    @Mock
    CategoryQualifier categoryQualifier;
    @Mock
    ProviderQualifier providerQualifier;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenProduct_ReturnsProductDTO(){

        Provider provider = Provider.builder()
                .id(1L)
                .firstName("Provider 1")
                .lastName("Lastname")
                .createdAt(new Date())
                .build();

        Category category = Category.builder()
                .id(1L)
                .description("Category 1")
                .createdAt(new Date())
                .build();

        Product product = Product.builder()
                .id(1L)
                .barCode("1234")
                .itemCode("1234")
                .name("name")
                .description("Description")
                .cost(10)
                .cost(10)
                .createdAt(new Date())
                .piecesBox(10)
                .retailPrice(10)
                .idCategory(category)
                .idProvider(provider)
                .build();

        ProductDTO productDTO = productMapper.productToProductDTO(product);

        Assertions.assertNotNull(productDTO);
        Assertions.assertEquals(productDTO.getId(),product.getId());
    }

    @Test
    public void whenProductDTO_ReturnsProduct(){
        Provider provider = Provider.builder()
                .id(1L)
                .firstName("Provider 1")
                .lastName("Lastname")
                .createdAt(new Date())
                .build();

        Category category = Category.builder()
                .id(1L)
                .description("Category 1")
                .createdAt(new Date())
                .build();

        ProductDTO productDTO = ProductDTO.builder()
                .id(1L)
                .barCode("1234")
                .itemCode("1234")
                .name("name")
                .description("Description")
                .cost(10)
                .cost(10)
                .createdAt(new Date())
                .piecesBox(10)
                .retailPrice(10)
                .idCategory(1L)
                .idProvider(1L)
                .build();

        Mockito.when(categoryQualifier.longToCategory(Mockito.anyLong())).thenReturn(category);
        Mockito.when(providerQualifier.longToProvider(Mockito.anyLong())).thenReturn(provider);

        Product product = productMapper.productDTOToProduct(productDTO);

        Assertions.assertNotNull(product);
        Assertions.assertEquals(productDTO.getId(),product.getId());
        Assertions.assertNotNull(product.getIdProvider());
        Assertions.assertNotNull(product.getIdCategory());
    }

}