package controlador;

// enumeracao dos estados do jogo
// funcionam como macros/defines
// facil de adicionar/remover estados
public enum Estados {
	MENU_INICIAL, 
	MENU_REGISTRO_JOGADORES, // escolha dos jogadores
	JOGAR_DADOS, // momento para jogar os dados
	JOGAR_MOVIMENTO, // momento para jogador vai se movimentar
	JOGAR_CASA // momento para exibir em qual casa jogador caiu
	// ...
}

