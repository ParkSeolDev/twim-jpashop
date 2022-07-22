package jpabook.jpashop.db.entity;

import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Data {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "data_id")
    private Long id;
    private String splitData;

    @CreatedDate
    private Date createdDate;

    @Column(nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean isPrinted;
}

