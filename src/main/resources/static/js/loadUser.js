document.addEventListener("DOMContentLoaded", init);

function init() {
    loadCurrentUser();
    loadAllUsers();
}

async function loadCurrentUser() {
    try {
        const response = await fetch('/api/me');

        if (!response.ok) {
            throw new Error("Failed to fetch current user");
        }

        const data = await response.json();
        console.log("Current user data:", data);

        document.getElementById("username").innerText = data.name ?? data.username ?? "No Name";
        document.getElementById("email").innerText = data.email ?? "No Email";
        document.getElementById("phone").innerText = data.phoneNumber ?? data.phone ?? "No Phone";

    } catch (error) {
        console.error("Error loading current user:", error);
    }
}

async function loadAllUsers() {
    try {
        const response = await fetch('/api/users');

        if (!response.ok) {
            throw new Error("Failed to fetch users");
        }

        const users = await response.json();
        console.log("All users:", users);

        const contactList = document.getElementById("contactlist");
        contactList.innerHTML = "";

        users.forEach(user => {
            const li = document.createElement("li");
            li.classList.add("contact-item");

            const firstLetter = (user.name ?? user.username ?? "U").charAt(0).toUpperCase();

            li.innerHTML = `
                <div class="contact-avatar">${firstLetter}</div>
                <div class="contact-details">
                    <p class="contact-name">${user.name ?? user.username ?? "Unknown"}</p>
                    <p class="contact-last-message">Start chatting...</p>
                </div>
            `;

            contactList.appendChild(li);
        });

    } catch (error) {
        console.error("Error loading users:", error);
    }
}