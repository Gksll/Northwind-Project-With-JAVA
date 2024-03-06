package northwind.com.API;

import northwind.com.Business.Concretes.SupplierManager;
import northwind.com.Business.Request.Category.CreateCategoryRequest;
import northwind.com.Business.Request.Category.DeleteCategoryRequest;
import northwind.com.Business.Request.Category.UpdateCategoryRequest;
import northwind.com.Business.Request.Supplier.CreateSupplierRequest;
import northwind.com.Business.Request.Supplier.DeleteSupplierRequest;
import northwind.com.Business.Request.Supplier.UpdateSupplierRequest;
import northwind.com.Business.Response.Supplier.GetAllSupplierResponse;
import northwind.com.Business.Response.Supplier.GetSupplierResponse;
import northwind.com.Core.Results.DataResult;
import northwind.com.Core.Results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierManager supplierManager;

    @GetMapping("/getall")
    private DataResult<List<GetAllSupplierResponse>> getAllSuppliers() {
        return this.supplierManager.getAllSuppliers();
    }

    @GetMapping("/getbyid")
    private DataResult<GetSupplierResponse> getByIdSupplier(GetSupplierResponse response) {
        return this.supplierManager.getById(response);
    }
    @PostMapping("/add")
    public Result Add(@RequestBody CreateSupplierRequest request)
    {
        return this.supplierManager.addSupplier(request);
    }
    @PostMapping("/update")
    public Result Update(@RequestBody UpdateSupplierRequest request)
    {
        return this.supplierManager.updateSupplier(request);
    }
    @DeleteMapping("/delete")
    public Result Delete(@RequestBody DeleteSupplierRequest request)
    {
        return this.supplierManager.deleteSupplier(request);
    }
}
