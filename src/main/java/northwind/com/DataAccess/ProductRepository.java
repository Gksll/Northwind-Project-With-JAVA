package northwind.com.DataAccess;


import northwind.com.Entities.Concrete.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
 public interface ProductRepository extends JpaRepository<ProductEntity,Integer> {

@Query("SELECT p FROM ProductEntity p where p.quantityPerUnit ilike CONCAT('%', :unit,'%')")
 List<ProductEntity> getByQuantityPerUnit(@Param("unit") String unit);


}
