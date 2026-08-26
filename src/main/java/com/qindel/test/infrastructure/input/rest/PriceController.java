package com.qindel.test.infrastructure.input.rest;

import com.qindel.test.application.port.in.GetPriceUseCase;
import com.qindel.test.domain.model.Price;
import com.qindel.test.infrastructure.input.rest.api.PricesApi;
import com.qindel.test.infrastructure.input.rest.mapper.PriceRestMapper;
import com.qindel.test.infrastructure.input.rest.model.PriceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.time.OffsetDateTime;

@Controller
public class PriceController implements PricesApi {

    private final GetPriceUseCase getPriceUseCase;
    private final PriceRestMapper priceRestMapper;

    public PriceController(GetPriceUseCase getPriceUseCase, PriceRestMapper priceRestMapper) {
        this.getPriceUseCase = getPriceUseCase;
        this.priceRestMapper = priceRestMapper;
    }

    @Override
    public ResponseEntity<PriceResponse> getPrice(OffsetDateTime queryDate, Long productId, Long brandId) {
        Price price = getPriceUseCase.getPrice(queryDate, productId, brandId);

        return ResponseEntity.ok(
                priceRestMapper.toResponse(price, queryDate)
        );
    }
}
