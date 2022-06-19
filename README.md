# Super Gerenciador Musical com API Spotify

## Table of Contents
1. **[Descrição](#Descrção)**
    1. **[Requisito 1](#Requisito-1)**
    2. **[Requisitos 2 e 3](#Requisitos-2-e-3)**
2. **[Execução do Sistema](#Execução-do-Sistema)**
    1. **[Pré-configurações para o frontend](#Pré-configurações-para-o-frontend)**
    2. **[Passos para a execução](#Passos-para-a-execução)**
3. **[Documentação](#Documentation)**
4. **[Observações](#Observações)**
5. **[Desenvolvedores](#Desenvolvedores)**

## Descrição

Projeto Final da disciplina MAC0321 - Laboratório de Programação Orientada a Objetos.
Este projeto possui o objetivo de ser um sistema Web capaz de permitir a um usuário buscar por músicas e criar playlists de uma forma personalizada.
Além disso, o usuário poderá ver uma série de características acústico-musicais das suas músicas e ordená-las de acordo com uma série de critérios.

### Requisito 1

Nosso sistema permite a um usuário a criação, remoção e listagem de playlists, e a criação e remoção de músicas em uma determinada playlist na sua conta do Spotify.
Para a realização dessa conexão com o spotify utilizamos a [Web API do spotify](https://developer.spotify.com/documentation/web-api/) essa conexão se dá por meio de uma autorização concedida pelo usuário, 
o fluxo de autorização que utilizamos foi o [Authorization Code Flow](https://developer.spotify.com/documentation/general/guides/authorization/code-flow/).
Para mais informações sobre o processo de autenticação implementado pelo nosso sistema veja a nossa [Documentação Requisito 1](https://docs.google.com/document/d/1cjTkzrrpy6fLiE7F1ueLTiix-Zb1A35GtyUuEEYnOEE/edit#heading=h.4i59c2ceu1x).

### Requisitos 2 e 3

Nosso sistema permite a busca de músicas por vários critérios, título, autor, nome do álbum e nome de playlists públicas do spotify, também permite a busca de álbuns e playlists públicas, que ao se clicar, mostra-se todas as músicas daquela playlist pública ou álbum (e permite que cada uma delas seja inserida em uma das playlists do usuário). Além disso, para cada resultado de uma busca de música ele apresenta uma visão detalhada, oferecendo uma série de parâmetros sobre a música, incluindo dançável, energia, andamento (tempo), força (loudness), fala (speechiness), instrumental, ao vivo, acústica. Por fim, para uma determinada playlist do usuário, todas essas informações são mostradas na forma de uma tabela e o usuário poderá ordenar essa tabela por cada um desses parâmetros segundo o seu gosto.

## Execução do Sistema

Nossa aplicação é dividida em frontend e backend, o nosso backend é implementado utilizando o framework Spring Boot, com a linguagem Java, já o nosso frontend é implementado utilizando a biblioteca React com a linguagem Javascript. 
É importante ressaltar que nosso projeto está definido como em fase de desenvolvimento na Web Api do Spotify, logo os usuários devem entrar apenas com uma das contas colocadas no DashBoard.

### Pré-configurações para o frontend
Para executar o frontend, é necessária a utilização do npm, a versão utilizada é a 8.5.5, além disso, também é necessário a utilização do node cuja versão utilizada foi a 16.15.0, recomendamos que ambos sejam instalados em suas versões mais recentes. Com estes passos feitos, dentro da pasta supergerenciadormusical/front rode o comando npm install.

### Passos para a execução
Execute o backend, executando o arquivo SuperGerenciadorMusicalApplication.java como Spring Boot App, feito isso, rode o seguinte comando npm run dev na pasta supergerenciadormusical/front para executar o frontend. 
Feito isso, abra o navegador e siga para “http://localhost:3000/login”. Nessa aba ocorre a autenticação, terminada a autenticação, por fim, abra uma nova aba e vá para  http://localhost:3000/, onde todos os serviços da aplicação podem ser realizados.
Para mais informações consulte os tópicos 1.1, 1.2 e 2.1 da documentação.

## Documentação

A fim de corresponder o controle de versionamento por meio do git do nosso projeto, a documentação correspondente a entrega atual foi transferida para um documento pdf, "docs.pdf". Nela estão apresentados de forma detalhada os tópicos: 

1. Pré-configurações e execução da aplicação
2. Detalhando a Jornada do Usuário
3. Arquitetura e Padrão de Projeto
4. Organização do frontend
5. Controllers
6. Models e Services

## Observações

1. Por utilizarmos acentuação em algumas classes, atributos e métodos, optamos por usar a Codificação UTF-16 padrão do Java.
2. O Procedimento de o usuário abrir a aba "http://localhost:3000/login" para autenticar, e após isso, o mesmo ir para a aba "http://localhost:3000/login" para iniciar a execução dos serviços da aplicação, será alterado para a próxima entrega, planejamos fazer com que esse direcionamento seja automático.

## Desenvolvedores

Henrique Paes - hpaesdesouza@usp.br

Izaque Sena - izaque.sena@usp.br

João Pedro - miranda.jp@usp.br

Luiz Guilherme - luizflaminio@usp.br

Vinícius Viana - viniciusviana@usp.br

Vitor Wallace - vitor.walace.alves@usp.br
