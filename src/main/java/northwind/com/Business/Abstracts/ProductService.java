package northwind.com.Business.Abstracts;

import northwind.com.Business.Request.Product.CreateProductRequest;
import northwind.com.Business.Request.Product.DeleteProductRequest;
import northwind.com.Business.Request.Product.UpdateProductRequest;
import northwind.com.Business.Response.Product.GetAllProductResponse;
import northwind.com.Business.Response.Product.GetByQuantityPerUnitResponse;
import northwind.com.Core.Results.DataResult;
import northwind.com.Core.Results.Result;

import java.util.List;

public interface ProductService {
    DataResult<List<GetAllProductResponse>> getAll();
    DataResult<List<GetAllProductResponse>> getByQuantityPerUnitResponse(GetByQuantityPerUnitResponse unit);
    Result createProduct(CreateProductRequest request);

    Result updateProduct(UpdateProductRequest request);

    Result deleteProduct(DeleteProductRequest request);
}
