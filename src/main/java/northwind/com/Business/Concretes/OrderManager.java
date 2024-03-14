package northwind.com.Business.Concretes;

import northwind.com.Business.Abstracts.CategoryService;
import northwind.com.Business.Abstracts.OrderService;
import northwind.com.Business.Request.Category.CreateCategoryRequest;
import northwind.com.Business.Request.Category.DeleteCategoryRequest;
import northwind.com.Business.Request.Category.UpdateCategoryRequest;
import northwind.com.Business.Request.Order.CreateOrderRequest;
import northwind.com.Business.Request.Order.DeleteOrderRequest;
import northwind.com.Business.Request.Order.UpdateOrderRequest;
import northwind.com.Business.Response.Category.GetAllCategoryResponse;
import northwind.com.Business.Response.Category.GetCategoryResponse;
import northwind.com.Business.Response.Order.GetAllOrderResponse;
import northwind.com.Business.Response.Order.GetOrderResponse;
import northwind.com.Core.OperationStatus;
import northwind.com.Core.Results.*;
import northwind.com.Core.exceptions.BusinessException;
import northwind.com.Core.mapping.ModelMapperService;
import northwind.com.DataAccess.CategoryRepository;
import northwind.com.DataAccess.OrderRepository;
import northwind.com.Entities.Concrete.CategoryEntity;
import northwind.com.Entities.Concrete.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderManager implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ModelMapperService modelmapperService;

    @Override
    public DataResult<List<GetAllOrderResponse>> getAllCategories() {
        List<OrderEntity> orderEntityList = orderRepository.findAll();

        List<GetAllOrderResponse> responses = orderEntityList.stream()
                .map(order -> this.modelmapperService.forResponce().map(order, GetAllOrderResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(responses, "All ORDERS " + OperationStatus.LISTEDALL.getDescription());
    }

    @Override
    public DataResult<GetOrderResponse> getById(GetOrderResponse orderResponse) {
        if(Objects.nonNull(orderResponse))
        {
            OrderEntity orderEntity = orderRepository.findById(orderResponse.getOrderId()).get();
            GetOrderResponse orderEntityToFind = this.modelmapperService.forResponce().map(orderEntity, GetOrderResponse.class);
            return new SuccessDataResult<>(orderEntityToFind, "Order "+OperationStatus.LISTED.getDescription());
        }
        else
            return new ErrorDataResult<>(OperationStatus.NOTLISTED.getDescription()+" Order");
    }

    @Override
    public Result addOrder(CreateOrderRequest request) {
        checkIfOrderExists(request);
        OrderEntity orderEntityToAdd = this.modelmapperService.forRequest().map(request, OrderEntity.class);
        orderRepository.save(orderEntityToAdd);
        return new SuccessResult("Order "+ OperationStatus.ADDED.getDescription());
    }

    @Override
    public Result updateOrder(UpdateOrderRequest request) {
        checkIfOrderExistsForUpdate(request);
        OrderEntity orderEntity= this.modelmapperService.forRequest().map(request, OrderEntity.class);
        orderRepository.save(orderEntity);

        return new SuccessDataResult<>("ORDER "+ OperationStatus.UPDATED.getDescription());
    }

    @Override
    public Result deleteOrder(DeleteOrderRequest request) {
        OrderEntity orderEntity = checkIfOrderExistsForDelete(request);
        orderRepository.delete(orderEntity);
        return new SuccessResult("Order "+OperationStatus.DELETED.getDescription());
    }
    private OrderEntity checkIfOrderExistsForDelete(DeleteOrderRequest request) {
        Optional<OrderEntity> optionalOrder = orderRepository.findById(request.getOrderId());
        return optionalOrder.orElseThrow(() -> new BusinessException("ORDER " + OperationStatus.NOTFOUND.getDescription()));
    }

    private void checkIfOrderExists(CreateOrderRequest request) {
        List<OrderEntity> entities = orderRepository.findByCustomerIdAndEmployeeId(request.getCustomerId(), request.getEmployeeId());

        if(entities.size()>0)
            throw new BusinessException("ORDER " + OperationStatus.EXISTS.getDescription());
    }

    private OrderEntity checkIfOrderExistsForUpdate(UpdateOrderRequest request) {
        Optional<OrderEntity> orderEntity = orderRepository.findById(request.getOrderId());
        return orderEntity.orElseThrow(() -> new BusinessException("Order " + OperationStatus.NOTFOUND.getDescription()));
    }

}
