class TokenService {
  getToken() {
    return localStorage.getItem("token");
  }

  setToken(token) {
    localStorage.setItem("token", token);
  }

  isTokenValid() {
    const payload = this.getTokenPayload();
    return Date.now() / 1000 < payload.exp;
  }

  getTokenPayload() {
    const token = localStorage.getItem("token");
    const base64Payload = token.split(".")[1];
    return JSON.parse(atob(base64Payload));
  }
}

export default new TokenService();
