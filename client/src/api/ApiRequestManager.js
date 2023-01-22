import { API_URLS } from "../config/api-routes.js";
import { Token } from "./Token.js";

export async function sendRequestToApi(body, endpoint, method) {
  let request = createRequest(body, endpoint, method);
  return fetch(request)
    .then((response) => {
      if (response.status >= 400) {
        console.log("response", response);
        //if request failed //TODO change
        let refreshResponse = refresh();
        if (refreshResponse.status >= 400) {
          console.log("refresh failed");
          return refreshResponse;
        } else {
          console.log("token refreshed. Try to do request again");
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
}

export function testGetId() {
  sendRequestToApi(null, API_URLS.SET_GET.replace(":id", 6), "GET")
    .then((r) => r.json())
    .then((body) => {
      console.log(body.id);
    });
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
