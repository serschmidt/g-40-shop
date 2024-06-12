package de.ait_tr.g_40_shop.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //// Название должно быть длиной хотя бы 3 символа
    //// Название должно начинаться с заглавной буквы
    //// Остальные буквы в названии должны быть строчными латинскими (разрешаются пробелы)
    //  Titel darf keine Nummern und keine Symbole enthalten
    //  Title darf nicht null ist
    //  Banana -V
    //  Ba  -X
    //  BANANA -X
    //  BananA  -X
    //  Banana7 -X
    //  Banana# -X

    @NotNull(message = "Product title cannot be null")
    @NotBlank(message = "Product title cannot be empty")
    @Pattern(
        regexp = "[A-Z][a-z]{2,}",
        message = "Product title should be at least 3 character length "
                + "and start with capital letter"
    )
    @Column(name = "title")
    private String title;

//    @Min(5)
//    @Max(10000)
    @DecimalMin(
        value = "5.00",
        message = "Product pricer should be greater or equal than 5.00"
    )
    @DecimalMax(
        value = "100000.00",
        inclusive = false,
        message = "Product pricer should be lesser than 100000.00"
    )
    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "active")
    private boolean active;

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;
        return active == product.active && Objects.equals(id, product.id) && Objects.equals(title, product.title) && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price, active);
    }

    @Override
    public String toString() {
        return String.format("Product: id - %d, title - %s, price - %s, active - %s",
                id, title, price, active ? "yes" : "no");
    }
}
