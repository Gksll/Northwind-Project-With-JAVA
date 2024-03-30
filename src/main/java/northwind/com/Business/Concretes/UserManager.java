package northwind.com.Business.Concretes;

import northwind.com.Business.Abstracts.UserService;
import northwind.com.Business.Request.User.UserRequest;
import northwind.com.Core.Results.ErrorResult;
import northwind.com.Core.Results.Result;
import northwind.com.Core.Results.SuccessResult;
import northwind.com.DataAccess.UserRepository;
import northwind.com.Entities.Concrete.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserManager implements UserService {
    @Autowired
    private UserRepository repository;

    @Override
    public Result getByUserName(UserRequest userRequest) {
        UserEntity user = this.repository.findByUsername(userRequest.getUserName().toLowerCase());
        if (Objects.isNull(user))
            return new ErrorResult("Böyle bir kullanıcı yok!");


        if (user.getPassword().equals(userRequest.getPassword())) {
            return new SuccessResult("Başarıyla giriş yaptınız!");
        } else
            return new SuccessResult("Kullanıcı adı yada şifre hatalı!");

    }
}