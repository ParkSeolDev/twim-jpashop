package jpabook.jpashop.api.service;

import jpabook.jpashop.db.dto.DataDTO;
import jpabook.jpashop.db.entity.Data;
import jpabook.jpashop.db.mapper.DataMapper;
import jpabook.jpashop.db.repository.DataRepository;
import jpabook.jpashop.db.repository.DataRepositorySupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class DataService {
    private final DataRepository dataRepository;
    private final DataRepositorySupport dataRepositorySupport;
    private final DataMapper dataMapper;

    public DataDTO createData(DataDTO dataDto){
        Data data = dataRepository.save(dataMapper.toEntity(dataDto));
        return dataMapper.toDto(data);
    }
    
    public List<DataDTO> getSplitData(){
        List<Data> list = dataRepositorySupport.findText();
        List<DataDTO> dtoList = new ArrayList<>();
        for ( Data data : list) {
            dtoList.add(dataMapper.toDto(data));
        }
        return dtoList;
    }

    public void setIsPrinted(){
        List<Data> list = dataRepositorySupport.findText();
        List<DataDTO> dtoList = new ArrayList<>();
        for ( Data data : list) {
            dtoList.add(dataMapper.toDto(data));
            data.setPrinted(true);
            dataRepositorySupport.updateIsPrinted(data);
        }
    }
}
