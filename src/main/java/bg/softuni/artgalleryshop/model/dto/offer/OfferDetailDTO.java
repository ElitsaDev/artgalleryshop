package bg.softuni.artgalleryshop.model.dto.offer;

import bg.softuni.artgalleryshop.model.enums.MediumEnum;
import bg.softuni.artgalleryshop.model.enums.StyleEnum;

import java.math.BigDecimal;
import java.util.UUID;

public class OfferDetailDTO {

    private UUID id;
    private String imageUrl;
    private Integer year;
    private String author;
    private String product;
    private BigDecimal weight;
    private BigDecimal price;
    private MediumEnum medium;
    private StyleEnum style;

    public OfferDetailDTO() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public MediumEnum getMedium() {
        return medium;
    }

    public void setMedium(MediumEnum medium) {
        this.medium = medium;
    }

    public StyleEnum getStyle() {
        return style;
    }

    public void setStyle(StyleEnum style) {
        this.style = style;
    }

    public String getOfferHighlight() {

        return this.year + " " + this.author + " - " + this.product;
    }

    public UUID getId() {
        return id;
    }

    public OfferDetailDTO setId(UUID id) {
        this.id = id;
        return this;
    }
}
