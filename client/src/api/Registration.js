import { API_URLS } from "../config/api-routes.js";

export function register(nickname, email, password) {
  // make request to api with data for registration
  // return message from api
  let registrationInfo = {
    nickname: nickname,
    password: password,
    email: email,
  };

  const myHeaders = new Headers();
  myHeaders.append("Content-Type", "application/json");
  myHeaders.append("Access-Control-Allow-Origin", "*");
  const request = new Request(API_URLS.USER_REGISTER, {
    method: "POST",
    headers: myHeaders,
    body: JSON.stringify(registrationInfo),
  });

  fetch(request).then((data) => {
    console.log(data);
  });
}

register("user1", "user1", "user1");