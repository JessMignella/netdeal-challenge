package br.com.netdeal.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@IdClass(CargoId.class)
public class Cargo {
    @Id
    private String titulo;
    @Id
    private int nivel;
}
