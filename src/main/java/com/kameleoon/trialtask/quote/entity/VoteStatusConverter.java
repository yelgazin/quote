package com.kameleoon.trialtask.quote.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class VoteStatusConverter implements AttributeConverter<VoteStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(VoteStatus status) {
        return status.getCode();
    }

    @Override
    public VoteStatus convertToEntityAttribute(Integer integer) {
        return VoteStatus.fromCode(integer);
    }
}