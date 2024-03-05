package northwind.com.Business.Abstracts;

import northwind.com.Business.Request.Category.CreateCategoryRequest;
import northwind.com.Business.Request.Category.DeleteCategoryRequest;
import northwind.com.Business.Request.Category.UpdateCategoryRequest;
import northwind.com.Business.Response.Category.GetCategoryResponse;
import northwind.com.Business.Response.Category.GetAllCategoryResponse;
import northwind.com.Core.Results.DataResult;
import northwind.com.Core.Results.Result;
import northwind.com.Entities.Concrete.CategoryEntity;

import java.util.List;

public interface CategoryService {
     DataResult<List<GetAllCategoryResponse>> getAllCategories();

    DataResult<GetCategoryResponse> getById(GetCategoryResponse categoryResponse);

    Result addCategory(CreateCategoryRequest request);

    Result updateCategory(UpdateCategoryRequest request);

    Result deleteCategory(DeleteCategoryRequest request);
}
