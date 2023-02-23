import { API_URLS } from "@/config/api-routes";
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
  const token = localStorage.getItem("token");
  headers.append("Content-Type", "application/json");
  headers.append("Authorization", `Bearer ${token}`);
  return new Request(endpoint, {
    credentials: "include",
    method: method,
    headers: headers,
    body: method === "GET" ? null : JSON.stringify(body),
  });
}

// user endpoints

export async function refresh() {
  const response = await fetch(API_URLS.REFRESH_TOKEN, {
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

  const request = new Request(API_URLS.LOGIN, {
    credentials: "include",
    method: "POST",
    headers: myHeaders,
    body: JSON.stringify(loginInfo),
  });
  const response = await fetch(request);

  if (response.ok) {
    let body = await response.json();
    localStorage.setItem("token", body.token);
    return body;
  } else {
    return new ApiError(response.status, "Wrong login data");
  }
}

export async function registerNewUser(nickname, email, password) {
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
  return await response.json();
}

export function getUserById(id) {
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

export function getUserSets(id) {
  return sendRequestToApi(
    null,
    API_URLS.USER_SETS_GET.replace(":id", id),
    "GET"
  );
}

export function getUserTags(id) {
  return sendRequestToApi(
    null,
    API_URLS.USER_TAGS_GET.replace(":id", id),
    "GET"
  );
}

export function changeUserEmail(id) {
  return sendRequestToApi(
    null,
    API_URLS.USER_CHANGE_EMAIL.replace(":id", id),
    "PUT"
  );
}

export function changeUserNickname(id) {
  return sendRequestToApi(
    null,
    API_URLS.USER_CHANGE_NICKNAME.replace(":id", id),
    "PUT"
  );
}

export function changeUserPassword() {
  return sendRequestToApi(
    null,
    API_URLS.USER_CHANGE_PASSWORD.replace(":id", id),
    "PUT"
  );
}

// Flashcards set endpoints

export function getSetById(id) {
  return sendRequestToApi(null, API_URLS.SET_GET.replace(":id", id), "GET");
}

export function createNewSet(jsonSet) {
  return sendRequestToApi(jsonSet, API_URLS.SET_CREATE, "POST");
}

export function updateSet(jsonSet) {
  return sendRequestToApi(jsonSet, API_URLS.SET_UPDATE, "POST");
}

export function deleteSetById(id) {
  return sendRequestToApi(
    null,
    API_URLS.SET_DELETE.replace(":id", id),
    "DELETE"
  );
}

// Tags endpoints

export function getTagById(id) {
  return sendRequestToApi(null, API_URLS.TAG_GET.replace(":id", id), "GET");
}

export function createNewTag(jsonSet) {
  return sendRequestToApi(jsonSet, API_URLS.TAG_CREATE, "POST");
}

export function updateTag(jsonSet) {
  return sendRequestToApi(jsonSet, API_URLS.TAG_UPDATE, "POST");
}

export function deleteTagById(id) {
  return sendRequestToApi(
    null,
    API_URLS.TAG_DELETE.replace(":id", id),
    "DELETE"
  );
}

// languages endpoint

export function getLanguages() {
  return sendRequestToApi(null, API_URLS.LANGUAGES_GET, "GET");
}

// colors endpoint

export function getColors() {
  return sendRequestToApi(null, API_URLS.COLORS_GET, "GET");
}
