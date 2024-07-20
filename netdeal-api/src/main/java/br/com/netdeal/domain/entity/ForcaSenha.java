package br.com.netdeal.domain.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Embeddable
@Data
public class ForcaSenha {
    private int score;
    private String descricao;


    public static ForcaSenha of(String Senha) {
        ForcaSenha forcaSenha = new ForcaSenha();
        forcaSenha.setScore(forcaSenha.calcularScoreSenha(Senha));
        forcaSenha.setDescricao(obterDescricao(forcaSenha.getScore()));
        return forcaSenha;
    }

    public int calcularScoreSenha(String senha) {
        if (senha == null || senha.isEmpty()) {
            return 0;
        }

        int score = 0;

        // Pontuação baseada no comprimento da senha
        score += senha.length() * 4;

        // Pontuação adicional e subtraída por caracteres maiúsculos
        score += calcularPontuacaoMaiusculas(senha);

        // Pontuação adicional e subtraída por caracteres minúsculos
        score += calcularPontuacaoMinusculas(senha);

        // Pontuação adicional e subtraída por números
        score += calcularPontuacaoNumeros(senha);

        // Pontuação adicional por caracteres especiais
        score += calcularPontuacaoEspeciais(senha);

        // Verificar se a senha cumpre os requisitos
        if (senha.length() >= 8 && cumprirRequisitos(senha)) {
            score += 10;
        }

        // Pontuação subtraída quando apenas letras
        if (senha.matches("^[a-zA-Z]+$")) {
            score -= senha.length();
        }

        // Pontuação subtraída quando caracteres repetidos
        score -= caracteresRepetidos(senha);

        return Math.max(0, Math.min(100, score));
    }

    public static String obterDescricao(int score) {
        if (score < 20) {
            return "Muito Fraca";
        } else if (score < 40) {
            return "Fraca";
        } else if (score < 60) {
            return "Média";
        } else if (score < 80) {
            return "Forte";
        } else {
            return "Muito Forte";
        }
    }

    public int calcularPontuacaoMaiusculas(String senha) {
        int score = 0;
        if (senha.matches(".*[A-Z].*")) {
            int uppercaseCount = senha.replaceAll("[^A-Z]", "").length();
            score += uppercaseCount * 2 - contarConsecutivos(senha, "[A-Z]+");
        }
        return score;
    }

    public int calcularPontuacaoMinusculas(String senha) {
        int score = 0;
        if (senha.matches(".*[a-z].*")) {
            score += (senha.length() - senha.replaceAll("[a-z]", "").length()) * 2;
            score -= contarConsecutivos(senha, "[a-z]+");
        }
        return score;
    }

    public int calcularPontuacaoNumeros(String senha) {
        int score = 0;
        Matcher matcher = Pattern.compile("(\\d)\\1{1,}").matcher(senha);
        int countConsecutivos = 0;
        while (matcher.find()) {
            countConsecutivos += Math.max(0, matcher.group().length() - 1);
        }
        int countSequenciais = numerosSequenciais(senha);

        if (senha.matches(".*[0-9].*")) {
            score += (senha.length() - senha.replaceAll("[0-9]", "").length()) * 4;
            if (senha.matches("^[0-9]+$")) {
                score -= senha.length();
            }
            score -= countConsecutivos * 2;
            score -= countSequenciais * 3;
        }
        return score;
    }

    public int calcularPontuacaoEspeciais(String senha) {
        int score = 0;
        if (senha.matches(".*[^A-Za-z0-9].*")) {
            score += (senha.length() - senha.replaceAll("[^A-Za-z0-9]", "").length()) * 6;
        }
        return score;
    }

    public int contarConsecutivos(String senha, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(senha);
        int count = 0;
        while (matcher.find()) {
            // Subtrair 1 para desconsiderar o caractere que foi encontrado
            count += Math.max(0, matcher.group().length() - 1);
        }
        return count;
    }


    public int numerosSequenciais(String senha) {
        Pattern patternAsc = Pattern.compile("(?=\\d{3})\\d*(012|123|234|345|456|567|678|789|890)");
        Pattern patternDesc = Pattern.compile("(?=\\d{3})\\d*(987|876|765|654|543|432|321|210)");
        int count = 0;
        for (Matcher matcherAsc = patternAsc.matcher(senha); matcherAsc.find();) {
            count += Math.max(0, matcherAsc.group().length() - 2);
        }
        for (Matcher matcherDesc = patternDesc.matcher(senha); matcherDesc.find();) {
            count += Math.max(0, matcherDesc.group().length() - 2);
        }
        return count;
    }

    public boolean cumprirRequisitos(String senha) {
        int requisitosCumpridos = 0;
        if (senha.matches(".*[A-Z].*")) requisitosCumpridos++;
        if (senha.matches(".*[a-z].*")) requisitosCumpridos++;
        if (senha.matches(".*[0-9].*")) requisitosCumpridos++;
        if (senha.matches(".*[^A-Za-z0-9].*")) requisitosCumpridos++;
        return requisitosCumpridos >= 3;
    }

    public int caracteresRepetidos(String senha) {
        Set<Character> unicos = new HashSet<>();
        for (char c : senha.toCharArray()) {
            unicos.add(c);
        }
        return (senha.length() - unicos.size()) * senha.length();
    }
}