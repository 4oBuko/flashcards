import { API_URLS } from "@/config/api-routes";
import api from "./axios";

export function register(nickname, email, password) {
  let registrationInfo = {
    nickname: nickname,
    password: password,
    email: email,
  };

  return api
    .post(API_URLS.USER_REGISTER, registrationInfo)
    .then((response) => {
      if (response.data.message) {
        return response.data.message;
      }
    })
    .catch((err) => {
      console.log(err);
    });
}
