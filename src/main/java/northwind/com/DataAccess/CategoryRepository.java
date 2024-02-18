package northwind.com.DataAccess;

import northwind.com.Entities.Concrete.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

    CategoryEntity findByCategoryName(String categoryName);

}
