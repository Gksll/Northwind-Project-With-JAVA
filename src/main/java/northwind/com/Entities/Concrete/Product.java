package northwind.com.Entities.Concrete;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    int productId;

    @Column(name="product_name")
    String productName;

    @Column(name="supplier_id")
    int supplierId;

    @Column(name="quantity_per_unit")
    String quantityPerUnit;

    @Column(name="unit_price")
    double unitPrice;

    @Column(name="units_in_stock")
    int unitsInStock;

    @Column(name="units_in_order")
    int unitsInOrder;

    @Column(name="reorder_level")
    int reorderLevel;

    @Column(name="discontinued")
    int discontinued;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
