package northwind.com.Business.Response.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCategoryResponse {
    private int categoryId;
    private String categoryName;
    private String description;
    private String picture;
}
