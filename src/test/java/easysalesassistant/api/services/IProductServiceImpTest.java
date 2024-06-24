package easysalesassistant.api.services;

import easysalesassistant.api.dao.IProductDAO;
import easysalesassistant.api.dto.product.ProductDTO;
import easysalesassistant.api.entity.Product;
import easysalesassistant.api.entity.SystemUser;
import easysalesassistant.api.mappers.ProductMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.awt.*;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class IProductServiceImpTest {

    @InjectMocks
    IProductServiceImp productServiceImp;
    @Mock
    IProductDAO productDAO;
    @Mock
    ISystemUserService userService;
    @Mock
    ProductMapper productMapper;
    @Mock
    IStockService stockService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void returnProductDTO_WhenGetsIdProduct(){
        //Given
        Product product = Product.builder()
                .id(1L)
                .barCode("1234")
                .itemCode("1234")
                .name("name")
                .description("Description")
                .cost(10)
                .build();

        ProductDTO productDTO = ProductDTO.builder()
                .id(1L)
                .barCode("1234")
                .itemCode("1234")
                .name("name")
                .description("Description")
                .cost(10)
                .build();

        Mockito.when(productDAO.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(product));
        Mockito.when(productMapper.productToProductDTO(Mockito.any(Product.class))).thenReturn(productDTO);

        //Then
        ProductDTO productResponseDTO = productServiceImp.getProduct(10L);

        //Return
        Assertions.assertNotNull(productResponseDTO);
        Assertions.assertEquals(productResponseDTO.getId(),product.getId());
    }

    @Test
    public void whenCreateProduct_ReturnsProductDTO(){
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
                .build();

        SystemUser systemUser = SystemUser.builder()
                .id(1L)
                .firstName("Max")
                .lastName("Valencia")
                .createdAt(new Date())
                .build();

        Mockito.when(userService.getUserByContext()).thenReturn(systemUser);
        Mockito.when(productMapper.productDTOToProduct(Mockito.any(ProductDTO.class))).thenReturn(product);
        Mockito.when(productDAO.save(Mockito.any(Product.class))).thenReturn(product);

        ProductDTO productDTORespose = productServiceImp.saveProduct(productDTO);

        Assertions.assertNotNull(productDTORespose);
        Assertions.assertEquals(productDTORespose.getId(),productDTO.getId());
        Assertions.assertEquals(productDTORespose.getName(),productDTO.getName());
    }
}