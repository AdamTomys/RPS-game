let client = null;

function connect() {
  client = Stomp.client('ws://localhost:8080/chat');
  client.connect({}, function (frame) {
    client.subscribe("/topic/messages", function (message) {
      showMessage(JSON.parse(message.body).playerName);
    });
  })
}

function showMessage(value) {
  let newResponse = document.createElement('p');
  newResponse.appendChild(document.createTextNode(value))
  let response = document.getElementById('response');
  response.appendChild(newResponse);
}

function sendMessage() {
  let messageToSend = document.getElementById("messageToSend").value;
  client.send("/app/chat", {}, JSON.stringify({
    'playerName' : messageToSend,
    'weapon' : 'rock'
  }));
}