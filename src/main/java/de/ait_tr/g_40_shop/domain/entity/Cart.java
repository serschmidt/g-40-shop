package de.ait_tr.g_40_shop.domain.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

//@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Customer customer;

    private List<Product> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // TODO Funktional zum Cart hinzufügen

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart cart)) return false;

        return Objects.equals(id, cart.id) && Objects.equals(customer, cart.customer) && Objects.equals(products, cart.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, products);
    }

    @Override
    public String toString() {
        return String.format("Cart: id - %d, contains %d products",
                id, products == null ? 0 : products.size());
    }
}
