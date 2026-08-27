package com.qindel.test.infrastructure.input.rest.mapper;

import com.qindel.test.domain.model.Price;
import com.qindel.test.infrastructure.input.rest.model.PriceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.OffsetDateTime;


@Mapper(componentModel = "spring")
public interface PriceRestMapper {

    // La base de datos almacena fechas locales sin zona horaria. Se reutiliza el
    // offset de la petición para exponerlas como OffsetDateTime sin aplicar la
    // zona horaria predeterminada del servidor.

    @Mapping(target = "startDate", expression = "java(price.startDate().atOffset(queryDate.getOffset()))")
    @Mapping(target = "endDate", expression = "java(price.endDate().atOffset(queryDate.getOffset()))")
    PriceResponse toResponse(Price price, OffsetDateTime queryDate);
}
