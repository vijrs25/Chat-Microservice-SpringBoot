document.addEventListener("DOMContentLoaded", initDashboard);

let selectedUserId = null;
let currentConversationId = null;
let currentUserId = null;

async function initDashboard() {
  try {
    const currentUser = await fetchCurrentUser();
    if (!currentUser) return;

    currentUserId = currentUser.id;
    setCurrentUserView(currentUser);

    connectWebSocket(function () {
      if (currentConversationId) {
        subscribeToConversation(currentConversationId, handleIncomingMessage);
      }
    });

    const users = await fetchUsers();
    if (!users) return;

    renderUsers(users, currentUserId, handleUserClick);
    bindMessageForm();
  } catch (error) {
    console.error("Dashboard initialization failed:", error);
  }
}

async function handleUserClick(user) {
  try {
    selectedUserId = user.id;
    setChatHeader(user.name, "Loading chat...");

    const conversation = await openConversation(selectedUserId);
    if (!conversation) return;

    currentConversationId = conversation.id;
    setChatHeader(user.name, "Chat opened");

    const messages = await fetchMessages(currentConversationId);
    if (!messages) return;

    renderMessages(messages, currentUserId);
    subscribeToConversation(currentConversationId, handleIncomingMessage);
  } catch (error) {
    console.error("Error opening chat:", error);
    setChatHeader(user.name, "Failed to open chat");
  }
}

function bindMessageForm() {
  const form = document.querySelector(".chat-composer");

  form.addEventListener("submit", async function (event) {
    event.preventDefault();

    if (!currentConversationId) {
      alert("Please select a chat first");
      return;
    }

    const messageText = getMessageInputValue();
    if (!messageText) return;

    try {
      const savedMessage = await sendMessage(currentConversationId, messageText);
      if (!savedMessage) return;

      appendMessage(savedMessage, currentUserId);
      clearMessageInput();
    } catch (error) {
      console.error("Error sending message:", error);
    }
  });
}

function handleIncomingMessage(savedMessage) {
  if (!savedMessage || savedMessage.senderId === currentUserId) {
    return;
  }

  appendMessage(savedMessage, currentUserId);
}
