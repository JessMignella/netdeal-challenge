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

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "cargo_titulo", referencedColumnName = "titulo"),
            @JoinColumn(name = "cargo_nivel", referencedColumnName = "nivel")
    })
    private Cargo cargo;

    @ManyToOne
    @JoinColumn(name = "lideranca_id")
    private Funcionario lideranca;

    private String senha;

    @ManyToOne
    @JoinColumn(name = "forca_senha_score")
    private ForcaSenha forcaSenha;

}
