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
  USER_CHANGE_EMAIL: `/users/email`,
  USER_CHANGE_NICKNAME: `/users/nickname`,
  USER_CHANGE_PASSWORD: `/users/password`,
  USER_SETS_GET: `/users/:id/sets`,
  USER_TAGS_GET: `/users/:id/tags`,

  TAG_GET: `/tags/:id`,
  OWN_TAGS_GET: "/tags",
  TAG_CREATE: `/tags/`,
  TAG_UPDATE: `/tags/`,
  TAG_DELETE: `/tags/:id`,
  TAG_LIKE: `/tags/:id/like`,
  GET_LIKED_TAGS: `/tags/likes`,

  SET_GET: `/sets/:id`,
  OWN_SETS_GET: "/sets",
  SET_CREATE: `/sets/`,
  SET_UPDATE: `/sets/`,
  SET_DELETE: `/sets/:id`,
  SET_LIKE: `/sets/:id/like`,
  GET_LIKED_SETS: `/sets/likes`,
};

export const PUBLIC_ENDPOINTS = [
  ENDPOINTS.USER_REGISTER,
  ENDPOINTS.LOGIN,
  ENDPOINTS.CHECK_NICKNAME.substring(
    0,
    ENDPOINTS.CHECK_NICKNAME.lastIndexOf("/")
  ),
];
