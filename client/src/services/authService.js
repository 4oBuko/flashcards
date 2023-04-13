import { API_URLS } from "@/config/api-routes";
import { User } from "@/entities/User";
import { ApiError } from "@/entities/Error";
import api from "./axios";
import TokenService from "@/services/TokenService";

export async function refresh() {
  const response = await fetch(API_URLS.REFRESH_TOKEN, {
    credentials: "include",
    method: "POST",
  });
  if (response.ok) {
    const body = await response.json();
    let user = new User();
    user.token = body;
    localStorage.setItem("token", JSON.stringify(user));
    return body;
  }
}

export function login(email, password) {
  api
    .post(API_URLS.LOGIN, {
      email: email,
      password: password,
    })
    .then((response) => {
      if (response.data.token) {
        TokenService.setToken(response.data.token);
      }
    })
    .catch((err) => {
      console.log(err);
    });
}
