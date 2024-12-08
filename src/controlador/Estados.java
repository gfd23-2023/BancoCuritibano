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
	JOGAR_CARTA,			// retirar uma carta
	JOGAR_ACAO_CARTA,		// ação da carta retirada
	JOGAR_PROXIMO,			// ir para proxima jogada
	// ...
}

