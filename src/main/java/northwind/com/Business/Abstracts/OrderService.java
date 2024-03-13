package northwind.com.Business.Abstracts;

import northwind.com.Business.Request.Order.CreateOrderRequest;
import northwind.com.Business.Request.Order.DeleteOrderRequest;
import northwind.com.Business.Request.Order.UpdateOrderRequest;
import northwind.com.Business.Response.Order.GetAllOrderResponse;
import northwind.com.Business.Response.Order.GetOrderResponse;
import northwind.com.Core.Results.DataResult;
import northwind.com.Core.Results.Result;

import java.util.List;

public interface OrderService {
    DataResult<List<GetAllOrderResponse>> getAllCategories();

    DataResult<GetOrderResponse> getById(GetOrderResponse orderResponse);

    Result addOrder(CreateOrderRequest request);

    Result updateOrder(UpdateOrderRequest request);

    Result deleteOrder(DeleteOrderRequest request);
}
