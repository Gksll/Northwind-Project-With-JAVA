package northwind.com.Business.Request.Supplier;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeleteSupplierRequest {
    private int supplierId;
}
