
// IMPORTANT: notice the next request is scheduled only after the
//            previous request is fully processed either successfully
//	      or not.

function refreshVotes() {
	var request = new XMLHttpRequest();
        var vBoard=document.getElementById("votes");
        
        request.onload = function() {
            vBoard.innerHTML = this.responseText;
            vBoard.style.color="black";
            setTimeout(refreshVotes, 2000);
            };
            
        request.ontimeout = function() {
            vBoard.innerHTML = "Server timeout, still trying ...";
            vBoard.style.color="red";
            setTimeout(refreshVotes, 100); 
        };
        
        request.onerror = function() { 
            vBoard.innerHTML = "No server reply, still trying ...";
            vBoard.style.color="red";
            setTimeout(refreshVotes, 5000); 
        };
        
  	request.open("GET", "/votes", true);
	request.timeout = 5000;
  	request.send();
	}

function botaoSair() {
    var request = new XMLHttpRequest();
    request.open("PUT", "/sair" , true);
    request.send();
}

// Ao fechar a janela...
window.onbeforeunload = closeWindow;

function closeWindow(){
    var request = new XMLHttpRequest();
    request.open("PUT", "/sair" , true);
    request.send();
}

function assignarPedidoParaAprovar(idPedido){
    var request = new XMLHttpRequest();
    request.open("PUT", "/assignarPedidoParaAprovar/" + idPedido , true);
    request.send();
}

function assignarPedidoAprovado(idPedido){
    var request = new XMLHttpRequest();
    request.open("PUT", "/assignarPedidoAprovado/" + idPedido , true);
    request.send();
}

function aprovarPedido(idPedido){
    var request = new XMLHttpRequest();
    request.open("PUT", "/aprovarPedido/" + idPedido , true);
    request.send();
}

function rejeitarPedido(idPedido){
    var request = new XMLHttpRequest();
    request.open("PUT", "/rejeitarPedido/" + idPedido , true);
    request.send();
}