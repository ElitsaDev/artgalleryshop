package bg.softuni.artgalleryshop.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    @Column(nullable = false)
    private String clientPhoneNumber;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne
    private OfferEntity offer;

    @Column(nullable = false)
    private BigDecimal totalPrice;

    private LocalDateTime orderTime;

    @Column(name = "approve_conditions")
    private boolean approveConditions;

    public Order() {
    }

    public Order(String clientPhoneNumber,
                 Integer quantity,
                 OfferEntity offer,
                 BigDecimal totalPrice,
                 LocalDateTime orderTime,
                 boolean approveConditions) {
        this.clientPhoneNumber = clientPhoneNumber;
        this.quantity = quantity;
        this.offer = offer;
        this.totalPrice = totalPrice;
        this.orderTime = orderTime;
        this.approveConditions = approveConditions;
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

    public OfferEntity getOffer() {
        return offer;
    }

    public void setOffer(OfferEntity offer) {
        this.offer = offer;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public boolean isApproveConditions() {
        return approveConditions;
    }

    public void setApproveConditions(boolean approveConditions) {
        this.approveConditions = approveConditions;
    }

}
