package bg.softuni.artgalleryshop.service;

import bg.softuni.artgalleryshop.model.mapper.ProductMapper;
import bg.softuni.artgalleryshop.model.view.ProductViewModel;
import bg.softuni.artgalleryshop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper mapper;
    public ProductService(ProductRepository productRepository, ProductMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    public List<ProductViewModel> findAll() {
      return   this.productRepository.findAll().stream().map(mapper::productEntityToProductViewModel).collect(Collectors.toList());
    }
}
