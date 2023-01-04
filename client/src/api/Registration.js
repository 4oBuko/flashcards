import { API_URLS } from "../config/api-routes.js";

export async function register(nickname, email, password) {
  let registrationInfo = {
    nickname: nickname,
    password: password,
    email: email,
  };

  const myHeaders = new Headers();
  myHeaders.append("Content-Type", "application/json");
  
  const request = new Request(API_URLS.USER_REGISTER, {
    method: "POST",
    headers: myHeaders,
    body: JSON.stringify(registrationInfo),
  });

  const response = await fetch(request);

  return response.json();//todo why I get a promise
}
