package bg.softuni.artgalleryshop.service;

import bg.softuni.artgalleryshop.model.entity.AuthorEntity;
import bg.softuni.artgalleryshop.model.entity.ProductEntity;
import bg.softuni.artgalleryshop.model.enums.CategoryEnum;
import bg.softuni.artgalleryshop.model.mapper.ProductMapper;
import bg.softuni.artgalleryshop.model.view.ProductViewModel;
import bg.softuni.artgalleryshop.repository.AuthorRepository;
import bg.softuni.artgalleryshop.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {
    @Mock
    private  AuthorRepository authorRepository;
    @Mock
    private  ProductRepository productRepository;

    private ProductMapper productMapper ;

    private ProductEntity productEntity;
    private AuthorEntity authorEntity;

    private AuthorService toTest;

    @BeforeEach
    void setUp(){
        toTest = new AuthorService(authorRepository,productRepository);
        productEntity = new ProductEntity();

        productEntity.setAuthor(new AuthorEntity().setName("TestAuthor"))
                .setYear(1950)
                .setName("TestProductName")
                .setLength(BigDecimal.valueOf(100.0))
                .setWidth(BigDecimal.valueOf(100.0))
                .setImageUrl("TestURL")
                .setCategory(CategoryEnum.DRAWINGS);
    }

    @Test
    void getAllAuthors(){
       long num1 = toTest.getAllAuthors().size();
       long num2 = authorRepository.findAll().stream().count();

        Assertions.assertEquals(num1,num2);
    }

    @Test
    void getAllAuthorsProducts(){
        var aut = toTest.getAllAuthorsProducts().get(0);
       var author= authorRepository.findByName(authorEntity);

        Assertions.assertEquals(author.getProducts().size(), aut.getProducts().size());


        //TODO
    }
}
