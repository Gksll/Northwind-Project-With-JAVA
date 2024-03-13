package northwind.com.Business.Response.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllOrderResponse {
    private Integer orderId;
    private String customerId;
    private Integer employeeId;
    private Date orderDate;
    private Date requiredDate;
    private Date shippedDate;
    private Integer shipVia;
    private Double freight;
    private String shipName;
    private String shipAddress;
    private String shipCity;
    private String shipRegion;
    private String shipPostalCode;
    private String shipCountry;
}
