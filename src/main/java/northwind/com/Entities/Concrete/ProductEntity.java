package northwind.com.Entities.Concrete;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","categoryEntity","supplierEntity"})

public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "quantity_per_unit")
    private String quantityPerUnit;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Column(name = "units_in_stock")
    private Integer unitsInStock;

    @Column(name = "units_on_order")
    private Integer unitsOnOrder;

    @Column(name = "reorder_level")
    private Integer reorderLevel;

    @Column(name = "discontinued")
    private Integer discontinued;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryEntity;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private SupplierEntity supplierEntity;

    @OneToMany(mappedBy = "productEntity")
    private List<OrderDetailEntity> orderDetails;

    public String toString() {
        return "ProductEntity{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                (categoryEntity != null ? ", categoryName='" + categoryEntity.getCategoryName() + '\'' : "") +
                (supplierEntity != null ? ", supplierName='" + supplierEntity.getCompanyName() + '\'' : "") +
                (orderDetails != null ? ", orderDetail='" + orderDetails.stream().findFirst().get().getDiscount() + '\'' : "") +
                '}';
    }

}
