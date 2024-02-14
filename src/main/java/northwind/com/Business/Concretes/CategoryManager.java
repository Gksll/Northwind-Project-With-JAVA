package northwind.com.Business.Concretes;

import northwind.com.Business.Abstracts.CategoryService;
import northwind.com.Business.Request.Category.CreateCategoryRequest;
import northwind.com.Business.Request.Category.DeleteCategoryRequest;
import northwind.com.Business.Request.Category.UpdateCategoryRequest;
import northwind.com.Business.Response.GetAllCategoryResponse;
import northwind.com.Business.Response.GetCategoryResponse;
import northwind.com.Core.OperationStatus;
import northwind.com.Core.Results.*;
import northwind.com.Core.exceptions.BusinessException;
import northwind.com.Core.mapping.ModelMapperService;
import northwind.com.DataAccess.CategoryRepository;
import northwind.com.Entities.Concrete.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryManager implements CategoryService {
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapperService modelmapperService;
    public CategoryManager(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public DataResult<List<GetAllCategoryResponse>> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();

        List<GetAllCategoryResponse> responses = categoryList.stream()
                .map(category -> this.modelmapperService.forResponce().map(category, GetAllCategoryResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(responses, "All categories" + OperationStatus.LISTEDALL.getDescription());
    }

    @Override
    public DataResult<Category> getById(GetCategoryResponse categoryResponse) {

        if(Objects.nonNull(categoryResponse))
        {
            Category categoryToFind = this.modelmapperService.forResponce().map(categoryResponse, Category.class);
            Category category = categoryRepository.findById(categoryToFind.getCategoryId()).get();
            return new SuccessDataResult<>(category, "Category "+OperationStatus.LISTED.getDescription());
        }
        else
            return new ErrorDataResult<>(OperationStatus.NOTLISTED.getDescription()+" Category");
    }

    @Override
    public Result addCategory(CreateCategoryRequest request) {
        checkIfCategoryExists(request);
        Category categoryToAdd = this.modelmapperService.forRequest().map(request, Category.class);
        categoryRepository.save(categoryToAdd);
        return new SuccessDataResult<>("Category "+ OperationStatus.ADDED.getDescription());
    }

    @Override
    public Result updateCategory(UpdateCategoryRequest request) {
        Category category = checkIfCategoryExistsForUpdate(request);
        category.setCategoryName(request.getCategoryName());
        category.setDescription(request.getDescription());
        category.setPicture(request.getPicture());
        categoryRepository.save(category);
        return new SuccessDataResult<>("Category "+ OperationStatus.UPDATED.getDescription());
    }



    @Override
    public Result deleteCategory(DeleteCategoryRequest request) {
        Category category = checkIfCategoryExistsForDelete(request);
        categoryRepository.delete(category);
        return new SuccessResult("Category "+OperationStatus.DELETED.getDescription());
    }

    private Category checkIfCategoryExistsForDelete(DeleteCategoryRequest request) {
        Optional<Category> optionalCategory = categoryRepository.findById(request.getCategoryId());
        return optionalCategory.orElseThrow(() -> new BusinessException("Category " + OperationStatus.NOTFOUND.getDescription()));
    }

    private void checkIfCategoryExists(CreateCategoryRequest request) {
        Category category = categoryRepository.findByCategoryName(request.getCategoryName());
        if(Objects.nonNull(category))
            throw new BusinessException("Category" + OperationStatus.EXISTS.getDescription());
    }

    private Category checkIfCategoryExistsForUpdate(UpdateCategoryRequest request) {
        Optional<Category> optionalCategory = categoryRepository.findById(request.getCategoryId());
        return optionalCategory.orElseThrow(() -> new BusinessException("Category " + OperationStatus.NOTFOUND.getDescription()));
    }

}
