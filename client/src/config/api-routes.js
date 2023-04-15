export const API_URL = "http://localhost:8080";
// todo: how to set api change api url for dev and production
export const ENDPOINTS = {
  LOGIN: `/auth/login`,
  REFRESH_TOKEN: `/auth/refresh`,
  LOGOUT: `/auth/logout`,

  CHECK_NICKNAME: `/users/nickname/:nickname`,

  COLORS_GET: `/colors`,

  LANGUAGES_GET: `/languages`,

  USER_REGISTER: `/register`,
  USER_GET: `/users/:id`,
  USER_CHANGE_EMAIL: `/users/:id/email`,
  USER_CHANGE_NICKNAME: `/users/:id/nickname`,
  USER_CHANGE_PASSWORD: `/users/:id/password`,
  USER_SETS_GET: `/users/:id/sets`,
  USER_TAGS_GET: `/users/:id/tags`,

  TAG_GET: `/tags/:id`,
  TAG_CREATE: `/tags/`,
  TAG_UPDATE: `/tags/`,
  TAG_DELETE: `/tags/:id`,

  SET_GET: `/sets/:id`,
  SET_CREATE: `/sets/`,
  SET_UPDATE: `/sets/`,
  SET_DELETE: `/sets/:id`,
};
