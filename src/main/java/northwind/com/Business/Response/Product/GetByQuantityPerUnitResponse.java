package northwind.com.Business.Response.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByQuantityPerUnitResponse {

    private String quantityPerUnit;

}
