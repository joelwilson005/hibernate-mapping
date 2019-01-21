package info.joelwilson.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Entity(name = "Customer")
@Table(name = "customer")
public class Customer {

    //================================================================================
    // Properties
    //================================================================================
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;


    @OneToOne(
            mappedBy = "customer",
            orphanRemoval = true, // remove Cart if Customer is deleted
            fetch = FetchType.LAZY
    )
    private Cart cart;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private CustomerEmail customerEmail;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<ShippingAddress> shippingAddresses = new HashSet<>();

    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL
    )
    private Set<CustomerOrder> orders = new HashSet<>();

    @OneToMany(
            mappedBy = "customer",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private Set<ProductReview> productReviews = new HashSet<>();

    @OneToMany(mappedBy = "customer",
            orphanRemoval = true)
    private Set<ProductReviewComment> productReviewComments = new HashSet<>();


    //================================================================================
    // Constructors
    //================================================================================
    public Customer() {
    }

    public Customer(String firstName, String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;
    }


    //================================================================================
    // Accessors
    //================================================================================
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
        cart.setCustomer(this);
    }

    public CustomerEmail getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(CustomerEmail customerEmail) {
        this.customerEmail = customerEmail;
        customerEmail.setCustomer(this);
    }

    public Set<ShippingAddress> getShippingAddresses() {
        return shippingAddresses;
    }

    public void setShippingAddresses(Set<ShippingAddress> shippingAddresses) {
        this.shippingAddresses = shippingAddresses;
    }

    public Set<CustomerOrder> getOrders() {
        return orders;
    }

    public void setOrders(Set<CustomerOrder> orders) {
        this.orders = orders;
    }

    public Set<ProductReview> getProductReviews() {
        return productReviews;
    }

    public void setProductReviews(Set<ProductReview> productReviews) {
        this.productReviews = productReviews;
    }

    public Set<ProductReviewComment> getProductReviewComments() {
        return productReviewComments;
    }

    public void setProductReviewComments(Set<ProductReviewComment> productReviewComments) {
        this.productReviewComments = productReviewComments;
    }

}