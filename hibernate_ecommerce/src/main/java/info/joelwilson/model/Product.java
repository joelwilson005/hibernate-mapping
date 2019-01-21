package info.joelwilson.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

//================================================================================
// Named Queries
//================================================================================
@NamedNativeQueries(
        @NamedNativeQuery(
                name = "findAllProducts",
                query = "SELECT * FROM hibernate_ecommerce.public.product"
        )
)

@Entity(name = "Product")
@Table(name = "product")
public class Product {

    //================================================================================
    // Properties
    //================================================================================
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "date_added")
    private LocalDateTime dateAdded;

    @Column(name = "price", columnDefinition = "NUMERIC")
    private double price;

    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<ProductImage> images;

    @OneToMany(
            mappedBy = "product",
            orphanRemoval = true
    )
    private Set<ProductReview> reviews = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_product_category_id")
    private ProductCategory category;

    @ManyToOne
    @JoinColumn(name = "fk_manufacturer_id")
    private Manufacturer manufacturer;


    //================================================================================
    // Accessors
    //================================================================================
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<ProductImage> getImages() {
        return images;
    }

    public void setImages(Set<ProductImage> images) {
        this.images = images;
    }

    public Set<ProductReview> getReviews() {
        return reviews;
    }

    public void setReviews(Set<ProductReview> reviews) {
        this.reviews = reviews;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
}
