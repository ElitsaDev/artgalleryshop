package bg.softuni.artgalleryshop.service;

import bg.softuni.artgalleryshop.model.view.ProductViewModel;
import bg.softuni.artgalleryshop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductViewModel> findAll() {
      return   this.productRepository.findAll().stream().map(p -> new ProductViewModel(
                p.getName(),
                p.getCategory().name(),
                p.getImageUrl(),
                p.getYear(),
                p.getLength(),
                p.getWidth(),
                p.getAuthor().getName())).collect(Collectors.toList());
    }
}
