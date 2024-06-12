package de.ait_tr.g_40_shop.service.mapping;

import de.ait_tr.g_40_shop.domain.dto.ProductDto;
import de.ait_tr.g_40_shop.domain.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.StartDocument;

@Service
@Mapper
public interface ProductMappingService {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", constant = "true")
    Product mapDtoToEntity(ProductDto dto);

    ProductDto mapEntityToDto(Product entity);


    //Class has been changed into an interface
    //Mapstract in pom.xml generates the methods by himself
//    public Product mapDtoToEntity(ProductDto dto){
//        Product entity = new Product();
//        entity.setTitle(dto.getTitle());
//        entity.setPrice(dto.getPrice());
//        entity.setActive(true);
//        return entity;
//    }
//
//    public ProductDto mapEntityToDto(Product entity){
//        ProductDto dto = new ProductDto();
//        dto.setId(entity.getId());
//        dto.setTitle(entity.getTitle());
//        dto.setPrice(entity.getPrice());
//        return dto;
//    }
}
