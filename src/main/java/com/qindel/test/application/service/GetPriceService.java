package com.qindel.test.application.service;

import com.qindel.test.application.exception.PriceNotFoundException;
import com.qindel.test.application.port.in.GetPriceUseCase;
import com.qindel.test.application.port.out.PriceRepositoryPort;
import com.qindel.test.domain.model.Price;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class GetPriceService implements GetPriceUseCase {

    private final PriceRepositoryPort priceRepositoryPort;

    @Override
    public Price getPrice(OffsetDateTime queryDate, Long productId, Long brandId) {
        return priceRepositoryPort
                .findApplicablePrice(queryDate.toLocalDateTime(), productId, brandId)
                .orElseThrow(PriceNotFoundException::new);
    }
}