package northwind.com.Business.Concretes;


import northwind.com.Business.Abstracts.ProductService;
import northwind.com.Business.Request.Product.CreateProductRequest;
import northwind.com.Business.Response.Product.GetAllProductResponse;
import northwind.com.Business.Response.Product.GetByQuantityPerUnitResponse;
import northwind.com.Core.OperationStatus;
import northwind.com.Core.Results.*;
import northwind.com.Core.exceptions.BusinessException;
import northwind.com.Core.mapping.ModelMapperService;
import northwind.com.DataAccess.ProductRepository;
import northwind.com.Entities.Concrete.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductManager implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapperService modelMapperService;

    @Override
    public DataResult<List<GetAllProductResponse>> getAll() {
        List<ProductEntity> allProductEntities = productRepository.findAll();
        getAndCheckNull(allProductEntities);
        try {
            List<GetAllProductResponse> result = allProductEntities.stream().
                    map(productEntity -> this.modelMapperService.forResponce().map(productEntity,GetAllProductResponse.class))
                    .toList();
            return new SuccessDataResult<>(result,"PRODUCT "+ OperationStatus.LISTEDALL.getDescription());
        }
        catch (Exception e)
        {
            return new ErrorDataResult<>(e.getMessage());
        }
    }

    @Override
    public DataResult<List<GetAllProductResponse>> getByQuantityPerUnitResponse(GetByQuantityPerUnitResponse unit) {
        if(Objects.isNull(unit))
            throw new BusinessException("Unit values can not be EMPTY! ");

        List<ProductEntity> productList = this.productRepository.getByQuantityPerUnit(unit.getQuantityPerUnit());
        getAndCheckNull(productList);
        try {
            List<GetAllProductResponse> productListToReturn = productList.stream().map(productEntity -> this.modelMapperService.forResponce().map(productEntity, GetAllProductResponse.class))
                    .collect(Collectors.toList());
        return new SuccessDataResult<>(productListToReturn,"PRODUCTS ARE "+OperationStatus.LISTEDALL.getDescription());
        }
        catch (Exception e)
        {
            return new ErrorDataResult<>(e.getMessage());
        }
    }

    @Override
    public Result createProduct(CreateProductRequest request) {
        getAndCheckNull(request);
        try {
            ProductEntity productEntity = this.modelMapperService.forRequest().map(request, ProductEntity.class);
            this.productRepository.save(productEntity);
            return new SuccessResult("PRODUCT "+OperationStatus.ADDED.getDescription());
        }
        catch (Exception e)
        {
            return new ErrorResult(e.getMessage());
        }

    }


    public static <T> T getAndCheckNull(T value) {
        if (Objects.isNull(value)) {
            throw new BusinessException("There is NO PRODUCT!");
        }
        return value;
    }


}
