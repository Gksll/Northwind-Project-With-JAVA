package northwind.com.Entities.Concrete;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @Column(name = "order_id")
    private Long order_id;

    @Column(name = "customer_id")
    private String customer_id;

    @Column(name = "employee_id")
    private Long employee_id;

    @Column(name = "order_date")
    private Date order_date;

    @Column(name = "required_date")
    private Date required_date;

    @Column(name = "shipped_date")
    private Date shipped_date;

    @Column(name = "ship_via")
    private Long ship_via;

    @Column(name = "freight")
    private double freight;

    @Column(name = "ship_name")
    private String ship_name;

    @Column(name = "ship_address")
    private String ship_address;

    @Column(name = "ship_city")
    private String ship_city;

    @Column(name = "ship_region")
    private String ship_region;

    @Column(name = "ship_postal_code")
    private String ship_postal_code;

    @Column(name = "ship_country")
    private String ship_country;

    @OneToMany(mappedBy = "orderEntity")
    private List<OrderDetailEntity> orderDetails;


    // ...
}

