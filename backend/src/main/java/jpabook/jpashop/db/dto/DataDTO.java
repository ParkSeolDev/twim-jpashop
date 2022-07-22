package jpabook.jpashop.db.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataDTO {
    private long id;
    private String splitData;
    private Date createdDate;
    private boolean isPrinted;

}
