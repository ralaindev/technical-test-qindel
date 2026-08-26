package com.qindel.test.application.port.out;

import com.qindel.test.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepositoryPort {

    Optional<Price> findApplicablePrice(
            LocalDateTime queryDate,
            Long productId,
            Long brandId
    );
}