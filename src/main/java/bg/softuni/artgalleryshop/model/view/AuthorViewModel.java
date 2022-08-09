package bg.softuni.artgalleryshop.model.view;

import java.util.ArrayList;
import java.util.List;

public class AuthorViewModel {
    private String name;
    private List<ProductViewModel> products = new ArrayList<>();

    public List<ProductViewModel> getProducts() {
        return products;
    }

    public AuthorViewModel setProducts(List<ProductViewModel> products) {
        this.products = products;
        return this;
    }

    public AuthorViewModel addingProductVM(ProductViewModel productViewModel){
        this.products.add(productViewModel);
        return this;
    }

    public String getName() {
        return name;
    }

    public AuthorViewModel setName(String name) {
        this.name = name;
        return this;
    }
}
