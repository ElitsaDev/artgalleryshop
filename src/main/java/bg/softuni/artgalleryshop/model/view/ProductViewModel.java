package bg.softuni.artgalleryshop.model.view;

import java.math.BigDecimal;

public class ProductViewModel {
    private String name;

    private String category;

    private String imageUrl;

    private int year;

    private Integer length;

    private Integer width;

    private String author;

    public ProductViewModel() {
    }

    public ProductViewModel(String name, String category,
                            String imageUrl, int year, BigDecimal length,
                            BigDecimal width, String author) {
        this.name = name;
        this.category = category.charAt(0) + category.substring(1,category.length()).toLowerCase();
        this.imageUrl = imageUrl.replace("static","");
        this.year = year;
        this.length = length.intValue();
        this.width = width.intValue();
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

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
