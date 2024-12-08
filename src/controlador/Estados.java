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
	JOGAR_CARTA,			// exibe botao para retirar uma carta
	JOGAR_CARTA_ACAO,		// executa a ação da carta
	JOGAR_CARTA_OPCAO,		// exibe botao das opcoes (pagar ou cadeia)	
	JOGAR_PROXIMO,			// exibe botao para proxima jogada
	// ...
}

