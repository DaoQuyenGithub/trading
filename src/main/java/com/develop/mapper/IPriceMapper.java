package com.develop.mapper;

import com.develop.dto.PriceResp;
import com.develop.entity.Price;
import com.develop.dto.PriceReq;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy =  ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface IPriceMapper {
    IPriceMapper INSTANCE = Mappers.getMapper(IPriceMapper.class);

    PriceResp toEntity(Price source);

    Price toEntity(PriceReq source);

    List<PriceResp> toEntity(List<Price> source);
}
