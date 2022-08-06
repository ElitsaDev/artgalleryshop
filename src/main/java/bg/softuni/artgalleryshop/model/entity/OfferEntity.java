package bg.softuni.artgalleryshop.model.entity;

import bg.softuni.artgalleryshop.model.enums.MediumEnum;
import bg.softuni.artgalleryshop.model.enums.StyleEnum;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "offers")
public class OfferEntity  {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
      name = "UUID",
      strategy = "org.hibernate.id.UUIDGenerator"
  )
  @Type(type = "uuid-char")
  private UUID id;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private MediumEnum medium;
  private String imageUrl;

  private BigDecimal weight;

  @Column(nullable = false)
  private BigDecimal price;

  @Column(nullable = false)
  private String description;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private StyleEnum style;

  private int year;
  @ManyToOne
  private ProductEntity product;

  @ManyToOne
  private UserEntity seller;

  public UUID getId() {
    return id;
  }

  public OfferEntity setId(UUID id) {
    this.id = id;
    return this;
  }

  public MediumEnum getMedium() {
    return medium;
  }

  public OfferEntity setMedium(MediumEnum medium) {
    this.medium = medium;
    return this;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public OfferEntity setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  public BigDecimal getWeight() {
    return weight;
  }

  public OfferEntity setWeight(BigDecimal weight) {
    this.weight = weight;
    return this;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public OfferEntity setPrice(BigDecimal price) {
    this.price = price;
    return this;
  }

  public StyleEnum getStyle() {
    return style;
  }

  public OfferEntity setStyle(StyleEnum style) {
    this.style = style;
    return this;
  }

  public int getYear() {
    return year;
  }

  public OfferEntity setYear(int year) {
    this.year = year;
    return this;
  }

  public ProductEntity getProduct() {
    return product;
  }

  public OfferEntity setProduct(ProductEntity product) {
    this.product = product;
    return this;
  }

  public UserEntity getSeller() {
    return seller;
  }

  public OfferEntity setSeller(UserEntity seller) {
    this.seller = seller;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public OfferEntity setDescription(String description) {
    this.description = description;
    return this;
  }
}
