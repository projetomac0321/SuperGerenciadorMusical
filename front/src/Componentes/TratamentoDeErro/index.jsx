export function TratamentoDeErro(err){
        if(err.response) {
            alert("Tente novamente. Falha na resposta, erro de status code " + err.response.status); 
            window.location.href = "http://localhost:3000/";}
          else if(err.request){
            alert("Tente novamente mais tarde. A requisição foi feita, mas nenhuma resposta foi obtida");
            window.location.href = "http://localhost:3000/";}
          else {
            alert("Tente novamente mais tarde. Erro na configuração da requisição.");
            window.location.href = "http://localhost:3000/";}
}