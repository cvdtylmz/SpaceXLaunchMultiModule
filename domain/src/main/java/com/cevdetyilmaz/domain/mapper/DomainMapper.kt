package com.cevdetyilmaz.domain.mapper

interface DomainMapper<DataType, DomainType> {
    fun mapToDomainModel(dataModel: DataType): DomainType
}