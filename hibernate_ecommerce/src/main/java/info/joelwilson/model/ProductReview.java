package info.joelwilson.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "ProductReview")
@Table(name = "product_review")
public class ProductReview {

    //================================================================================
    // Properties
    //================================================================================
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "content")
    private String content;

    @Column(name = "date_added")
    private LocalDateTime dateAdded;

    @ManyToOne
    @JoinColumn(name = "fk_customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "fk_product_id")
    private Product product;

    @OneToMany(
            mappedBy = "productReview",
            orphanRemoval = true
    )
    private Set<ProductReviewComment> productReviewComments = new HashSet<>();


    //================================================================================
    // Accessors
    //================================================================================
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Set<ProductReviewComment> getProductReviewComments() {
        return productReviewComments;
    }

    public void setProductReviewComments(Set<ProductReviewComment> productReviewComments) {
        this.productReviewComments = productReviewComments;
    }

}
