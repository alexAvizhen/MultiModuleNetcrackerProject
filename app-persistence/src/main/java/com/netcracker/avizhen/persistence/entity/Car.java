package com.netcracker.avizhen.persistence.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.netcracker.avizhen.persistence.web.jsonview.Views;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Александр on 19.10.2016.
 */
@Entity
@Table(name = "car")
public class Car {

    private static final long CURRENT_YEAR = 2016;
    private static final long MIN_YEAR = 1800;

    @Id
    @GenericGenerator(name="CUST_GEN" , strategy="increment")
    @GeneratedValue(generator="CUST_GEN")
    @Column(name = "id")
    @JsonView(Views.Public.class)
    private Integer id;

    @NotNull
    @Column(name = "model", nullable = false)
    @JsonView(Views.Public.class)
    private String model;

    @NotNull
    @Column(name = "make", nullable = false)
    @JsonView(Views.Public.class)
    private String make;

    @NotNull
    @Column(name = "price")
    @JsonView(Views.Public.class)
    private Integer price;

    @NotNull
    @Column(name = "year")
    @JsonView(Views.Public.class)
    @Min(MIN_YEAR)
    @Max(CURRENT_YEAR)
    private int year;

    @Column(name = "car_condition")
    @JsonView(Views.Public.class)
    private String condition;

    @Column(name = "description")
    @JsonView(Views.Public.class)
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car", cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH})
    Set<CarImage> images = new HashSet<CarImage>();

    @OneToOne(mappedBy = "car", cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH})
    private Advert advert;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car", cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    private Set<Item> items = new HashSet<Item>();

    public Car() {
    }

    public Car(String model, String make, Integer price, int year, String condition,
               String description, Set<CarImage> images, Advert advert, Set<Item> items) {
        this.model = model;
        this.make = make;
        this.price = price;
        this.year = year;
        this.condition = condition;
        this.description = description;
        this.images = images;
        this.advert = advert;
        this.items = items;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<CarImage> getImages() {
        return images;
    }

    public void setImages(Set<CarImage> images) {
        this.images = images;
    }

    public Advert getAdvert() {
        return advert;
    }

    public void setAdvert(Advert advert) {
        this.advert = advert;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", make='" + make + '\'' +
                ", price=" + price +
                ", year=" + year +
                ", condition='" + condition + '\'' +
                ", description='" + description + '\'' +
                '}';
    }


}
