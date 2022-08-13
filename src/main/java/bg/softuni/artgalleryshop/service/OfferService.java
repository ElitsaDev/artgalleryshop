package bg.softuni.artgalleryshop.service;

import bg.softuni.artgalleryshop.model.dto.offer.OfferDetailDTO;
import bg.softuni.artgalleryshop.model.entity.ProductEntity;
import bg.softuni.artgalleryshop.repository.AuthorRepository;
import bg.softuni.artgalleryshop.repository.ProductRepository;
import bg.softuni.artgalleryshop.repository.OfferRepository;
import bg.softuni.artgalleryshop.repository.UserRepository;
import bg.softuni.artgalleryshop.model.dto.offer.AddOfferDTO;
import bg.softuni.artgalleryshop.model.entity.OfferEntity;
import bg.softuni.artgalleryshop.model.entity.UserEntity;
import bg.softuni.artgalleryshop.model.mapper.OfferMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final ProductRepository modelRepository;

    private final AuthorRepository authorRepository;
    private final OfferMapper offerMapper;

    public OfferService(OfferRepository offerRepository,
                        UserRepository userRepository,
                        ProductRepository modelRepository,
                        AuthorRepository authorRepository, OfferMapper offerMapper) {
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.modelRepository = modelRepository;
        this.authorRepository = authorRepository;
        this.offerMapper = offerMapper;
    }

    public Page<OfferDetailDTO> getAllOffers(Pageable pageable) {
        return offerRepository.
            findAll(pageable).
            map(offerMapper::offerEntityToCardListingOfferDto);

    }

    public void addOffer(AddOfferDTO addOfferDTO, UserDetails userDetails) {
        OfferEntity newOffer = offerMapper.
                addOfferDtoToOfferEntity(addOfferDTO);

        UserEntity seller = userRepository.findByUsername(userDetails.getUsername()).
                orElseThrow();

        ProductEntity product = modelRepository.findById(addOfferDTO.getProductId()).
                orElseThrow();


        newOffer.setProduct(product);
        newOffer.setSeller(seller);

        offerRepository.save(newOffer);
    }

    public List<OfferDetailDTO> findOfferByOfferName(String query) {
        return this.offerRepository
                .findAllByProduct_NameContains(query)
                .stream()
                .map(offerMapper::offerEntityToCardListingOfferDto)
                .toList();
    }

    public OfferDetailDTO getOfferById(UUID id) {
        return this.offerRepository
                .findById(id)
                .map(offerMapper::offerEntityToOfferDetailDTO)
                .orElse(null);
    }

    public Optional<OfferEntity> findOfferById(UUID id) {
        return this.offerRepository.findById(id);
    }

    public void deleteOfferById(UUID uuid) {
        this.offerRepository.deleteById(uuid);
    }


    public Optional<Object> findOfferByUUID(UUID uuid) {
        return this.offerRepository
                        .findById(uuid)
                                .map(offerMapper::offerEntityToOfferDetailDTO);
    }

}
