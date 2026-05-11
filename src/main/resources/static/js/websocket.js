let stompClient = null;
let activeConversationSubscription = null;

function connectWebSocket(onConnected) {
  const socket = new SockJS(APP_CONFIG.websocket.endpoint);
  stompClient = Stomp.over(socket);

  stompClient.connect(
    {},
    function () {
      console.log("WebSocket connected");
      if (typeof onConnected === "function") {
        onConnected();
      }
    },
    function (error) {
      console.error("WebSocket connection error:", error);
    }
  );
}

function subscribeToConversation(conversationId, onMessageReceived) {
  if (!stompClient || !stompClient.connected) {
    console.warn("WebSocket not connected yet");
    return;
  }

  if (activeConversationSubscription) {
    activeConversationSubscription.unsubscribe();
  }

  const topic = APP_CONFIG.websocket.conversationTopic + conversationId;

  activeConversationSubscription = stompClient.subscribe(topic, function (message) {
    const savedMessage = JSON.parse(message.body);
    onMessageReceived(savedMessage);
  });

  console.log("Subscribed to " + topic);
}
