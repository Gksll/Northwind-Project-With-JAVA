package northwind.com.API;

import northwind.com.Business.Concretes.CategoryManager;
import northwind.com.Entities.Concrete.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {
    @Autowired
    CategoryManager categoryManager;

    @GetMapping("/getAll")
    public List<Category> getAll() {
        return categoryManager.getAllCategories();
    }

    @GetMapping("/getbyid")
    public Category getById(int id) {
        return categoryManager.getById(id);
    }
}
