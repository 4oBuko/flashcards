import instance from "@/axios/axios";
import { ENDPOINTS } from "@/config/api-routes";

class TagService {
  createNewTag(name, sets, isPublic, color) {
    instance
      .post(ENDPOINTS.TAG_CREATE, {
        name: name,
        sets: sets.map((set) => set.id),
        isPublic: isPublic,
        colorId: color.id,
      })
      .then((response) => {
        console.log(response.data);
      });
  }
}

export default new TagService();
