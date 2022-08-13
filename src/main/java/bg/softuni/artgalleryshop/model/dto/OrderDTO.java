package bg.softuni.artgalleryshop.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class OrderDTO {
    @NotNull
    private String clientPhoneNumber;

    @NotNull
    @Positive
    private Integer quantity;

    private UUID offerId;

    private BigDecimal totalPrice;
    private LocalDateTime orderTime;
    public OrderDTO() {
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public void setClientPhoneNumber(String clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public UUID getOffer(UUID offerId) {
        return offerId;
    }

    public void setOffer(UUID offerId) {
        this.offerId = offerId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getOrderTime() {
        return orderTime= LocalDateTime.now();
    }

    public void setOrderTime(LocalDateTime now) {
        this.orderTime = LocalDateTime.now();
    }
}
