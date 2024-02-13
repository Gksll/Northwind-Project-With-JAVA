package northwind.com.Business.Concretes;


import northwind.com.DataAccess.ProductRepository;
import northwind.com.Entities.Concrete.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductManager {
    @Autowired
    private ProductRepository productRepository;
    public List<Product> getAll()
    {
        return productRepository.findAll();

    }


}
