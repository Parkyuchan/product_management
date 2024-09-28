package kr.co.hanbit.product.management.application;

import kr.co.hanbit.product.management.domain.EntityNotFoundException;
import kr.co.hanbit.product.management.presentation.ProductDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class SimpleProductServiceTest {

    @Autowired
    SimpleProductService simpleProductService;

//    @Transactional
    @Test
    @DisplayName("상품을 추가한 후 id로 조회하면 해당 상품이 조회되어야 한다.")
    void productAddAndFindByIdTest() {
        ProductDto productDto =  new ProductDto("연필", 300, 20);

        ProductDto savedProductDto = simpleProductService.add(productDto);
        Long savedProductId = savedProductDto.getId();

        ProductDto foundProductDto = simpleProductService.findById(savedProductId);

        assertTrue(savedProductDto.getId().equals(foundProductDto.getId()));
        assertTrue(savedProductDto.getName().equals(foundProductDto.getName()));
        assertTrue(savedProductDto.getPrice().equals(foundProductDto.getPrice()));
        assertTrue(savedProductDto.getAmount().equals(foundProductDto.getAmount()));

    }

    @Test
    @DisplayName("존재하지 않는 상품 id로 조회하면 EntityNotFoundException이 발생해야한다.")
    void findProductNotExistIdTest() {
        Long notExistId = -1L;

        //예외발생 테스트 메서드
        //첫번째 매개변수로는 어떤 예외가 발생해야 하는지 지정
        //두번째 매개변수는 예외가 발생해야 할 코드를 넣을 수 있는 람다 표현식
        assertThrows(EntityNotFoundException.class, () -> {
            simpleProductService.findById(notExistId);
        });
    }
}