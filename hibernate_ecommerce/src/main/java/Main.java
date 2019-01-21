import info.joelwilson.model.*;
import info.joelwilson.util.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();


        //================================================================================
        // Creating objects
        //================================================================================
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName("Apple");
        manufacturer.setDateAdded(LocalDateTime.now());

        ProductCategory productCategory = new ProductCategory();
        productCategory.setName("Smart phone");
        productCategory.setDescription("Smart phone");
        productCategory.setDateAdded(LocalDateTime.now());

        Product iPhone = new Product();
        iPhone.setName("iPhone");
        iPhone.setPrice(1499);
        iPhone.setDateAdded(LocalDateTime.now());
        iPhone.setManufacturer(manufacturer);
        iPhone.setCategory(productCategory);

        ProductImage iPhoneImage = new ProductImage();
        iPhoneImage.setUrl("http://www.aws/s3/product/iphone/8393893.png");
        iPhoneImage.setDateAdded(LocalDateTime.now());
        iPhoneImage.setPrimary(true);
        iPhoneImage.setProduct(iPhone);

        Customer customer = new Customer();
        customer.setFirstName("Rich");
        customer.setLastName("Person");
        customer.setDateCreated(LocalDateTime.now());

        CustomerEmail customerEmail = new CustomerEmail();
        customerEmail.setEmailAddress("richperson@outlook.com");
        customerEmail.setDateAdded(LocalDateTime.now());
        customerEmail.setVerfied(true);
        customerEmail.setCustomer(customer);
        customer.setCustomerEmail(customerEmail);

        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setAddress1("31 Sky Line Drive");
        shippingAddress.setAddress2("Apt 3");
        shippingAddress.setCity("Kingston");
        shippingAddress.setParish("St. Andrew");
        shippingAddress.setNote("Green door");
        shippingAddress.setCustomer(customer);

        ProductReview productReview = new ProductReview();
        productReview.setProduct(iPhone);
        productReview.setContent("Too expensive, iPhones are way overpriced!!");
        productReview.setDateAdded(LocalDateTime.now());
        productReview.setCustomer(customer);

        Cart cart = new Cart();
        cart.setCustomer(customer);

        CartProduct cartProduct = new CartProduct();
        cartProduct.setCart(cart);
        cartProduct.setProduct(iPhone);
        cartProduct.setQuantity(2);
        cartProduct.setDateAdded(LocalDateTime.now());


        CustomerOrder order = new CustomerOrder();
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus("Pending");
        order.setOrderChargePaid(true);
        order.setShippingAddress(shippingAddress);

        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setCustomerOrder(order);
        orderProduct.setProduct(iPhone);
        orderProduct.setQuantity(2);
        orderProduct.setDateAdded(LocalDateTime.now());



        //================================================================================
        // Persisting objects
        //================================================================================

        EntityManager entityManager = HibernateUtil.getSessionFactory().createEntityManager();

        entityManager.getTransaction().begin();

        UUID customerId = null;

        try {
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
            customerId = (UUID) HibernateUtil.getSessionFactory().getPersistenceUnitUtil().getIdentifier(customer);
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new PersistenceException("Unable to save entity!");
        } finally {
            entityManager.close();
        }

        //================================================================================
        // Retrieving objects
        //================================================================================

        entityManager = HibernateUtil.getSessionFactory().createEntityManager();

        // JPQL query
        String sql = "from Customer";
        Query query = entityManager.createQuery(sql);

        // Typed query
        String typedQueryString = "from Customer";
        TypedQuery<Customer> typedQuery = entityManager.createQuery(typedQueryString, Customer.class);

        // Native SQL query
        String sqlString = "SELECT * FROM hibernate_ecommerce.public.customer";
        Query sqlQuery = entityManager.createNativeQuery(sqlString, Customer.class);


        TypedQuery<Customer> singleCustomerQuery = entityManager.createNamedQuery("findCustomerById", Customer.class);
        singleCustomerQuery.setParameter("id", customerId);

        List<Customer> customers = typedQuery.getResultList();

        List<Customer> customerList = sqlQuery.getResultList();

        customers.forEach(x -> System.out.println(x.getFirstName()));

        customerList.forEach(x -> System.out.println(x.getLastName()));

        Customer cust = singleCustomerQuery.getSingleResult();

        System.out.println(cust.getCustomerEmail().getEmailAddress());

        entityManager.close();


    }
}