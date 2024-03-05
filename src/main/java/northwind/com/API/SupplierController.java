package northwind.com.API;

import northwind.com.Business.Concretes.SupplierManager;
import northwind.com.Business.Response.Supplier.GetAllSupplierResponse;
import northwind.com.Core.Results.DataResult;
import northwind.com.DataAccess.SupplierRepository;
import northwind.com.Entities.Concrete.SupplierEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierManager supplierManager;

    @GetMapping("/getall")
    private DataResult<List<GetAllSupplierResponse>> getAllSuppliers(){
        return this.supplierManager.getAllSuppliers();

    }
}
