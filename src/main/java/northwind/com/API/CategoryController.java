package northwind.com.API;

import northwind.com.Business.Abstracts.CategoryService;
import northwind.com.Business.Concretes.CategoryManager;
import northwind.com.Business.Request.Category.CreateCategoryRequest;
import northwind.com.Business.Request.Category.DeleteCategoryRequest;
import northwind.com.Business.Request.Category.UpdateCategoryRequest;
import northwind.com.Business.Response.GetAllCategoryResponse;
import northwind.com.Business.Response.GetCategoryResponse;
import northwind.com.Core.Results.DataResult;
import northwind.com.Core.Results.Result;
import northwind.com.Core.Results.SuccessDataResult;
import northwind.com.Entities.Concrete.Category;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryManager categoryManager) {
        this.categoryService = categoryManager;
    }
        @GetMapping("/getall")
    public DataResult<List<GetAllCategoryResponse>> getAll() {
       return new SuccessDataResult<>(categoryService.getAllCategories().getData(),categoryService.getAllCategories().getMessage());
    }

    @GetMapping("/getbyid")
    public DataResult<Category> getById(GetCategoryResponse response) {
        return new SuccessDataResult<>(categoryService.getById(response).getData(), categoryService.getById(response).getMessage());
    }
    @PostMapping("/add")
    public Result Add(@RequestBody CreateCategoryRequest createCategoryRequest)
    {
        return categoryService.addCategory(createCategoryRequest);
    }
    @PostMapping("/update")
    public Result Update(@RequestBody UpdateCategoryRequest updateCategoryRequest)
    {
        return categoryService.updateCategory(updateCategoryRequest);
    }
    @DeleteMapping("/delete")
    public Result Delete(@RequestBody DeleteCategoryRequest deleteCategoryRequest)
    {
        return categoryService.deleteCategory(deleteCategoryRequest);
    }
}
