const APP_CONFIG = {
  api: {
    currentUser: "/api/me",
    users: "/api/users",
    openChat: "/api/chats/open",
    messages: "/api/messages/conversation",
    sendMessage: "/api/messages/send",
  },
  websocket: {
    endpoint: "/ws-connect",
    conversationTopic: "/topic/conversation/",
  },
};
