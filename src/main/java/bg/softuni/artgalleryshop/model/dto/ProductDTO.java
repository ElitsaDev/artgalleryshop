package bg.softuni.artgalleryshop.model.dto;

public class ProductDTO {
  private long id;
  private String name;

  public long getId() {
    return id;
  }

  public ProductDTO setId(long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public ProductDTO setName(String name) {
    this.name = name;
    return this;
  }
}
