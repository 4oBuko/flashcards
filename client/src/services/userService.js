import instance from "@/axios/axios";
import { API_URL, ENDPOINTS } from "@/config/api-routes";

class UserService {
  isNicknameAvailable(nickname) {
    return instance
      .get(ENDPOINTS.CHECK_NICKNAME.replace(":nickname", nickname))
      .then((response) => {
        return response.data.isAvailable;
      });
  }
}

export default new UserService();
