package bg.softuni.artgalleryshop.model.mapper;

import bg.softuni.artgalleryshop.model.entity.ProductEntity;
import bg.softuni.artgalleryshop.model.view.ProductViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "author", target = "author.name")
    ProductEntity productViewModelToProductEntity(ProductViewModel productViewModel);
    @Mapping(source = "author.name", target = "author")
    ProductViewModel productEntityToProductViewModel(ProductEntity productEntity);
}
