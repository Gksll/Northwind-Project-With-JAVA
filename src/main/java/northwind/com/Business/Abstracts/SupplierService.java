package northwind.com.Business.Abstracts;

import northwind.com.Business.Request.Category.CreateCategoryRequest;
import northwind.com.Business.Request.Category.DeleteCategoryRequest;
import northwind.com.Business.Request.Category.UpdateCategoryRequest;
import northwind.com.Business.Request.Supplier.CreateSupplierRequest;
import northwind.com.Business.Request.Supplier.DeleteSupplierRequest;
import northwind.com.Business.Request.Supplier.UpdateSupplierRequest;
import northwind.com.Business.Response.Category.GetAllCategoryResponse;
import northwind.com.Business.Response.Category.GetCategoryResponse;
import northwind.com.Business.Response.Supplier.GetAllSupplierResponse;
import northwind.com.Business.Response.Supplier.GetSupplierResponse;
import northwind.com.Core.Results.DataResult;
import northwind.com.Core.Results.Result;
import northwind.com.Entities.Concrete.CategoryEntity;

import java.util.List;

public interface SupplierService {
     DataResult<List<GetAllSupplierResponse>> getAllSuppliers();

    DataResult<GetSupplierResponse> getById(GetSupplierResponse response);

    Result addSupplier(CreateSupplierRequest request);

    Result updateSupplier(UpdateSupplierRequest request);

    Result deleteSupplier(DeleteSupplierRequest request);
}
