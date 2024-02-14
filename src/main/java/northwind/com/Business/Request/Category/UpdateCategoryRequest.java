package northwind.com.Business.Request.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCategoryRequest  {
    private int categoryId;
    private String categoryName;
    private String description;
    private String picture;
}
