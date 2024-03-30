package northwind.com.API;

import northwind.com.Business.Abstracts.CategoryService;
import northwind.com.Business.Abstracts.UserService;
import northwind.com.Business.Concretes.CategoryManager;
import northwind.com.Business.Request.Category.CreateCategoryRequest;
import northwind.com.Business.Request.Category.DeleteCategoryRequest;
import northwind.com.Business.Request.Category.UpdateCategoryRequest;
import northwind.com.Business.Request.User.UserRequest;
import northwind.com.Business.Response.Category.GetAllCategoryResponse;
import northwind.com.Business.Response.Category.GetCategoryResponse;
import northwind.com.Core.Results.DataResult;
import northwind.com.Core.Results.Result;
import northwind.com.Core.Results.SuccessDataResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/login")
    public Result Login(@RequestBody UserRequest request)
    {
        return userService.getByUserName(request);
    }

}
