export const API_URL = "http://localhost:8080";
// todo: how to set api change api url for dev and production
export const API_URLS = {
  LOGIN: `${API_URL}/auth/login`,
  REFRESH_TOKEN: `${API_URL}/auth/refresh`,
  LOGOUT: `${API_URL}/auth/logout`,

  CHECK_NICKNAME: `${API_URL}/users/nickname/:nickname`,

  COLORS_GET: `${API_URL}/colors`,

  LANGUAGES_GET: `${API_URL}/languages`,

  USER_REGISTER: `${API_URL}/register`,
  USER_GET: `${API_URL}/users/:id`,
  USER_CHANGE_EMAIL: `${API_URL}/users/:id/email`,
  USER_CHANGE_NICKNAME: `${API_URL}/users/:id/nickname`,
  USER_CHANGE_PASSWORD: `${API_URL}/users/:id/password`,
  USER_SETS_GET: `${API_URL}/users/:id/sets`,
  USER_TAGS_GET: `${API_URL}/users/:id/tags`,

  TAG_GET: `${API_URL}/tags/:id`,
  TAG_CREATE: `${API_URL}/tags/`,
  TAG_UPDATE: `${API_URL}/tags/`,
  TAG_DELETE: `${API_URL}/tags/:id`,

  SET_GET: `${API_URL}/sets/:id`,
  SET_CREATE: `${API_URL}/sets/`,
  SET_UPDATE: `${API_URL}/sets/`,
  SET_DELETE: `${API_URL}/sets/:id`,
};
