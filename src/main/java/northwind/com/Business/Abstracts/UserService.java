package northwind.com.Business.Abstracts;

import northwind.com.Business.Request.User.UserRequest;
import northwind.com.Core.Results.Result;

public interface UserService {
    Result getByUserName(UserRequest UserRequest);
}
