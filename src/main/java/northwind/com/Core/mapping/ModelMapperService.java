package northwind.com.Core.mapping;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {
    ModelMapper forResponce();
    ModelMapper forRequest();
}
