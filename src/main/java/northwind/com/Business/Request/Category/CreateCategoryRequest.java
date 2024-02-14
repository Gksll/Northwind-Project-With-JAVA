package northwind.com.Business.Request.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import northwind.com.Entities.Concrete.Category;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCategoryRequest {
    private String categoryName;
    private String description;
}
