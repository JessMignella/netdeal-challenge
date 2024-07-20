package br.com.netdeal.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Embedded
    private Cargo cargo;

    @ManyToOne
    @JoinColumn(name = "lideranca_id")
    private Funcionario lideranca;

    private String senha;

    @Embedded
    private ForcaSenha forcaSenha;

    @Embeddable
    @Data
    public static class Cargo {
        private String titulo;
        private int nivel;
    }

}
