package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Consulta} entity.
 */
public class ConsultaDTO implements Serializable {

    private Long id;

    private String name;

    private String cpf;

    private LocalDate date;

    private String email;

    private Double price;

    private Double durationInMinutes;

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(Double durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ConsultaDTO)) {
            return false;
        }

        ConsultaDTO consultaDTO = (ConsultaDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, consultaDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ConsultaDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", cpf='" + getCpf() + "'" +
            ", date='" + getDate() + "'" +
            ", email='" + getEmail() + "'" +
            ", price=" + getPrice() +
            ", durationInMinutes=" + getDurationInMinutes() +
            "}";
    }
}
