package com.qindel.test.infrastructure.output.database.repository;

import com.qindel.test.infrastructure.output.database.entity.PriceJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface SpringDataPriceRepository
        extends JpaRepository<PriceJpaEntity, Long> {

    @Query("""
            SELECT p
            FROM PriceJpaEntity p
            WHERE p.brandId = :brandId
              AND p.productId = :productId
              AND :queryDate BETWEEN p.startDate AND p.endDate
            ORDER BY p.priority DESC
            """)
    List<PriceJpaEntity> findApplicablePrices(
            Long brandId,
            Long productId,
            LocalDateTime queryDate
    );
}