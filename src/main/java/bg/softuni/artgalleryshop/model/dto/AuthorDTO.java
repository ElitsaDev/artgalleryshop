package bg.softuni.artgalleryshop.model.dto;

import java.util.ArrayList;
import java.util.List;

public class AuthorDTO {
  private String name;
  private List<ProductDTO> products;

  public String getName() {
    return name;
  }

  public AuthorDTO setName(String name) {
    this.name = name;
    return this;
  }

  public List<ProductDTO> getProducts() {
    return products;
  }

  public AuthorDTO setProducts(List<ProductDTO> products) {
    this.products = products;
    return this;
  }

  public AuthorDTO addProduct(ProductDTO product) {
    if (this.products == null) {
      this.products = new ArrayList<>();
    }
    this.products.add(product);
    return this;
  }
}
