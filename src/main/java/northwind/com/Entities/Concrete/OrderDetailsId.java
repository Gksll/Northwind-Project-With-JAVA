package northwind.com.Entities.Concrete;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderDetailsId implements Serializable {

    private int order_id;

    private int product_id;
}
