package de.ait_tr.g_40_shop.controller;

import de.ait_tr.g_40_shop.domain.dto.ProductDto;
import de.ait_tr.g_40_shop.exception_handling.Response;
import de.ait_tr.g_40_shop.exception_handling.exceptions.FirstTestException;
import de.ait_tr.g_40_shop.service.interfaces.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // CRUD - Create (POST), Read(GET), Update(PUT), Delete (DELETE)

    // Create product - POST - localhost:8080/products
    @PostMapping
    public ProductDto save(@RequestBody ProductDto product) {
        return service.save(product);
    }

    //  1. Alle Produkte zu bekommen allen erlauben, auch anonyme Nutzer
    //  2. Anfrage nach Identifikator nur für eingelogte Nutzer egal mit welcher Rolle
    //  3. Verändern nur Admin


    @GetMapping
    public List<ProductDto> get(@RequestParam(required = false) Long id) {
        if (id == null) {
            return service.getAllActiveProducts();
        } else {
            ProductDto product = service.getById(id);
            return product == null ? null : List.of(product);
        }
    }

    @GetMapping("/all")
    public List<ProductDto> getAll(){
        return service.getAllActiveProducts();
    }

    //Get product - GET - localhost:8080/products/3
    //Get product - GET - localhost:8080/products/3/5/7
    // Example for different input parameter as number after slash
//    @GetMapping("/{id}")
//    public List<Product> getById(@PathVariable Long id) {
//        return List.of(service.getById(id));
//
//    }

    // Update product - PUT - localhost:8080/products
    @PutMapping
    public ProductDto update(@RequestBody ProductDto product) {
        return service.update(product);
    }

    //delete product - DELETE - localhost:8080/products?id=3

    @DeleteMapping
    public void delete(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String title
    ) {
        if (id != null) {
            service.deleteById(id);
        } else if (title != null) {
            service.deleteByTitle(title);
        }
    }

    @PutMapping("/restore")
    public void restore(@RequestParam Long id) {
        service.restoreById(id);
    }

    @GetMapping("/quantity")
    public long getQuantity() {
        return service.getActiveProductsQuantity();
    }

    @GetMapping("/total-price")
    public BigDecimal getTotalPrice() {
        return service.getActiveProductsTotalPrice();
    }

    @GetMapping("/average-price")
    public BigDecimal getAveragePrice() {
        return service.getActiveProductsAveragePrice();
    }

    // 1 condition to handle with exceptions
    //  PLUS  - punktuelle Einstellung der einzelnen Exceptions
    //          für einzelne Controller, wenn wir verschiedene Lösungen
    //          für verschiedene Controller brauchen.
    //  MINUS -
    @ExceptionHandler(FirstTestException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)  // richtiger Status, aber keine Weiterleitung der Mitteilung
    public Response handleException(FirstTestException e) {
        return new Response((e.getMessage()));
    }

}
