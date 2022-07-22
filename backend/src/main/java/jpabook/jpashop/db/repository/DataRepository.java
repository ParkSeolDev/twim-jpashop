package jpabook.jpashop.db.repository;

import jpabook.jpashop.db.entity.Data;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository extends JpaRepository<Data, Long> {
}
