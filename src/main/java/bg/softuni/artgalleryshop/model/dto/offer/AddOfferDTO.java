package bg.softuni.artgalleryshop.model.dto.offer;

import bg.softuni.artgalleryshop.model.enums.MediumEnum;
import bg.softuni.artgalleryshop.model.enums.StyleEnum;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class AddOfferDTO {
  @NotNull
  @Min(1)
  private Long productId;
  @NotNull
  private MediumEnum medium;
  @Positive
  @NotNull
  private BigDecimal price;
  @Positive
  @NotNull
  private BigDecimal weight;
  @Min(1500)
  @NotNull
  private Integer year;
  @NotEmpty
  private String description;
  @NotNull
  private StyleEnum style;
  @NotEmpty
  private String imageUrl;

  private String author;

  public Long getProductId() {
    return productId;
  }

  public AddOfferDTO setProductId(Long productId) {
    this.productId = productId;
    return this;
  }
  public MediumEnum getMedium() {
    return medium;
  }

  public AddOfferDTO setMedium(MediumEnum medium) {
    this.medium = medium;
    return this;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public AddOfferDTO setPrice(BigDecimal price) {
    this.price = price;
    return this;
  }

  public BigDecimal getWeight() {
    return weight;
  }

  public AddOfferDTO setWeight(BigDecimal weight) {
    this.weight = weight;
    return this;
  }

  public Integer getYear() {
    return year;
  }

  public AddOfferDTO setYear(Integer year) {
    this.year = year;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public AddOfferDTO setDescription(String description) {
    this.description = description;
    return this;
  }

  public StyleEnum getStyle() {
    return style;
  }

  public AddOfferDTO setStyle(StyleEnum style) {
    this.style = style;
    return this;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public AddOfferDTO setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  public String getAuthor() {
    return author;
  }

  public AddOfferDTO setAuthor(String author) {
    this.author = author;
    return this;
  }


  @Override
  public String toString() {
    return "AddOfferDTO{" +
            "productId=" + productId +
            ", medium=" + medium +
            ", price=" + price +
            ", weight=" + weight +
            ", year=" + year +
            ", description='" + description + '\'' +
            ", style=" + style +
            ", imageUrl='" + imageUrl + '\'' +
            ", author='" + author + '\'' +
            '}';
  }
}
