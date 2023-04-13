import { API_URLS } from "@/config/api-routes";
import { User } from "@/entities/User";

export function getById(id) {
  return sendRequestToApi(
    null,
    API_URLS.USER_GET.replace(":id", id),
    "GET"
  ).then((response) => {
    return new User(
      response.id,
      response.nickname,
      response.email,
      response.registrationDate,
      response.isConfirmed
    );
  });
}

export function getByNickname(nickname) {}
