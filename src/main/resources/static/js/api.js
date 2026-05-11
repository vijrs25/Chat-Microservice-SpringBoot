async function apiRequest(url, options = {}) {
  const response = await fetch(url, {
    ...options,
    headers: {
      ...getAuthHeaders(),
      ...(options.headers || {}),
    },
  });

  if (redirectToLoginIfUnauthorized(response)) {
    return null;
  }

  if (!response.ok) {
    throw new Error(`Request failed: ${response.status} ${response.statusText}`);
  }

  return response;
}

async function apiGetJson(url) {
  const response = await apiRequest(url, { method: "GET" });
  return response ? response.json() : null;
}

async function apiPostJson(url, body) {
  const response = await apiRequest(url, {
    method: "POST",
    body: JSON.stringify(body),
  });

  return response ? response.json() : null;
}
