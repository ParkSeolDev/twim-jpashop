package jpabook.jpashop.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jpabook.jpashop.db.entity.item.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
    
}