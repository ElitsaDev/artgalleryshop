package bg.softuni.artgalleryshop.model.mapper;

import bg.softuni.artgalleryshop.model.dto.offer.OfferDetailDTO;
import bg.softuni.artgalleryshop.model.dto.offer.AddOfferDTO;
import bg.softuni.artgalleryshop.model.entity.OfferEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OfferMapper {
  OfferEntity addOfferDtoToOfferEntity(AddOfferDTO addOfferDTO);

  @Mapping(source = "product.name", target = "product")
  @Mapping(source = "product.author.name", target = "author")
  OfferDetailDTO offerEntityToCardListingOfferDto(OfferEntity offerEntity);
  @Mapping(source = "product.name", target = "product")
  @Mapping(source = "product.author.name", target = "author")
  OfferDetailDTO offerEntityToOfferDetailDTO(OfferEntity offerEntity);
}
