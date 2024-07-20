package br.com.netdeal.application.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioDto {
    private Long id;
    private String nome;
    private CargoDto cargo;
    private LiderancaDto lideranca;
    private String senha;
    private ForcaSenhaDto forcaSenha;

    @Data
    public static class CargoDto {
        private String titulo;
        private int nivel;
    }

    @Data
    public static class LiderancaDto {
        private Long id;
        private String nome;
    }

    @Data
    @Builder
    public static class ForcaSenhaDto {
        private int score;
        private String descricao;
    }

}

