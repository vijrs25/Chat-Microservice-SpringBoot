async function fetchCurrentUser() {
  return apiGetJson(APP_CONFIG.api.currentUser);
}

async function fetchUsers() {
  return apiGetJson(APP_CONFIG.api.users);
}

async function openConversation(otherUserId) {
  return apiGetJson(`${APP_CONFIG.api.openChat}?otherUserId=${otherUserId}`);
}

async function fetchMessages(conversationId) {
  return apiGetJson(`${APP_CONFIG.api.messages}/${conversationId}`);
}

async function sendMessage(conversationId, messageText) {
  return apiPostJson(APP_CONFIG.api.sendMessage, {
    conversationid: conversationId,
    messagetext: messageText,
  });
}
