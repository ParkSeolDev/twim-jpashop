package jpabook.jpashop.db.mapper;

import jpabook.jpashop.db.dto.DataDTO;
import jpabook.jpashop.db.dto.FileDTO;
import jpabook.jpashop.db.entity.Data;
import jpabook.jpashop.db.entity.File;
import org.springframework.stereotype.Component;

@Component
public class DataMapper {
    public DataDTO toDto(Data data) {
        return DataDTO.builder()
                .id(data.getId())
                .splitData(data.getSplitData())
                .createdDate(data.getCreatedDate())
                .isPrinted(data.isPrinted())
                .build();
    }

    public Data toEntity(DataDTO dataDto) {
        return Data.builder()
                .id(dataDto.getId())
                .splitData(dataDto.getSplitData())
                .createdDate(dataDto.getCreatedDate())
                .isPrinted(dataDto.isPrinted())
                .build();
    }
}
