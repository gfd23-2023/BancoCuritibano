### __BancoCuritibano__

O __Banco Curitibano__ é uma implementação do jogo __Banco Imobiliário__ que utiliza os princípios da Programação Orientada a Objetos, o padrão de arquitetura MVC (Modelo-Visão-Controlador) e padrões de design GoF.
A estrutura de pacotes e pastas e o uso do makefile estão detalhados nas próximas seções.

### __Estrutura do projeto__
O código-fonte está organizado no diretótio src, que segue a divisão lógica do MVC:
* Modelo: classes e pacotes que armazenam os dados principais do jogo. Exemplos incluem os jogadores e as casas do tabuleiro.
* Visão: classes responsáveis pela exibição do estado do jogo e pela interação com o usuário, como o menu principal.
* Controlador: classes responsáveis pela interação entre Modelo e Visão, além de fazer, também, interação entre classes do Modelo (entre jogadores e casas, por exemplo)

### __Uso do Makefile__
O makefile pode ser usado das três seguintes formas:
* `make`: sem regra, apenas gera os arquivos necessários para execução no diretório bin.
* `make run`: compila o projeto (se necessário) e executa o jogo.
* `make clean`: apaga todos os arquivos que não são código-fonte (em grande parte, arquivos com extensão .class)