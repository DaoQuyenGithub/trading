package com.develop.mapper;

import com.develop.dto.response.CryptoTransactionResp;
import com.develop.entity.CryptoTransaction;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy =  ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ICryptorTransactionMapper {
    ICryptorTransactionMapper INSTANCE = Mappers.getMapper(ICryptorTransactionMapper.class);

    CryptoTransactionResp toResp(CryptoTransaction source);

    List<CryptoTransactionResp> toResp(List<CryptoTransaction> source);
}
