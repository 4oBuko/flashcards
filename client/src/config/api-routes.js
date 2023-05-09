export const API_URL = import.meta.env.VITE_API_DOMAIN;
export const ENDPOINTS = {
  LOGIN: `/auth/login`,
  REFRESH_TOKEN: `/auth/refresh`,
  LOGOUT: `/auth/logout`,

  CHECK_NICKNAME: `/nicknames/:nickname`,

  COLORS_GET: `/colors`,

  LANGUAGES_GET: `/languages`,

  USER_REGISTER: `/register`,
  USER_GET: `/users/id/:id`,
  USER_GET_BY_NICKNAME: "/users/:nickname",
  USER_CHANGE_EMAIL: `/users/:id/email`,
  USER_CHANGE_NICKNAME: `/users/:id/nickname`,
  USER_CHANGE_PASSWORD: `/users/:id/password`,
  USER_SETS_GET: `/users/:id/sets`,
  USER_TAGS_GET: `/users/:id/tags`,

  TAG_GET: `/tags/:id`,
  OWN_TAGS_GET: "/tags",
  TAG_CREATE: `/tags/`,
  TAG_UPDATE: `/tags/`,
  TAG_DELETE: `/tags/:id`,

  SET_GET: `/sets/:id`,
  OWN_SETS_GET: "/sets",
  SET_CREATE: `/sets/`,
  SET_UPDATE: `/sets/`,
  SET_DELETE: `/sets/:id`,
};
