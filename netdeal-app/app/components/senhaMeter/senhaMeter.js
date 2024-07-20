app.directive('senhaMeter', function () {
    return {
        restrict: 'E',
        scope: {
            senha: '='
        },
        templateUrl: 'components/senhaMeter/senhaMeter.html',
        link: function (scope) {
            scope.score = 0;

            // Função para atualizar o score da senha
            scope.atualizarScore = function () {
                scope.score = calcularScoreSenha(scope.senha);
            };

            // Função para obter a descrição do score
            scope.obterDescricaoScore = function () {
                if (scope.score < 20) {
                    return 'Muito Fraca';
                } else if (scope.score < 40) {
                    return 'Fraca';
                } else if (scope.score < 60) {
                    return 'Média';
                } else if (scope.score < 80) {
                    return 'Forte';
                } else {
                    return 'Muito Forte';
                }
            };
        }
    };
});

function calcularScoreSenha(senha) {
    let score = 0;
    // Pontuação baseada no comprimento da senha
    score += senha.length * 4;

    // Pontuação adicional e subtraida por caracteres maiusculos
    score += calcularPontuacaoMaiusculas(senha);
    // Pontuação adicional e subtraida por caracteres minusculos
    score += calcularPontuacaoMinusculas(senha);
    // Pontuação adicional e subtraida por numeros
    score += calcularPontuacaoNumeros(senha);
    // Pontuação adicional por caracteres especiais
    score += calcularPontuacaoEspeciais(senha);

    // Verificar se a senha cumpre os requisitos
    if (senha.length >= 8 && cumprirRequisitos(senha)) {
        score += 10;
    }
    // Pontuação subtraida quando apenas letras
    if (/^[a-zA-Z]+$/.test(senha)) {
        score -= senha.length;
    }
    //Pontuação subitraida quando caracteres repetidos
    score -= caracteresRepetidos(senha);
    if (score > 0) {
        return Math.min(100, score);

    } else return 0;
}

// Função para calcular pontuação de caracteres maiusculos
function calcularPontuacaoMaiusculas(senha) {
    let score = 0;
    if (/[A-Z]/.test(senha)) {
        score += senha.replace(/[A-Z]/g, '').length * 2;
        score -= contarConsecutivos(senha, /[A-Z]+/g);
    }
    return score;
}

// Função para calcular pontuação de caracteres minusculos
function calcularPontuacaoMinusculas(senha) {
    let score = 0;
    if (/[a-z]/.test(senha)) {
        score += senha.replace(/[a-z]/g, '').length * 2;
        score -= contarConsecutivos(senha, /[a-z]+/g);
    }
    return score;
}

// Função para calcular pontuação de numeros
function calcularPontuacaoNumeros(senha) {
    let score = 0;
    if (/[0-9]/.test(senha)) {
        score += senha.replace(/[0-9]/g, '').length * 4;
        if (/^[0-9]+$/.test(senha)) {
            score -= senha.length;
        }
        score -= (numerosConsecutivos(senha) * 2);
        score -= (numerosSequenciais(senha) * 3);
    }
    return score;
}

// Função para calcular pontuação de caracteres especiais
function calcularPontuacaoEspeciais(senha) {
    let score = 0;
    if (/[^A-Za-z0-9]/.test(senha)) {
        score += senha.replace(/[^A-Za-z0-9]/g, '').length * 6;
    }
    return score;
}

// Função para contar caracteres consecutivos
function contarConsecutivos(senha, regex) {
    const matches = senha.match(regex);
    let unicos = new Set(senha);
    if (matches && unicos.size < 9) {
        return matches.reduce((total, match) => total + Math.max(0, match.length - 1), 0);
    }
    return 0;
}

// Função para contar numeros consecutivos
function numerosConsecutivos(senha) {
    const regex = /(\d)\1{1,}/g;
    const matches = senha.match(regex);
    let unicos = new Set(senha);
    if (matches && unicos.size < 9) {
        return matches.reduce((total, match) => total + Math.max(0, match.length - 1), 0);
    }
    return 0;
}

// Função para contar numeros sequenciais (crescente e decrescente)
function numerosSequenciais(senha) {
    const regexAsc = /(?=\d{3})\d*(012|123|234|345|456|567|678|789|890)/g;
    const regexDesc = /(?=\d{3})\d*(987|876|765|654|543|432|321|210)/g;
    const matchesAsc = senha.match(regexAsc) || [];
    const matchesDesc = senha.match(regexDesc) || [];
    const allMatches = [...matchesAsc, ...matchesDesc];
    return allMatches.reduce((total, match) => total + Math.max(0, match.length - 2), 0);
}

// Função para contar letras sequenciais (crescente e decrescente)
function letrasSequenciais(password) {
    const regexAsc = /(?:abc|bcd|cde|def|efg|fgh|ghi|hij|ijk|jkl|klm|lmn|mno|nop|opq|pqr|qrs|rst|stu|tuv|uvw|vwx|wxy|xyz)+/g;
    const regexDesc = /(?:zyx|yxw|xwv|wvu|vut|uts|tsr|srq|rqp|qpo|pon|onm|nml|mlk|lkj|kji|jih|ihg|hgf|gfe|fed|edc|dcb|cba)+/g;
    const matchesAsc = password.match(regexAsc) || [];
    const matchesDesc = password.match(regexDesc) || [];
    let allMatches = [...matchesAsc, ...matchesDesc];
    allMatches = [...new Set(allMatches)];
    const uniqueMatches = [];
    allMatches.forEach(match => {
        const existing = uniqueMatches.find(m => m.includes(match) || match.includes(m));
        if (!existing || match.length > existing.length) {
            uniqueMatches.push(match);
        }
    });
    return uniqueMatches.reduce((total, match) => total + Math.max(0, match.length - 2), 0);
}

// Função para verificar se a senha cumpre os requisitos
function cumprirRequisitos(senha) {
    let requisitosCumpridos = 0;
    if (/[A-Z]/.test(senha)) requisitosCumpridos++;
    if (/[a-z]/.test(senha)) requisitosCumpridos++;
    if (/[0-9]/.test(senha)) requisitosCumpridos++;
    if (/[^A-Za-z0-9]/.test(senha)) requisitosCumpridos++;
    return requisitosCumpridos >= 3;
}

//formula de caracteres repetidos feita por mim porque a função não esta declarada
function caracteresRepetidos(senha) {
    let score = 0;
    let unicos = new Set(senha);
    if (unicos.size < 9) {
        score = (senha.length - unicos.size) * senha.length;
    }
    return score;
}
//não fiz Simbolos sequenciais porque não entendi o que é considerado 