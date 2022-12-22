export class Flashcard {
  id;
  setId;
  question;
  answer;

  constructor(id, setId, question, answer) {
    this.id = id;
    this.setId = setId;
    this.question = question;
    this.answer = answer;
  }
}
