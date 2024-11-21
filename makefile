# Nome do compilador
JAVAC = javac

# Diretórios
SRC_DIR = src
BIN_DIR = bin

# Nome da classe principal
MAIN_CLASS = Main

# Busca por todos os arquivos .java no diretório src
SOURCES = $(shell find $(SRC_DIR) -name "*.java")

# Cria os nomes dos arquivos .class correspondentes no diretório bin
CLASSES = $(patsubst $(SRC_DIR)/%.java, $(BIN_DIR)/%.class, $(SOURCES))

# Alvo padrão
.PHONY: all
all: $(CLASSES)

# Regra para compilar arquivos .java em .class
$(BIN_DIR)/%.class: $(SRC_DIR)/%.java
	mkdir -p $(BIN_DIR)
	$(JAVAC) -d $(BIN_DIR) $<

# Alvo para rodar o programa
.PHONY: run
run: all
	java -cp $(BIN_DIR) $(MAIN_CLASS)

# Limpeza dos arquivos compilados
.PHONY: clean
clean:
	rm -rf $(BIN_DIR)/*.class

