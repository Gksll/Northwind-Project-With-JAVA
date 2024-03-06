package northwind.com.DataAccess;

import northwind.com.Entities.Concrete.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<SupplierEntity, Integer> {

    SupplierEntity findByCompanyName(String companyName);

}
