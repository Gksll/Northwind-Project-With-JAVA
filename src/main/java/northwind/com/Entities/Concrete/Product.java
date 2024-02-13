package northwind.com.Entities.Concrete;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="productid")
    Integer productId;
    @Column(name="productname")
    String productName;
    @Column(name="supplierid")
    int supplierId;
    @Column(name="categoryid")
    int categoryId;
    @Column(name="unit")
    String unit;
    @Column(name="price")
    double price;
    @ManyToOne
    @JoinColumn(name = "categories")
    private Category categories;
}
