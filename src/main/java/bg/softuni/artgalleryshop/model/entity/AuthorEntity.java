package bg.softuni.artgalleryshop.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class AuthorEntity extends BaseEntity{

  @Column(nullable = false)
  private String name;

  @OneToMany(
      mappedBy = "author",
      fetch = FetchType.EAGER,
      cascade = CascadeType.ALL
  )
  private List<ProductEntity> products = new ArrayList<>();

  public String getName() {
    return name;
  }

  public AuthorEntity setName(String name) {
    this.name = name;
    return this;
  }

  public List<ProductEntity> getProducts() {
    return products;
  }

  public AuthorEntity setProducts(List<ProductEntity> products) {
    this.products = products;
    return this;
  }

  @Override
  public String toString() {
    return "AuthorEntity{" +
            "name='" + name + '\'' +
            ", products=" + products +
            '}';
  }
}
