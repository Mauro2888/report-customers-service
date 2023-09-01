package report.model.entity;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {

    @NotBlank
    private String name;

    @NotBlank
    private String address;
    @NotBlank
    private String phoneNumber;

    @NotNull
    @PastOrPresent
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate createdAt;

    @PrePersist
    void prePersist(){
        createdAt = LocalDate.now();
    }


    public Customer() {
    }

    public Customer(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) && Objects.equals(address, customer.address) && Objects.equals(phoneNumber, customer.phoneNumber) && Objects.equals(createdAt, customer.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, phoneNumber, createdAt);
    }
}

