package bg.softuni.artgalleryshop.service;

import bg.softuni.artgalleryshop.model.dto.AuthorDTO;
import bg.softuni.artgalleryshop.model.dto.ProductDTO;
import bg.softuni.artgalleryshop.model.entity.AuthorEntity;
import bg.softuni.artgalleryshop.model.entity.ProductEntity;
import bg.softuni.artgalleryshop.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

  private AuthorRepository authorRepository;

  public AuthorService(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
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
}
