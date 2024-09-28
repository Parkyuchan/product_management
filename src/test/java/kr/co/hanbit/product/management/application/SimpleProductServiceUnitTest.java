package kr.co.hanbit.product.management.application;

import kr.co.hanbit.product.management.domain.Product;
import kr.co.hanbit.product.management.domain.ProductRepository;
import kr.co.hanbit.product.management.presentation.ProductDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SimpleProductServiceUnitTest {

    //아무 기능을 하지 않는 목 객체 지정
    @Mock
    private ProductRepository productRepository;

    @Mock
    private ValidationService validationService;

    //목 객체를 주입해주는 애노테이션
    @InjectMocks
    private SimpleProductService simpleProductService;

    @Test
    @DisplayName("상품 추가 후에는 추가된 상품이 반환되어야한다.")
    void productAddTest() {
        ProductDto productDto = new ProductDto("연필", 300, 20);
        Long PRODUCT_ID = 1L;

        Product product = ProductDto.toEntity(productDto);
        product.setId(PRODUCT_ID);
        //목 객체가 어떻게 행동해야 하는지 정의(when에 해당하는 동작 수행하면 thenReturn 반환), any는 아무값이나 들어가는 것 의미
        when(productRepository.add(any())).thenReturn(product);

        ProductDto savedProductDto = simpleProductService.add(productDto);

        assertTrue(savedProductDto.getId().equals(PRODUCT_ID));
        assertTrue(savedProductDto.getName().equals(productDto.getName()));
        assertTrue(savedProductDto.getPrice().equals(productDto.getPrice()));
        assertTrue(savedProductDto.getAmount().equals(productDto.getAmount()));
    }
}
