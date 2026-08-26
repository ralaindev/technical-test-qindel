package com.qindel.test.infrastructure.output.database.mapper;

import com.qindel.test.domain.model.Price;
import com.qindel.test.infrastructure.output.database.entity.PriceJpaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceDatabaseMapper {

    Price toDomain(PriceJpaEntity entity);
}