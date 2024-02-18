package northwind.com.Business.Concretes;

import northwind.com.Business.Abstracts.CategoryService;
import northwind.com.Business.Request.Category.CreateCategoryRequest;
import northwind.com.Business.Request.Category.DeleteCategoryRequest;
import northwind.com.Business.Request.Category.UpdateCategoryRequest;
import northwind.com.Business.Response.Category.GetAllCategoryResponse;
import northwind.com.Business.Response.Category.GetCategoryResponse;
import northwind.com.Core.OperationStatus;
import northwind.com.Core.Results.*;
import northwind.com.Core.exceptions.BusinessException;
import northwind.com.Core.mapping.ModelMapperService;
import northwind.com.DataAccess.CategoryRepository;
import northwind.com.Entities.Concrete.CategoryEntity;
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
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();

        List<GetAllCategoryResponse> responses = categoryEntityList.stream()
                .map(category -> this.modelmapperService.forResponce().map(category, GetAllCategoryResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(responses, "All categories" + OperationStatus.LISTEDALL.getDescription());
    }

    @Override
    public DataResult<CategoryEntity> getById(GetCategoryResponse categoryResponse) {

        if(Objects.nonNull(categoryResponse))
        {
            CategoryEntity categoryEntityToFind = this.modelmapperService.forResponce().map(categoryResponse, CategoryEntity.class);
            CategoryEntity categoryEntity = categoryRepository.findById(categoryEntityToFind.getCategoryId()).get();
            return new SuccessDataResult<>(categoryEntity, "Category "+OperationStatus.LISTED.getDescription());
        }
        else
            return new ErrorDataResult<>(OperationStatus.NOTLISTED.getDescription()+" Category");
    }

    @Override
    public Result addCategory(CreateCategoryRequest request) {
        checkIfCategoryExists(request);
        CategoryEntity categoryEntityToAdd = this.modelmapperService.forRequest().map(request, CategoryEntity.class);
        categoryRepository.save(categoryEntityToAdd);
        return new SuccessDataResult<>("Category "+ OperationStatus.ADDED.getDescription());
    }

    @Override
    public Result updateCategory(UpdateCategoryRequest request) {
        CategoryEntity categoryEntity = checkIfCategoryExistsForUpdate(request);
        categoryEntity.setCategoryName(request.getCategoryName());
        categoryEntity.setDescription(request.getDescription());
        categoryEntity.setPicture(request.getPicture());
        categoryRepository.save(categoryEntity);
        return new SuccessDataResult<>("Category "+ OperationStatus.UPDATED.getDescription());
    }



    @Override
    public Result deleteCategory(DeleteCategoryRequest request) {
        CategoryEntity categoryEntity = checkIfCategoryExistsForDelete(request);
        categoryRepository.delete(categoryEntity);
        return new SuccessResult("Category "+OperationStatus.DELETED.getDescription());
    }

    private CategoryEntity checkIfCategoryExistsForDelete(DeleteCategoryRequest request) {
        Optional<CategoryEntity> optionalCategory = categoryRepository.findById(request.getCategoryId());
        return optionalCategory.orElseThrow(() -> new BusinessException("Category " + OperationStatus.NOTFOUND.getDescription()));
    }

    private void checkIfCategoryExists(CreateCategoryRequest request) {
        CategoryEntity categoryEntity = categoryRepository.findByCategoryName(request.getCategoryName());
        if(Objects.nonNull(categoryEntity))
            throw new BusinessException("Category" + OperationStatus.EXISTS.getDescription());
    }

    private CategoryEntity checkIfCategoryExistsForUpdate(UpdateCategoryRequest request) {
        Optional<CategoryEntity> optionalCategory = categoryRepository.findById(request.getCategoryId());
        return optionalCategory.orElseThrow(() -> new BusinessException("Category " + OperationStatus.NOTFOUND.getDescription()));
    }

}
