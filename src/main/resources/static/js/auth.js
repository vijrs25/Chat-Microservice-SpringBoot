function getAuthHeaders() {
  const token = localStorage.getItem("token");
  return {
    "Content-Type": "application/json",
    Authorization: "Bearer " + token,
  };
}

function redirectToLoginIfUnauthorized(response) {
  if (response.status === 401 || response.status === 403) {
    localStorage.clear();
    window.location.href = "/login.html";
    return true;
  }
  return false;
}
