import { API_URLS } from "../config/api-routes.js";
import { Token } from "./Token.js";

export function sendRequestToApi(body, endpoint, method) {
  let request = createRequest(body, endpoint, method);
  return fetch(request)
    .then(async (response) => {
      if (!response.ok) {
        let refreshResponse = await refresh();
        if (!refreshResponse.ok) {
          return refreshResponse;
        } else {
          return fetch(createRequest(body, endpoint, method));
        }
      } else {
        return response;
      }
    })
    .catch((error) => {
      console.log(error);
    });
}

export async function refresh() {
  const response = await fetch(API_URLS.ACCOUNT_REFRESH_TOKEN, {
    credentials: "include",
    method: "POST",
  });
  if (response.ok) {
    const body = await response.json();
    localStorage.setItem("token", body.token);
  }
  return response;
}

export async function login(email, password) {
  let loginInfo = {
    email: email,
    password: password,
  };

  const myHeaders = new Headers();
  myHeaders.append("Content-Type", "application/json");

  const request = new Request(API_URLS.ACCOUNT_LOGIN, {
    credentials: "include",
    method: "POST",
    headers: myHeaders,
    body: JSON.stringify(loginInfo),
  });
  const response = await fetch(request);
  let object = await response.json();
  localStorage.setItem("token", new Token(object.token));
  return object;
}

function createRequest(body, endpoint, method) {
  const headers = new Headers();
  const token = localStorage.getItem("token");
  headers.append("Content-Type", "application/json");
  headers.append("Authorization", `Bearer ${token}`);
  const request = new Request(endpoint, {
    credentials: "include",
    method: method,
    headers: headers,
    body: method == "GET" ? null : JSON.stringify(body),
  });
  return request;
}
