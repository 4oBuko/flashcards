import api from "./axios";
import { API_URLS } from "@/config/api-routes";

class SetService {
  getById(id) {
    return api.get(API_URLS.SET_GET.replace(":id", id)).then((response) => {
      if (response.data) {
        return response.data;
      }
    });
  }
}

export default new SetService();
