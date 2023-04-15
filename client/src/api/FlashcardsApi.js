import { ENDPOINTS } from "@/config/api-routes";
import { ApiError } from "@/entities/Error";
import { User } from "@/entities/User";

function sendRequestToApi(body, endpoint, method) {
  let request = createRequest(body, endpoint, method);
  return fetch(request)
    .then(async (response) => {
      if (response.status === 401) {
        let refreshResponse = await refresh();
        if (!refreshResponse.ok) {
          return new ApiError(response.status, response.body);
        } else {
          return fetch(createRequest(body, endpoint, method))
            .then((response) => response.json())
            .catch(); //TODO add error handling
        }
      } else if (!response.ok) {
        return new ApiError(response.status, response.body);
      } else {
        return response.json();
      }
    })
    .catch((error) => {
      console.log(error);
    });
}

function createRequest(body, endpoint, method) {
  const headers = new Headers();
  const user = JSON.parse(localStorage.getItem("user"));
  headers.append("Content-Type", "application/json");
  headers.append("Authorization", `Bearer ${user.token}`);
  return new Request(endpoint, {
    credentials: "include",
    method: method,
    headers: headers,
    body: method === "GET" ? null : JSON.stringify(body),
  });
}

// user endpoints

export function getUserSets(id) {
  return sendRequestToApi(
    null,
    ENDPOINTS.USER_SETS_GET.replace(":id", id),
    "GET"
  );
}

export function getUserTags(id) {
  return sendRequestToApi(
    null,
    ENDPOINTS.USER_TAGS_GET.replace(":id", id),
    "GET"
  );
}

export function changeUserEmail(id) {
  return sendRequestToApi(
    null,
    ENDPOINTS.USER_CHANGE_EMAIL.replace(":id", id),
    "PUT"
  );
}

export function changeUserNickname(id) {
  return sendRequestToApi(
    null,
    ENDPOINTS.USER_CHANGE_NICKNAME.replace(":id", id),
    "PUT"
  );
}

export function changeUserPassword() {
  return sendRequestToApi(
    null,
    ENDPOINTS.USER_CHANGE_PASSWORD.replace(":id", id),
    "PUT"
  );
}

// Flashcards set endpoints

export function getSetById(id) {
  return sendRequestToApi(null, ENDPOINTS.SET_GET.replace(":id", id), "GET");
}

export function createNewSet(jsonSet) {
  return sendRequestToApi(jsonSet, ENDPOINTS.SET_CREATE, "POST");
}

export function updateSet(jsonSet) {
  return sendRequestToApi(jsonSet, ENDPOINTS.SET_UPDATE, "POST");
}

export function deleteSetById(id) {
  return sendRequestToApi(
    null,
    ENDPOINTS.SET_DELETE.replace(":id", id),
    "DELETE"
  );
}

// Tags endpoints

export function getTagById(id) {
  return sendRequestToApi(null, ENDPOINTS.TAG_GET.replace(":id", id), "GET");
}

export function createNewTag(jsonSet) {
  return sendRequestToApi(jsonSet, ENDPOINTS.TAG_CREATE, "POST");
}

export function updateTag(jsonSet) {
  return sendRequestToApi(jsonSet, ENDPOINTS.TAG_UPDATE, "POST");
}

export function deleteTagById(id) {
  return sendRequestToApi(
    null,
    ENDPOINTS.TAG_DELETE.replace(":id", id),
    "DELETE"
  );
}

// languages endpoint

export function getLanguages() {
  return sendRequestToApi(null, ENDPOINTS.LANGUAGES_GET, "GET");
}

// colors endpoint

export function getColors() {
  return sendRequestToApi(null, ENDPOINTS.COLORS_GET, "GET");
}
