# Nome do compilador
JAVAC = javac

# Diretórios
SRC_DIR = src
BIN_DIR = bin

# Nome da classe principal (com pacote, se aplicável)
MAIN_CLASS = Main  # Alterar para algo como `app.Main` se estiver em um pacote

# Busca por todos os arquivos .java no diretório src
SOURCES = $(shell find $(SRC_DIR) -name "*.java")

# Alvo padrão: compila todos os .java
.PHONY: all
all:
	mkdir -p $(BIN_DIR)
	$(JAVAC) -d $(BIN_DIR) $(SOURCES)

# Alvo para rodar o programa
.PHONY: run
run: all
	java -cp $(BIN_DIR) $(MAIN_CLASS)

# Limpeza dos arquivos compilados
.PHONY: clean
clean:
	find $(BIN_DIR) -name "*.class" -delete

