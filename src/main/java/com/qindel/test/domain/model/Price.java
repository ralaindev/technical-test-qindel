package com.qindel.test.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Las fechas representan la hora local de la cadena y no incluyen zona horaria.
 */
public record Price(
        Long brandId,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Long priceList,
        Long productId,
        Integer priority,
        BigDecimal price,
        String currency
) {
}
