package info.joelwilson.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "CustomerOrder")
@Table(name = "customer_order")
public class CustomerOrder {

    //================================================================================
    // Properties
    //================================================================================
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "order_charge_paid")
    private boolean isOrderChargePaid;

    @ManyToOne
    @JoinColumn(name = "fk_customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "fk_shipping_address_id")
    private ShippingAddress shippingAddress;


    @OneToMany(mappedBy = "customerOrder", cascade = CascadeType.ALL)
    private Set<OrderProduct> orderProducts = new HashSet<>();


    //================================================================================
    //  Accessors
    //================================================================================
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public boolean isOrderChargePaid() {
        return isOrderChargePaid;
    }

    public void setOrderChargePaid(boolean orderChargePaid) {
        isOrderChargePaid = orderChargePaid;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Set<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(Set<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

}
