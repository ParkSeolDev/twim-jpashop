package jpabook.jpashop.db.entity.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
@Getter
@Setter
public class Book extends Item {
    private  String isbn;
    private String author;

    public Book(){
        super();
    }
    public Book(String name, int price, int stockQuantity) {
        super(name, price, stockQuantity);
    }
}
