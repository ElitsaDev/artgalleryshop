package bg.softuni.artgalleryshop.model.view;

import bg.softuni.artgalleryshop.model.entity.AuthorEntity;
import bg.softuni.artgalleryshop.model.enums.CategoryEnum;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

public class ProductViewModel {
    private String name;

    private String category;

    private String imageUrl;

    private int year;

    private BigDecimal length;

    private BigDecimal width;

    private String author;

    public ProductViewModel() {
    }

    public ProductViewModel(String name, String category,
                            String imageUrl, int year, BigDecimal length,
                            BigDecimal width, String author) {
        this.name = name;
        this.category = category;
        this.imageUrl = imageUrl;
        this.year = year;
        this.length = length;
        this.width = width;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
