package northwind.com.API;


import northwind.com.Business.Concretes.ProductManager;
import northwind.com.Entities.Concrete.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController
{
    @Autowired
    ProductManager productManager;
    
    @GetMapping("/getAll")
public List<Product> getAllProducts()
{
    return  productManager.getAll();
}
}
