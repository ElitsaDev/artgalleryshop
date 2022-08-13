package bg.softuni.artgalleryshop.service;

import bg.softuni.artgalleryshop.model.entity.AuthorEntity;
import bg.softuni.artgalleryshop.model.entity.ProductEntity;
import bg.softuni.artgalleryshop.model.mapper.ProductMapper;
import bg.softuni.artgalleryshop.model.mapper.ProductMapperImpl;
import bg.softuni.artgalleryshop.model.view.ProductViewModel;
import bg.softuni.artgalleryshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    private ProductService serviceToTest;
    private ProductEntity productEntity;
    private AuthorEntity authorEntity;

    private ProductMapper productMapper ;

    @BeforeEach
    void setUp() {
        productMapper = new ProductMapperImpl();
        serviceToTest = new ProductService(productRepository, productMapper);
    }


    public void saveInDB() {
        ProductViewModel productViewModel = new ProductViewModel();
        productViewModel.setCategory("DRAWINGS");
        productViewModel.setAuthor("TestAuthor");
        productViewModel.setName("The Mona Lisa");
        productViewModel.setLength(100);
        productViewModel.setWidth(100);
        productViewModel.setYear(1950);
        productViewModel.setImageUrl("/static/images/testPainting.jpg");

       var product = productMapper.productViewModelToProductEntity(productViewModel);
       productRepository.save(product);
    }

    @Test
    public void findAll() {
        saveInDB();
        serviceToTest.findAll();
        verify(productRepository).findAll();
    }

}
