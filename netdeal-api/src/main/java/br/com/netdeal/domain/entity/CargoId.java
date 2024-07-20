package br.com.netdeal.domain.entity;

import java.io.Serializable;
import lombok.Data;

@Data
public class CargoId implements Serializable {
    private String titulo;
    private int nivel;
}
