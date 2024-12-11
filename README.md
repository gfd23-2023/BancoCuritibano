## __BancoCuritibano__

O __Banco Curitibano__ é uma implementação do jogo __Banco Imobiliário__ que utiliza os princípios da Programação Orientada a Objetos, o padrão de arquitetura MVC (Modelo-Visão-Controlador) e padrões de design GoF.
As regras do jogo, a estrutura de pacotes e pastas e o uso do makefile estão detalhados nas próximas seções.

#### __Regras do jogo__
Esta seção descreve as regras do jogo. É importante frisar que, no registro de jogadores, o preenchimento deve ser feito nos campos de cima para baixo, pressionando `Enter`após digitar cada nome e "Continuar" quando desejar iniciar o jogo.
###### Dinheiro
Cada jogador começa com 5000 reais.

##### Cores
As cores são definidas automaticamente, dependendo da ordem em que os nomes são digitados.

##### Fluxo do jogo
A ordem de jogadas segue a ordem de inserção dos jogadores no registro.
Um jogador, em seu turno, roda os dados. Automaticamente, a "peça" com sua cor será movimentada no tabuleiro e a ação da casa de destino do jogador (se houver) será acionada. As ações de cada tipo de casa estão descritas na próxima seção.

##### Casas
* Propriedade: ao cair em uma propriedade, existem três opções. A primeira é a de comprá-la, se ainda não tiver nenhum proprietário. A segunda é pagar o aluguel, se houver um proprietário que não seja o próprio jogador. A terceira é fazer uma construção, se atender aos requisitos descritos na seção "Construção".

* Carta: ao cair em uma casa de carta, uma carta entre as descritas em "/src/modelo/cartas/cartas.csv" será sorteada e aplicada no jogador.

* Blitz: ao cair na blitz, o jogador será mandado para a cadeia.

* Imposto de renda: ao cair no imposto de renda, o jogador terá que pagar 100 reais.

##### Construção
O jogo aceita construções (criar casas) em uma propriedade. Essas construções podem ser feitas levando em consideração que:
* O número máximo de casas por propriedade é 5.
* O jogador só poderá construir se tiver o monopólio sobre a cor da propriedade em que deseja construir. Por exemplo, se o jogador quer construir em uma propriedade verde, a construção só poderá acontecer se esse mesmo jogador for o dono de todas as propriedades verdes do tabuleiro.
* Para construir a i-ésima casa em uma propriedade, todas as outras propriedades de mesma cor devem ter, pelo menos, i-1 casas.

##### Prisão
Um jogador pode ser enviado para a prisão de dois modos diferentes: escolhendo a opção de ir direto para a cadeia na carta "Pagar ou cadeia" ou caindo na casa "Blitz". Ao ser preso, o jogador fica três rodadas sem jogar - mas ainda pode receber seu aluguel.

## __Estrutura do projeto__
O código-fonte está organizado no diretório src, que segue a divisão lógica do MVC:
* Modelo: classes e pacotes que armazenam os dados principais do jogo. Exemplos incluem os jogadores e as casas do tabuleiro.
* Visão: classes responsáveis pela exibição do estado do jogo e pela interação com o usuário, como o menu principal.
* Controlador: classes responsáveis pela interação entre Modelo e Visão, além de fazer, também, interação entre classes do Modelo (entre jogadores e casas, por exemplo).

## __Uso do Makefile__
O makefile pode ser usado das quatro seguintes formas:
* `make`: sem regra, apenas gera os arquivos necessários para execução no diretório bin.
* `make run`: compila o projeto (se necessário) e executa o jogo.
* `make clean`: apaga todos os arquivos que não são código-fonte (em grande parte, arquivos com extensão .class).
* `make clean_salvamento`: apaga o arquivo .ser, usado para carregar um jogo salvo.