function setCurrentUserView(user) {
  document.getElementById("username").innerText = user.name ?? user.username ?? "No Name";
  document.getElementById("email").innerText = user.email ?? "No Email";
  document.getElementById("phone").innerText = user.phoneNumber ?? user.phone ?? "No Phone";
}

function renderUsers(users, currentUserId, onUserClick) {
  const contactList = document.getElementById("contactlist");
  contactList.innerHTML = "";

  users
    .filter((user) => user.id !== currentUserId)
    .forEach((user) => {
      const li = document.createElement("li");
      li.classList.add("contact-item");

      const firstLetter = user.name?.charAt(0)?.toUpperCase() || "?";

      li.innerHTML = `
        <div class="contact-avatar">${firstLetter}</div>
        <div class="contact-details">
            <p class="contact-name">${user.name}</p>
            <p class="contact-last-message">Click to chat</p>
        </div>
      `;

      li.addEventListener("click", () => {
        document
          .querySelectorAll(".contact-item")
          .forEach((item) => item.classList.remove("active"));

        li.classList.add("active");
        onUserClick(user);
      });

      contactList.appendChild(li);
    });
}

function setChatHeader(userName, status) {
  document.querySelector(".chat-header h3").innerText = userName;
  document.querySelector(".chat-header p").innerText = status;
}

function renderMessages(messages, currentUserId) {
  const chatBody = document.querySelector(".chat-messages");
  chatBody.innerHTML = "";

  messages.forEach((message) => appendMessage(message, currentUserId));
  scrollToBottom();
}

function appendMessage(message, currentUserId) {
  const chatBody = document.querySelector(".chat-messages");
  const isMine = message.senderId === currentUserId;

  const row = document.createElement("div");
  row.className = isMine ? "message-row sent" : "message-row received";

  const bubble = document.createElement("div");
  bubble.className = "message-bubble";
  bubble.innerText = message.massageText ?? "";

  row.appendChild(bubble);
  chatBody.appendChild(row);
  scrollToBottom();
}

function clearMessageInput() {
  document.querySelector(".chat-input").value = "";
}

function getMessageInputValue() {
  return document.querySelector(".chat-input").value.trim();
}

function scrollToBottom() {
  const chatContainer = document.querySelector(".chat-messages");
  chatContainer.scrollTop = chatContainer.scrollHeight;
}
