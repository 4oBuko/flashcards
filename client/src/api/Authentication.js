import { API_URLS } from "../config/api-routes.js";

export async function login(email, password) {
  let loginInfo = {
    email: email,
    password: password,
  };

  const myHeaders = new Headers();
  myHeaders.append("Content-Type", "application/json");

  const request = new Request(API_URLS.ACCOUNT_LOGIN, {
    method: "POST",
    headers: myHeaders,
    body: JSON.stringify(loginInfo),
  });

  const response = await fetch(request);
  let object = await response.json();
  return object;
  // todo try to login user with email and password
  // if authentication successful get access token and
  // put refresh token in http only cookies
  //
}

export async function refresh() {
  // send request to get new access token
}
