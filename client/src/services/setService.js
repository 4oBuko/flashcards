import api from "../axios/axios";
import { ENDPOINTS } from "@/config/api-routes";

class SetService {
  getById(id) {
    return api.get(ENDPOINTS.SET_GET.replace(":id", id)).then((response) => {
      if (response.data) {
        return response.data;
      }
    });
  }

  createNew(
    name,
    questionLanguage,
    answerLanguage,
    description,
    cards,
    isPublic
  ) {
    return api
      .post(ENDPOINTS.SET_CREATE, {
        name: name,
        questionLanguageId: questionLanguage.id,
        answerLanguageId: answerLanguage.id,
        description: description,
        flashcards: cards,
        isPublic: isPublic,
      })
      .then((response) => response);
  }
}

export default new SetService();
