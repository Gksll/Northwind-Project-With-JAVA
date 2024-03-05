package northwind.com.Business.Response.Supplier;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCategoryResponse {
    private int categoryId;
    private String categoryName;
    private String description;
}
