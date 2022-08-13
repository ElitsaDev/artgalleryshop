package bg.softuni.artgalleryshop.service;

import bg.softuni.artgalleryshop.model.dto.OrderDTO;
import bg.softuni.artgalleryshop.model.entity.OfferEntity;
import bg.softuni.artgalleryshop.model.entity.Order;
import bg.softuni.artgalleryshop.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class OrderService {
    private final OfferService offerService;
    private final OrderRepository orderRepository;

    public OrderService(OfferService offerService,
                        OrderRepository orderRepository) {

        this.offerService = offerService;
        this.orderRepository = orderRepository;
    }

    public void addOrder(OfferEntity offer,  OrderDTO orderDTO) {
        orderDTO.setOrderTime(LocalDateTime.now());
        orderDTO.setOffer(offer.getId());

            Order order = new Order(
                    orderDTO.getClientPhoneNumber(),
                    orderDTO.getQuantity(),
                    offer,
                    offer.getPrice().multiply(BigDecimal.valueOf(orderDTO.getQuantity())) ,
                    orderDTO.getOrderTime(), true);
            this.orderRepository.save(order);

    }
}
