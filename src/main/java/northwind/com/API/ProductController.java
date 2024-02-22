package northwind.com.API;


import northwind.com.Business.Concretes.ProductManager;
import northwind.com.Business.Request.Product.CreateProductRequest;
import northwind.com.Business.Request.Product.DeleteProductRequest;
import northwind.com.Business.Request.Product.UpdateProductRequest;
import northwind.com.Business.Response.Product.GetAllProductResponse;
import northwind.com.Business.Response.Product.GetByQuantityPerUnitResponse;
import northwind.com.Core.Results.DataResult;
import northwind.com.Core.Results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductManager productManager;

    @GetMapping("/getall")
    public DataResult<List<GetAllProductResponse>> getAllProducts() {
        return productManager.getAll();
    }
    @GetMapping("/getbyquantityperunit")
    public DataResult<List<GetAllProductResponse>> getByQuantityPerUnit(GetByQuantityPerUnitResponse response) {
        return productManager.getByQuantityPerUnitResponse(response);
    }
    @PostMapping("/add")
    public Result createProduct(@RequestBody CreateProductRequest response) {
        return productManager.createProduct(response);
    }
    @PostMapping("/update")
    public Result updateProduct(@RequestBody UpdateProductRequest request) {
        return productManager.updateProduct(request);
    }
    @PostMapping("/delete")
    public Result deleteProduct(@RequestBody DeleteProductRequest request) {
        return productManager.deleteProduct(request);
    }
}
