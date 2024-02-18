package northwind.com.Business.Request.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {

    private String productName;

    private Integer supplierId;

    private String quantityPerUnit;

    private Double unitPrice;

    private Integer unitsInStock;

    private Integer unitsOnOrder;

    private Integer reorderLevel;

    private Integer discontinued;

    private Integer categoryId;
}
