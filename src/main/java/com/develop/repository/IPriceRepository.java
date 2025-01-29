package com.develop.repository;

import com.develop.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IPriceRepository extends JpaRepository<Price, Long> {
    @Query(value = """
                SELECT 
                    p.*
                FROM 
                    crypto_price p
                INNER JOIN (
                    SELECT 
                        symbol, 
                        MAX(updated_at) AS max_last_updated
                    FROM 
                        crypto_price
                    GROUP BY 
                        symbol
                ) latest ON p.symbol = latest.symbol AND p.updated_at = latest.max_last_updated;
            """, nativeQuery = true)
    List<Price> findLatestPriceBySymbol();


    @Query(value = "SELECT TOP 1 * FROM crypto_price WHERE symbol = :symbol ORDER BY updated_at DESC", nativeQuery = true)
    Optional<Price> findLatestBySymbol(String symbol);
}