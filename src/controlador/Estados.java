package controlador;

// enumeracao dos estados do jogo
// funcionam como macros/defines
// facil de adicionar/remover estados
public enum Estados {
	MENU_INICIAL, 
	MENU_REGISTRO_JOGADORES,// escolha dos jogadores
	JOGAR_DADOS,			// jogar os dados
	JOGAR_MOVIMENTO,		// jogador vai se movimentar depois de jogar os dados
	JOGAR_CASA,				// analise da casa atual
	JOGAR_PROPRIEDADE,		// exibe propriedade e possibilidade de compra
	JOGAR_ALUGUEL,			// propriedade tem dono, paga aluguel
	JOGAR_IMPOSTO,			// caiu na casa de pagar imposto
	JOGAR_CADEIA,			// caiu na casa vai para cadeia
	JOGAR_CONSTRUIR,		// tem o monopolio, exibe propriedade, pode construir
	JOGAR_PARTIDA,			// vai voltar para o ponto de partida 
	JOGAR_CARTA,			// exibe botao para retirar uma carta
	JOGAR_CARTA_ACAO,		// executa a ação da carta
	JOGAR_CARTA_OPCAO,		// exibe botao das opcoes (pagar ou cadeia)	
	JOGAR_PROXIMO,			// exibe botao para proxima jogada
	JOGAR_SALVAR,			// salvar jogo
	JOGAR_CARREGAR,			// carregar jogo
	// ...
}

