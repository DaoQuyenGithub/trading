package com.develop.service.impl;

import com.develop.dto.response.BinanceResp;
import com.develop.dto.response.HoubiDataResp;
import com.develop.dto.response.PriceResp;
import com.develop.entity.Price;
import com.develop.mapper.IPriceMapper;
import com.develop.repository.IPriceRepository;
import com.develop.service.IPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PriceService implements IPriceService {
    private static final Set<String> SUPPORTED_SYMBOLS = Set.of("BTCUSDT", "ETHUSDT");

    private final IPriceRepository priceRepository;
    private final IPriceMapper mapper = IPriceMapper.INSTANCE;

    @Transactional
    @Override
    public List<Price> saveAll(List<Price> price) {
        List<Price> prices = priceRepository.saveAll(price);
        priceRepository.flush();
        return prices;
    }

    @Override
    public List<PriceResp> getLatestPriceForPair() {
        List<Price> price = priceRepository.findLatestPriceBySymbol();
        List<PriceResp> rs = mapper.toEntity(price);
        return rs;
    }

    @Override
    public List<Price> getBestPriceAggregation(List<HoubiDataResp> houbis, List<BinanceResp> binances) {
        List<Price> prices = new ArrayList<>();
        for (int i = 0; i < binances.size(); i++) {
            String symbol = binances.get(i).getSymbol();
            if (SUPPORTED_SYMBOLS.contains(symbol)) {
                BigDecimal binanceBid = binances.get(i).getBidPrice();
                BigDecimal binanceAsk = binances.get(i).getAskPrice();

                for (int j = 0; j < houbis.size(); j++) {
                    String huobiSymbol = houbis.get(j).getSymbol().toUpperCase();

                    if (symbol.equals(huobiSymbol)) {
                        BigDecimal huobiBid = houbis.get(j).getBid();
                        BigDecimal huobiAsk = houbis.get(j).getAsk();

                        BigDecimal bestBid = binanceBid.max(huobiBid);
                        BigDecimal bestAsk = binanceAsk.min(huobiAsk);


                        Price entity = new Price();
                        entity.setAskPrice(bestAsk);
                        entity.setBidPrice(bestBid);
                        entity.setSymbol(symbol);

                        prices.add(entity);
                    }
                }
            }
        }
        return prices;
    }

    @Override
    public Price findLatestBySymbol(String symbol) {
        return priceRepository.findLatestBySymbol(symbol)
                .orElseThrow(() -> new RuntimeException("No price available for symbol: " + symbol));
    }
}
