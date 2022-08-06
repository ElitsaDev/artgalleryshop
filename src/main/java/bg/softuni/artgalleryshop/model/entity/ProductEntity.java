package bg.softuni.artgalleryshop.model.entity;

import bg.softuni.artgalleryshop.model.enums.CategoryEnum;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class ProductEntity extends BaseEntity {

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private CategoryEnum category;

  @Column(nullable = false)
  private String imageUrl;

  private int year;

  private BigDecimal length;

  private BigDecimal width;
  @ManyToOne
  private AuthorEntity author;

  public String getName() {
    return name;
  }

  public ProductEntity setName(String name) {
    this.name = name;
    return this;
  }

  public CategoryEnum getCategory() {
    return category;
  }

  public ProductEntity setCategory(CategoryEnum category) {
    this.category = category;
    return this;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public ProductEntity setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  public int getYear() {
    return year;
  }

  public ProductEntity setYear(int year) {
    this.year = year;
    return this;
  }

  public BigDecimal getLength() {
    return length;
  }

  public ProductEntity setLength(BigDecimal length) {
    this.length = length;
    return this;
  }
  public BigDecimal getWidth() {
    return width;
  }

  public ProductEntity setWidth(BigDecimal width) {
    this.width = width;
    return this;
  }
  public AuthorEntity getAuthor() {
    return author;
  }

  public ProductEntity setAuthor(AuthorEntity author) {
    this.author = author;
    return this;
  }

  @Override
  public String toString() {
    return "ProductEntity{" +
        "name='" + name + '\'' +
        ", category=" + category +
        ", imageUrl='" + imageUrl + '\'' +
        ", year=" + year +
        ", length=" + length +
        ", width=" + width +
        ", author=" + (author != null ? author.getName() : null) +
        '}';
  }
}
