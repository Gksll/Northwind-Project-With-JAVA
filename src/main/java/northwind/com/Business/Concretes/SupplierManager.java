package northwind.com.Business.Concretes;

import northwind.com.Business.Abstracts.SupplierService;
import northwind.com.Business.Response.Supplier.GetAllSupplierResponse;
import northwind.com.Core.OperationStatus;
import northwind.com.Core.Results.DataResult;
import northwind.com.Core.Results.SuccessDataResult;
import northwind.com.Core.mapping.ModelMapperService;
import northwind.com.DataAccess.SupplierRepository;
import northwind.com.Entities.Concrete.SupplierEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierManager implements SupplierService {

    @Autowired
    private ModelMapperService modelmapperService;
    @Autowired
    private SupplierRepository supplierRepository;


    @Override
    public DataResult<List<GetAllSupplierResponse>> getAllSuppliers() {
        List<SupplierEntity> entityList = this.supplierRepository.findAll();

        List<GetAllSupplierResponse> getAllSupplierResponseList = entityList.stream()
                .map(supplier -> this.modelmapperService.forResponce().map(supplier, GetAllSupplierResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(getAllSupplierResponseList, " All supliers " + OperationStatus.LISTEDALL.getDescription());
    }

//    @Override
//    public DataResult<List<GetAllCategoryResponse>> getAllCategories() {
//        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
//
//        List<GetAllCategoryResponse> responses = categoryEntityList.stream()
//                .map(category -> this.modelmapperService.forResponce().map(category, GetAllCategoryResponse.class))
//                .collect(Collectors.toList());
//
//        return new SuccessDataResult<>(responses, "All categories" + OperationStatus.LISTEDALL.getDescription());
//    }
//
//    @Override
//    public DataResult<GetCategoryResponse> getById(GetCategoryResponse categoryResponse) {
//
//        if(Objects.nonNull(categoryResponse))
//        {
//
//            CategoryEntity categoryEntity = categoryRepository.findById(categoryResponse.getCategoryId()).get();
//            GetCategoryResponse categoryEntityToFind = this.modelmapperService.forResponce().map(categoryEntity, GetCategoryResponse.class);
//            return new SuccessDataResult<>(categoryEntityToFind, "Category "+OperationStatus.LISTED.getDescription());
//        }
//        else
//            return new ErrorDataResult<>(OperationStatus.NOTLISTED.getDescription()+" Category");
//    }
//
//    @Override
//    public Result addCategory(CreateCategoryRequest request) {
//        checkIfCategoryExists(request);
//        CategoryEntity categoryEntityToAdd = this.modelmapperService.forRequest().map(request, CategoryEntity.class);
//        categoryRepository.save(categoryEntityToAdd);
//        return new SuccessResult("Category "+ OperationStatus.ADDED.getDescription());
//    }
//
//    @Override
//    public Result updateCategory(UpdateCategoryRequest request) {
//        checkIfCategoryExistsForUpdate(request);
//        CategoryEntity categoryEntity = this.modelmapperService.forRequest().map(request, CategoryEntity.class);
//        categoryRepository.save(categoryEntity);
//
//        return new SuccessDataResult<>("Category "+ OperationStatus.UPDATED.getDescription());
//    }
//
//
//
//    @Override
//    public Result deleteCategory(DeleteCategoryRequest request) {
//        CategoryEntity categoryEntity = checkIfCategoryExistsForDelete(request);
//        categoryRepository.delete(categoryEntity);
//        return new SuccessResult("Category "+OperationStatus.DELETED.getDescription());
//    }
//
//    private CategoryEntity checkIfCategoryExistsForDelete(DeleteCategoryRequest request) {
//        Optional<CategoryEntity> optionalCategory = categoryRepository.findById(request.getCategoryId());
//        return optionalCategory.orElseThrow(() -> new BusinessException("Category " + OperationStatus.NOTFOUND.getDescription()));
//    }
//
//    private void checkIfCategoryExists(CreateCategoryRequest request) {
//        CategoryEntity categoryEntity = categoryRepository.findByCategoryName(request.getCategoryName());
//        if(Objects.nonNull(categoryEntity))
//            throw new BusinessException("Category" + OperationStatus.EXISTS.getDescription());
//    }
//
//    private CategoryEntity checkIfCategoryExistsForUpdate(UpdateCategoryRequest request) {
//        Optional<CategoryEntity> optionalCategory = categoryRepository.findById(request.getCategoryId());
//        return optionalCategory.orElseThrow(() -> new BusinessException("Category " + OperationStatus.NOTFOUND.getDescription()));
//    }

}
