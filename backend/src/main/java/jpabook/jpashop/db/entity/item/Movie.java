package jpabook.jpashop.db.entity.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
@Getter
@Setter
public class Movie extends Item {

    private String director;
    private String actor;

    public Movie(){

    }
    public Movie(String name, int price, int stockQuantity) {
        super(name, price, stockQuantity);
    }
}
