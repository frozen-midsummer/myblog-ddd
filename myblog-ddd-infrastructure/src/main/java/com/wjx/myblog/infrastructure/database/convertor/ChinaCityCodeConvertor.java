package com.wjx.myblog.infrastructure.database.convertor;

import com.wjx.myblog.weather.dto.ChinaCityCodeDTO;
import com.wjx.myblog.infrastructure.database.dataobject.ChinaCityCodeDO;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface ChinaCityCodeConvertor {
    ChinaCityCodeDTO toDataTransferObj(ChinaCityCodeDO chinaCityCodeDO);

    List<ChinaCityCodeDTO> toDataTransferObjList(List<ChinaCityCodeDO> chinaCityCodeDOList);

    ChinaCityCodeDO toDataObject(ChinaCityCodeDTO chinaCityCodeDTO);
}
