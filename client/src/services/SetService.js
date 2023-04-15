import api from "./axios";
import { ENDPOINTS } from "@/config/api-routes";

class SetService {
  getById(id) {
    return api.get(ENDPOINTS.SET_GET.replace(":id", id)).then((response) => {
      if (response.data) {
        return response.data;
      }
    });
  }
}

export default new SetService();
