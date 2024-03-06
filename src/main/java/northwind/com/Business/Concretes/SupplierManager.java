package northwind.com.Business.Concretes;

import northwind.com.Business.Abstracts.SupplierService;
import northwind.com.Business.Request.Supplier.CreateSupplierRequest;
import northwind.com.Business.Request.Supplier.DeleteSupplierRequest;
import northwind.com.Business.Request.Supplier.UpdateSupplierRequest;
import northwind.com.Business.Response.Supplier.GetAllSupplierResponse;
import northwind.com.Business.Response.Supplier.GetSupplierResponse;
import northwind.com.Core.OperationStatus;
import northwind.com.Core.Results.*;
import northwind.com.Core.exceptions.BusinessException;
import northwind.com.Core.mapping.ModelMapperService;
import northwind.com.DataAccess.SupplierRepository;
import northwind.com.Entities.Concrete.SupplierEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SupplierManager implements SupplierService {

    @Autowired
    private ModelMapperService modelmapperService;
    @Autowired
    private SupplierRepository supplierRepository;
    @Override
    public DataResult<List<GetAllSupplierResponse>> getAllSuppliers() {
        List<SupplierEntity> entityList = this.supplierRepository.findAll();

        List<GetAllSupplierResponse> getAllSupplierResponseList = entityList.stream()
                .map(supplier -> this.modelmapperService.forResponce().map(supplier, GetAllSupplierResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(getAllSupplierResponseList, " All supliers " + OperationStatus.LISTEDALL.getDescription());
    }

    @Override
    public DataResult<GetSupplierResponse> getById(GetSupplierResponse response) {
        if(Objects.nonNull(response))
        {
            SupplierEntity supplierEntity = this.supplierRepository.findById(response.getSupplierId()).get();
            GetSupplierResponse supplierToFind = this.modelmapperService.forResponce().map(supplierEntity, GetSupplierResponse.class);
            return new SuccessDataResult<>(supplierToFind, "Supplier "+OperationStatus.LISTED.getDescription());
        }
        else
            return new ErrorDataResult<>(OperationStatus.NOTLISTED.getDescription()+" Supplier");
    }

    @Override
    public Result addSupplier(CreateSupplierRequest request) {
        checkIfSupplierExists(request);
        SupplierEntity supplierEntityToAdd = this.modelmapperService.forRequest().map(request, SupplierEntity.class);
        this.supplierRepository.save(supplierEntityToAdd);
        return new SuccessResult("Supplier  "+ OperationStatus.ADDED.getDescription());
    }

    @Override
    public Result updateSupplier(UpdateSupplierRequest request) {
        checkIfSupplierExistsForUpdate(request);
        SupplierEntity supplierEntity = this.modelmapperService.forRequest().map(request, SupplierEntity.class);
        this.supplierRepository.save(supplierEntity);
        return new SuccessResult("Supplier "+ OperationStatus.UPDATED.getDescription());
    }




    @Override
    public Result deleteSupplier(DeleteSupplierRequest request) {
        SupplierEntity supplierEntity = checkIfSupplierExistsForDelete(request);
        this.supplierRepository.delete(supplierEntity);
        return new SuccessResult("Supplier  "+OperationStatus.DELETED.getDescription());
    }

    private SupplierEntity checkIfSupplierExistsForDelete(DeleteSupplierRequest request) {
        Optional<SupplierEntity> optionalSupplier = this.supplierRepository.findById(request.getSupplierId());
        return optionalSupplier.orElseThrow(() -> new BusinessException("Supplier " + OperationStatus.NOTFOUND.getDescription()));
    }
    private void checkIfSupplierExists(CreateSupplierRequest request) {
        SupplierEntity byCompanyName = supplierRepository.findByCompanyName(request.getCompanyName());
        if(Objects.nonNull(byCompanyName))
            throw new BusinessException("Supplier " + OperationStatus.EXISTS.getDescription());
    }

    private void checkIfSupplierExistsForUpdate(UpdateSupplierRequest request) {
        Optional<SupplierEntity> optionalSupplier = this.supplierRepository.findById(request.getSupplierId());
        optionalSupplier.orElseThrow(() -> new BusinessException("Supplier " + OperationStatus.NOTFOUND.getDescription()));
    }

}
