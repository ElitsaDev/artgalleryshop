package bg.softuni.artgalleryshop.web;

import bg.softuni.artgalleryshop.model.dto.OrderDTO;
import bg.softuni.artgalleryshop.model.dto.offer.OfferDetailDTO;
import bg.softuni.artgalleryshop.model.dto.offer.SearchOfferDTO;
import bg.softuni.artgalleryshop.model.entity.OfferEntity;
import bg.softuni.artgalleryshop.service.OfferService;
import bg.softuni.artgalleryshop.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@Controller
public class OrderController {
    private final OrderService orderService;
    private final OfferService offerService;
    private final OfferController offerController;

    public OrderController(OrderService orderService,
                           OfferService offerService, OfferController offerController) {
        this.orderService = orderService;

        this.offerService = offerService;
        this.offerController = offerController;
    }

    //http://localhost:8080/offers/5ebdd23e-7bf3-4166-ab67-98242b871f68/details

    @RequestMapping("/order")
    @ResponseStatus(HttpStatus.OK)
    public String order(@RequestParam("offerId") UUID offerId,
                        @Valid OrderDTO orderDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes,
                        Model model){
        Optional<OfferEntity> offer = this.offerService.findOfferById(offerId);


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("orderDTO", orderDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderDTO",
                    bindingResult);
            return "redirect:/offers";
        }
        if(offer.isPresent()) {
            this.orderService.addOrder(offer.get(), orderDTO);
        }
        return "details";
    }

    @ModelAttribute(name = "orderDTO")
    private OrderDTO initOrder() {
        return new OrderDTO();
    }

}
