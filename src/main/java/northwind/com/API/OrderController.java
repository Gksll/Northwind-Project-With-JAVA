package northwind.com.API;

import northwind.com.Business.Abstracts.CategoryService;
import northwind.com.Business.Abstracts.OrderService;
import northwind.com.Business.Concretes.CategoryManager;
import northwind.com.Business.Request.Category.CreateCategoryRequest;
import northwind.com.Business.Request.Category.DeleteCategoryRequest;
import northwind.com.Business.Request.Category.UpdateCategoryRequest;
import northwind.com.Business.Request.Order.CreateOrderRequest;
import northwind.com.Business.Response.Category.GetAllCategoryResponse;
import northwind.com.Business.Response.Category.GetCategoryResponse;
import northwind.com.Business.Response.Order.GetAllOrderResponse;
import northwind.com.Business.Response.Order.GetOrderResponse;
import northwind.com.Core.Results.DataResult;
import northwind.com.Core.Results.Result;
import northwind.com.Core.Results.SuccessDataResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/getall")
    public DataResult<List<GetAllOrderResponse>> getAll() {
       return new SuccessDataResult<>(orderService.getAllCategories().getData(),orderService.getAllCategories().getMessage());
    }

    @GetMapping("/getbyid")
    public DataResult<GetOrderResponse> getById(GetOrderResponse response) {
        return new SuccessDataResult<>(orderService.getById(response).getData(), orderService.getById(response).getMessage());
    }
    @PostMapping("/add")
    public Result Add(@RequestBody CreateOrderRequest createOrderRequest)
    {
        return orderService.addOrder(createOrderRequest);
    }
//    @PostMapping("/update")
//    public Result Update(@RequestBody UpdateCategoryRequest updateCategoryRequest)
//    {
//        return categoryService.updateCategory(updateCategoryRequest);
//    }
//    @DeleteMapping("/delete")
//    public Result Delete(@RequestBody DeleteCategoryRequest deleteCategoryRequest)
//    {
//        return categoryService.deleteCategory(deleteCategoryRequest);
//    }
}
