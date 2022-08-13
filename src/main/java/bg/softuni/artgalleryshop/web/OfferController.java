package bg.softuni.artgalleryshop.web;

import bg.softuni.artgalleryshop.model.dto.offer.OfferDetailDTO;
import bg.softuni.artgalleryshop.model.dto.offer.SearchOfferDTO;
import bg.softuni.artgalleryshop.model.enums.UserRoleEnum;
import bg.softuni.artgalleryshop.model.user.ArtGalleryShopUserDetails;
import bg.softuni.artgalleryshop.model.dto.offer.AddOfferDTO;
import bg.softuni.artgalleryshop.service.AuthorService;
import bg.softuni.artgalleryshop.service.OfferService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/offers")
public class OfferController {
    private final OfferService offerService;
    private final AuthorService authorService;
    public OfferController(OfferService offerService,
                           AuthorService authorService) {
        this.offerService = offerService;
        this.authorService = authorService;
    }

    @GetMapping("/all")
    public String allOffers(
        Model model,
        @PageableDefault(
            sort = "price",
            direction = Sort.Direction.ASC,
            page = 0,
            size = 5) Pageable pageable) {

        model.addAttribute("offers", offerService.getAllOffers(pageable));

        return "offers";
    }

    @GetMapping("/add")
    public String addOffer(Model model) {
        if (!model.containsAttribute("addOfferModel")) {
            model.addAttribute("addOfferModel", new AddOfferDTO());
        }
        model.addAttribute("authors", authorService.getAllAuthors());

        return "offer-add";
    }

    @PostMapping("/add")
    public String addOffer(@Valid AddOfferDTO addOfferModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           @AuthenticationPrincipal ArtGalleryShopUserDetails userDetails) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addOfferModel", addOfferModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addOfferModel",
                    bindingResult);
            return "redirect:/offers/add";
        }

        offerService.addOffer(addOfferModel, userDetails);

        return "redirect:/offers/all";
    }

    @GetMapping("/search")
    public String searchOffer() {
        return "offer-search";
    }

    @PostMapping("/search")
    public String searchQuery(@Valid SearchOfferDTO searchOfferDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("searchOfferModel", searchOfferDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.searchOfferModel",
                    bindingResult);
            return "redirect:/offers/search";
        }

        return String.format("redirect:/offers/search/%s", searchOfferDTO.getQuery());
    }

    @GetMapping("/search/{query}")
    public String searchResults(@PathVariable String query, Model model) {
        model.addAttribute("offers", this.offerService.findOfferByOfferName(query));
        return "offer-search";
    }


    @ModelAttribute(name = "searchOfferModel")
    private SearchOfferDTO initSearchModel() {
        return new SearchOfferDTO();
    }

    @GetMapping("/{id}/details")
    public String getOfferDetail(@PathVariable("id") UUID id, Model model ) {
        OfferDetailDTO offer = offerService.getOfferById(id);
        model.addAttribute("offer", offer);

        return "/details";
    }

    @PostMapping("/details")
    public String getOfferDetail(@Valid OfferDetailDTO offerDetailDTO,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerDetailDTO", offerDetailDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.offerDetailDTO",
                    bindingResult);
            return "redirect:/search";
        }

        return "/details";

    }

    @GetMapping("/new-in")
    public String newIn(){
        return "new-in";
    }


    @GetMapping("/offer/{id}")
    public String offerDetails(@PathVariable String id,
                               Model model) {
        model.addAttribute("id", id);
        return "details";
    }

    @PostMapping("/offer/{id}")
    public String deleteOffer(@PathVariable("id") UUID uuid) {
        offerService.deleteOfferById(uuid);

        return "redirect:/offers/all";
    }
}
