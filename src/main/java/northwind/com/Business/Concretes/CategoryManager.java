package northwind.com.Business.Concretes;

import northwind.com.DataAccess.CategoryRepository;
import northwind.com.Entities.Concrete.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Service
public class CategoryManager {

    @Autowired
    private CategoryRepository categoryRepository;


    @GetMapping("/getAll")
     public List<Category> getAllCategories()
    {
       return categoryRepository.findAll();
    }

    @GetMapping("/getById")
    public Category getById(int id)
    {
      return  categoryRepository.findById(id).get();
    }



}
