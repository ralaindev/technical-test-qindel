package com.qindel.test.infrastructure.output.database;

import com.qindel.test.application.port.out.PriceRepositoryPort;
import com.qindel.test.domain.model.Price;
import com.qindel.test.infrastructure.output.database.mapper.PriceDatabaseMapper;
import com.qindel.test.infrastructure.output.database.repository.SpringDataPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PriceDatabaseAdapter implements PriceRepositoryPort {

    private final SpringDataPriceRepository repository;
    private final PriceDatabaseMapper priceDatabaseMapper;

    @Override
    public Optional<Price> findApplicablePrice(
            LocalDateTime queryDate,
            Long productId,
            Long brandId) {

        return repository
                .findApplicablePrices(brandId, productId, queryDate)
                .stream()
                .findFirst()
                .map(priceDatabaseMapper::toDomain);
    }
}