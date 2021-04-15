# Salesman

Este projeto foi criado para processar um arquivo de entrada, onde os dados são separados por um carácter delimitador, possuindo três tipos de dados, os vendedores, os clientes e
as vendas. A partir destes dados de entrada tem-se como objetivo gerar um relatório com os seguintes dados:

- Quantidade de clientes
- Quantidade de vendedores
- Pior vendedor
- Venda mais cara

# Destaque do que foi utilizado para o desenvolvimento deste projeto:

- Java 11
- Spring Integration
- Gradle
- JUnit

# Configurações possíveis

- separator: ç
  - Utilizado para definir o separador do arquivo

- file.out.regex: (.*)(\.dat)
  - Utilizado para definir a regex de replace para gerar o nome do arquivo de output

- file.out.replace: $1.done$2
    - Utilizado para definir o replacement para gerar o nome do arquivo de output

- file.out.folder: /data/out
    - Utilizado para definir a pasta que será gravado os arquivos de saida. Caminho relativo da user home do usuário.

- file.in.folder: /data/in
    - Utilizado para definir a pasta da qual será lido os arquivos. Caminho relativo da user home do usuário.

- file.in.type: "*.dat"
    - Pattern para validar os tipos de arquivos a serem processados

- poller.timerate: 1000
    - Intervalo de verificação para novos arquivos

- poller.maxmessages: 5
    - Máximo de mensagens por poll

##### Como utilizar

- git clone https://github.com/reawmarco/salesman.git
- cd salesman
- ./gradlew build bootRun
