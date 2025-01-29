package com.develop.mapper;

import com.develop.dto.response.UserWalletResp;
import com.develop.dto.response.WalletResp;
import com.develop.entity.User;
import com.develop.entity.Wallet;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy =  ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface IWalletMapper {
    IWalletMapper INSTANCE = Mappers.getMapper(IWalletMapper.class);

    UserWalletResp toRes(User source);

    WalletResp toResp(Wallet source);
}
