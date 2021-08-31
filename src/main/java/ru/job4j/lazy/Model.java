package ru.job4j.lazy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "model")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand1;


    public static Model of(String name, Brand brand1) {
        Model model = new Model();
        model.name = name;
        model.brand1 = brand1;
        return model;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Model model = (Model) o;
        return id == model.id
                && name.equals(model.name)
                && Objects.equals(brand1, model.brand1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, brand1);
    }

    @Override
    public String toString() {
        return "Model{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", brand=" + brand1
                + '}';
    }

    public Brand getBrand() {
        return brand1;
    }

    public void setBrand(Brand brand) {
        this.brand1 = brand;
    }
}
