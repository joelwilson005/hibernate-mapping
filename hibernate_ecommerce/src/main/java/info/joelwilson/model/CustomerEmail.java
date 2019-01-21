package info.joelwilson.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "CustomerEmail")
@Table(name = "customer_email")
public class CustomerEmail {

    //================================================================================
    // Properties
    //================================================================================
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "date_added")
    private LocalDateTime dateAdded;

    @Column(name = "is_verified")
    private boolean isVerfied;

    @OneToOne
    @JoinColumn(name = "fk_customer_id")
    private Customer customer;


    //================================================================================
    // Accessors
    //================================================================================
    public CustomerEmail() {}

    public CustomerEmail(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public boolean isVerfied() {
        return isVerfied;
    }

    public void setVerfied(boolean verfied) {
        isVerfied = verfied;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
