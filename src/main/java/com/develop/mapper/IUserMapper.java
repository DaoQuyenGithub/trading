package com.develop.mapper;

import com.develop.dto.response.UserWalletResp;
import com.develop.entity.User;
import com.develop.entity.Wallet;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy =  ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface IUserMapper {
    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    UserWalletResp toRes(User source);

    @Mapping(source = "id", target = "walletId")
    UserWalletResp toUserWalletResp(Wallet source);
}
