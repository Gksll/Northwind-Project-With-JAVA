package northwind.com.DataAccess;

import northwind.com.Entities.Concrete.SimpleMailEntitiy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailRepository extends JpaRepository<SimpleMailEntitiy, Integer> {


}
