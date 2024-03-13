package northwind.com.DataAccess;

import northwind.com.Entities.Concrete.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    List<OrderEntity> findByCustomerIdAndEmployeeId(String customerId, int employeeId);

}
