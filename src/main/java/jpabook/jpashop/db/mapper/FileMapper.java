package jpabook.jpashop.db.mapper;

import org.springframework.stereotype.Component;

import jpabook.jpashop.db.dto.FileDto;
import jpabook.jpashop.db.entity.File;

@Component
public class FileMapper {
    public FileDto toDto(File file) {
        return FileDto.builder()
                .id(file.getId())
                .name(file.getName())
                .createdDate(file.getCreatedDate())
                .uploadedDate(file.getUploadedDate())
                .filePath(file.getFilePath())
                .build();
    }

    public File toEntity(FileDto fileDto) {
        return File.builder()
                .id(fileDto.getId())
                .name(fileDto.getName())
                .createdDate(fileDto.getCreatedDate())
                .uploadedDate(fileDto.getUploadedDate())
                .filePath(fileDto.getFilePath())
                .build();
    }
}
