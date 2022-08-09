package bg.softuni.artgalleryshop.service;

import bg.softuni.artgalleryshop.model.dto.AuthorDTO;
import bg.softuni.artgalleryshop.model.dto.ProductDTO;
import bg.softuni.artgalleryshop.model.entity.AuthorEntity;
import bg.softuni.artgalleryshop.model.entity.ProductEntity;
import bg.softuni.artgalleryshop.model.view.AuthorViewModel;
import bg.softuni.artgalleryshop.model.view.ProductViewModel;
import bg.softuni.artgalleryshop.repository.AuthorRepository;
import bg.softuni.artgalleryshop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {

  private final AuthorRepository authorRepository;
  private final ProductRepository productRepository;

  public AuthorService(AuthorRepository authorRepository,
                       ProductRepository productRepository) {
    this.authorRepository = authorRepository;
    this.productRepository = productRepository;
  }

  public List<AuthorDTO> getAllAuthors() {
    return authorRepository.
        findAll().
        stream().
        map(this::mapAuthor).
        collect(Collectors.toList());
  }
  private AuthorDTO mapAuthor(AuthorEntity authorEntity) {
    List<ProductDTO> products = authorEntity.
        getProducts().
        stream().
        map(this::mapProduct).
        toList();

    return new AuthorDTO().
        setProducts(products).
        setName(authorEntity.getName());
  }
  private ProductDTO mapProduct(ProductEntity productEntity) {
    return new ProductDTO().
        setId(productEntity.getId()).
        setName(productEntity.getName());
  }

  public List<AuthorViewModel> getAllAuthorsProducts() {
    List<AuthorViewModel> result = new ArrayList<>();
    List<ProductEntity> allProducts = productRepository.findAll();


    allProducts.forEach(product -> {
        // example The Mona Lisa -> Leonardo
        AuthorEntity authorEntity = product.getAuthor();

        Optional<AuthorViewModel> authorViewModelOpt = findByName(result, authorEntity.getName());
        if(authorViewModelOpt.isEmpty()){
          AuthorViewModel newAuthor = new AuthorViewModel();

          newAuthor.setName(authorEntity.getName());
          result.add(newAuthor);
          authorViewModelOpt = Optional.of(newAuthor);
        }
        //map ProductEntity to ProductViewModel
        ProductViewModel newProduct = new ProductViewModel(
                product.getName(),
                product.getCategory().name(),
                product.getImageUrl(),
                product.getYear(),
                product.getLength(),
                product.getWidth(),
                product.getAuthor().getName());

        authorViewModelOpt.get().addingProductVM(newProduct);

    });

    return result;
  }

  private static Optional<AuthorViewModel> findByName(List<AuthorViewModel> all, String name) {
    return all.stream().filter(m -> m.getName().equals(name)).findAny();
  }
}
