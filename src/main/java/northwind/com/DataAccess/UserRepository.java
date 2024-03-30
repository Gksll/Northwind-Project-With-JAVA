package northwind.com.DataAccess;

import northwind.com.Entities.Concrete.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query("SELECT u FROM UserEntity u WHERE u.userName = :userName")
    UserEntity findByUsername(@Param("userName") String userName);


}
