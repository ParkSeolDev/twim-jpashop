package jpabook.jpashop.db.repository;

import jpabook.jpashop.db.entity.Data;
import jpabook.jpashop.db.entity.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DataRepositorySupport {
    private final EntityManager em;

    public List<Data> findText(){
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss.S");
//        String formattedString = date.format(formatter);
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
//        String time = format.format(date);
//        String time = date.toString().substring(0,21);

        return em.createQuery("select d from Data d where d.isPrinted = 0", Data.class)
                .getResultList();
    }

    @Transactional
    @Modifying
    public void updateIsPrinted(Data data){
//        em.createQuery("update Data d set d.isPrinted = 1").executeUpdate();
        em.merge(data);
    }
}
