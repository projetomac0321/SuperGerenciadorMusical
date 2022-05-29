# Super Gerenciador Musical com API Spotify

## Table of Contents
1. **[Descrição](#Descrção)**
    1. **[Requisito 1](#Requisito-1)**
2. **[Execução do Sistema](#Execução-do-Sistema)**
3. **[Documentação](#Documentation)**
4. **[Desenvolvedores](#Desenvolvedores)**

## Descrição

Projeto Final da disciplina MAC0321 - Laboratório de Programação Orientada a Objetos.
Este projeto possui o objetivo de ser um sistema Web capaz de permitir a um usuário buscar por músicas e criar playlists de uma forma personalizada.
Além disso, o usuário poderá ver uma série de características acústico-musicais das suas músicas e ordená-las de acordo com uma série de critérios.

### Requisito 1

Nosso sistema permite a um usuário a criação, remoção e listagem de playlists, e a criação e remoção de músicas em uma determinada playlist na sua conta do Spotify.
Para a realização dessa conexão com o spotify utilizamos a [Web API do spotify](https://developer.spotify.com/documentation/web-api/) essa conexão se dá por meio de uma autorização concedida pelo usuário, 
o fluxo de autorização que utilizamos foi o [Authorization Code Flow](https://developer.spotify.com/documentation/general/guides/authorization/code-flow/).
Para mais informações sobre o processo de autenticação implementado pelo nosso sistema veja a nossa [Documentação](https://docs.google.com/document/d/1cjTkzrrpy6fLiE7F1ueLTiix-Zb1A35GtyUuEEYnOEE/edit#heading=h.4i59c2ceu1x).

## Execução do Sistema

Devido ao fato de ser a primeira entrega/versão do projeto, nosso sistema não foi implementado com todas as funcionalidades do objetivo da entrega final. 
Portanto, nosso sistema, nesse primeiro momento, não possui FrontEnd, sendo a interação com o usuário realizada via terminal. 
Ao executar nosso projeto, o usuário deverá ir para a porta local padrão definida pelo springboot: localhost:8080/ realizar a autorização, e prosseguir para o terminal.   
É importante ressaltar que nosso projeto está definido como em fase de desenvolvimento na Web Api do Spotify, logo os usuários devem entrar apenas com uma das contas colocadas no DashBoard.  

## Documentação

Visite a [Documentação](https://docs.google.com/document/d/1cjTkzrrpy6fLiE7F1ueLTiix-Zb1A35GtyUuEEYnOEE/edit#heading=h.8ig6huv67gzu) detalhada do nosso projeto onde explicamos a implementação do nosso sistema e seus componentes.

## Desenvolvedores

Henrique Paes - hpaesdesouza@usp.br

Izaque Sena - izaque.sena@usp.br

João Pedro - miranda.jp@usp.br

Luiz Guilherme - luizflaminio@usp.br

Vinícius Viana - viniciusviana@usp.br

Vitor Wallace - vitor.walace.alves@usp.br
