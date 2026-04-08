document.addEventListener("DOMContentLoaded", init);

let selectedUserId = null;
let currentConversationId = null;
let currentUserId = null;

function init() {
  loadCurrentUser();
  loadAllUsers();
}

async function loadCurrentUser() {
  try {
    const response = await fetch("/api/me");

    if (!response.ok) {
      throw new Error("Failed to fetch current user");
    }

    const data = await response.json();
    currentUserId = data.id;

    console.log("Current user data:", data);

    document.getElementById("username").innerText =
      data.name ?? data.username ?? "No Name";
    document.getElementById("email").innerText = data.email ?? "No Email";
    document.getElementById("phone").innerText =
      data.phoneNumber ?? data.phone ?? "No Phone";
  } catch (error) {
    console.error("Error loading current user:", error);
  }
}

async function loadAllUsers() {
  try {
    const response = await fetch("/api/users");

    if (!response.ok) {
      throw new Error("Failed to load users");
    }

    const users = await response.json();
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

          console.log("openchat call " + user);
          li.classList.add("active");
          openChat(user.id, user.name);
        });

        contactList.appendChild(li);
      });
  } catch (error) {
    console.error("Error loading users:", error);
  }
}

async function openChat(userId, userName) {
  try {
    selectedUserId = userId;

    document.querySelector(".chat-header h3").innerText = userName;
    document.querySelector(".chat-header p").innerText = "Loading chat...";

    const response = await fetch(`/api/chats/open?otherUserId=${userId}`);

    if (!response.ok) {
      throw new Error("Failed to open chat");
    }

    const conversation = await response.json();
    console.log("Inside openchat call " + conversation);
    currentConversationId = conversation.id;
    console.log("currentConversationId =", currentConversationId);
    document.querySelector(".chat-header p").innerText = "Chat opened";

    await loadMessages(currentConversationId);
  } catch (error) {
    console.error("Error opening chat:", error);
  }
}

async function loadMessages(conversationId) {
  try {
    const response = await fetch(
      `/api/messages/conversation/${conversationId}`,
    );

    if (!response.ok) {
      throw new Error("Failed to load messages");
    }

    const messages = await response.json();
    console.log("console Load massages " + messages);
    const chatBody = document.querySelector(".chat-messages");
    chatBody.innerHTML = "";

    messages.forEach((msg) => {
      const div = document.createElement("div");
      console.log("msg sender id"+msg.senderId+ "cuurent user "+currentUserId)
      
      const isMine = msg.SenderId === currentUserId;
      div.className = isMine ? "message-row sent" : "message-row received";
      div.innerText = msg.massageText;

      chatBody.appendChild(div);
    });

    chatBody.scrollTop = chatBody.scrollHeight;
  } catch (error) {
    console.error("Error loading messages:", error);
  }
}

const form = document.querySelector(".chat-composer");
const input = document.querySelector(".chat-input");

form.addEventListener("submit", async function (e) {
    e.preventDefault();

    const messageText = input.value.trim();
    if (!messageText) return;
    console.log("currentConversationId =", currentConversationId);
    try {
        const response = await fetch("/api/messages/send", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                conversationid: currentConversationId,
                messagetext: messageText
            })
        });

        if (!response.ok) {
            throw new Error("Failed to send message");
        }

        const savedMessage = await response.json();

        appendMessage(savedMessage);

        input.value = "";

    } catch (error) {
        console.error("Error sending message:", error);
    }
});

async function appendMessage(savedMesage){
const chatMessage = document.querySelector(".chat-messages");

const div = document.createElement("div");
 if(savedMesage.senderid == currentUserId){
   div.classList.add("message-row", "sent");
 }else{
  div.classList.add("message-row", "recieved");
 }

   div.innerHTML = `
        <div class="message-bubble sent">
            ${savedMesage.massageText}
        </div>
    `;

    chatMessage.appendChild(div);
    scrollToBottom();
}

function scrollToBottom() {
    const chatContainer = document.querySelector(".chat-messages");

    chatContainer.scrollTop = chatContainer.scrollHeight;
}