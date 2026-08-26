package com.qindel.test.application.port.in;

import com.qindel.test.domain.model.Price;

import java.time.OffsetDateTime;

public interface GetPriceUseCase {

    Price getPrice(
            OffsetDateTime queryDate,
            Long productId,
            Long brandId
    );
}
