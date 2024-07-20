package br.com.netdeal.domain.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ForcaSenhaTest {

    @Test
    void testOfMethod() {
        ForcaSenha forcaSenha = ForcaSenha.of("Senha123!");
        assertNotNull(forcaSenha);
        assertEquals(68, forcaSenha.getScore());
        assertEquals("Forte", forcaSenha.getDescricao());
    }

    @Test
    void testObterDescricaoScore() {
        assertEquals("Muito Fraca", ForcaSenha.obterDescricao(10));
        assertEquals("Fraca", ForcaSenha.obterDescricao(30));
        assertEquals("Média", ForcaSenha.obterDescricao(50));
        assertEquals("Forte", ForcaSenha.obterDescricao(70));
        assertEquals("Muito Forte", ForcaSenha.obterDescricao(90));
    }

    @Test
    void testCalcularScoreSenha() {
        ForcaSenha forcaSenha = new ForcaSenha();

        assertEquals(0, forcaSenha.calcularScoreSenha(null));
        assertEquals(0, forcaSenha.calcularScoreSenha(""));

        assertEquals(13, forcaSenha.calcularScoreSenha("abc"));
        assertEquals(58, forcaSenha.calcularScoreSenha("Senha123"));
        assertEquals(68, forcaSenha.calcularScoreSenha("Senha123!"));
    }

    @Test
    void testNumerosConsecutivos() {
        ForcaSenha forcaSenha = new ForcaSenha();
        String regex = "(\\d)\\1{1,}";
        assertEquals(0, forcaSenha.contarConsecutivos("a1b2c3",regex));
        assertEquals(1, forcaSenha.contarConsecutivos("112", regex));
        assertEquals(2, forcaSenha.contarConsecutivos("111",regex));
    }

    @Test
    void testCaracteresMaiusculosConsecutivos() {
        ForcaSenha forcaSenha = new ForcaSenha();
        String regex = "[A-Z]+";
        assertEquals(0, forcaSenha.contarConsecutivos("!!!",regex));
        assertEquals(2, forcaSenha.contarConsecutivos("AAA", regex));
        assertEquals(2, forcaSenha.contarConsecutivos("AAbCD",regex));
    }
    @Test
    void testCaracteresMinusculosConsecutivos() {
        ForcaSenha forcaSenha = new ForcaSenha();
        String regex = "[a-z]+";
        assertEquals(0, forcaSenha.contarConsecutivos("!!!",regex));
        assertEquals(1, forcaSenha.contarConsecutivos("Azz", regex));
        assertEquals(4, forcaSenha.contarConsecutivos("ddddd",regex));
    }
    @Test
    void testNumerosSequenciais() {
        ForcaSenha forcaSenha = new ForcaSenha();

        assertEquals(0, forcaSenha.numerosSequenciais("a1b2c3"));
        assertEquals(1, forcaSenha.numerosSequenciais("123"));
        assertEquals(4, forcaSenha.numerosSequenciais("123456"));
        assertEquals(1, forcaSenha.numerosSequenciais("321"));
    }

    @Test
    void testCumprirRequisitos() {
        ForcaSenha forcaSenha = new ForcaSenha();
        assertFalse(forcaSenha.cumprirRequisitos("senha"));
        assertTrue(forcaSenha.cumprirRequisitos("Senha123!"));
    }

    @Test
    void testCaracteresRepetidos() {
        ForcaSenha forcaSenha = new ForcaSenha();

        assertEquals(0, forcaSenha.caracteresRepetidos("abc"));
        assertEquals(8, forcaSenha.caracteresRepetidos("aabb"));
        assertEquals(6, forcaSenha.caracteresRepetidos("aaa"));
    }
}