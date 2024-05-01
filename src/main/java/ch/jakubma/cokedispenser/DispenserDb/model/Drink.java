package ch.jakubma.cokedispenser.DispenserDb.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "drinks")
public class Drink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "price")
    private Integer price;

    @Column(name = "bottleCount")
    private Integer bottleCount;

    public Drink() {

    }
    public Drink(String name, Integer price, Integer bottleCount) {
        this.name = name;
        this.price = price;
        this.bottleCount = bottleCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getBottleCount() {
        return bottleCount;
    }

    public void setBottleCount(Integer bottleCount) {
        this.bottleCount = bottleCount;
    }

}
