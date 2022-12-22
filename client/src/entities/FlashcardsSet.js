export class FlashcardsSet {
  id;
  userId;
  name;
  description;
  isPublic;
  tags;
  flashcards;
  questionLanguageId;
  answerLanguageId;

  constructor(
    id,
    userId,
    name,
    description,
    isPublic,
    tags,
    flashcards,
    questionLanguageId,
    answerLanguageId
  ) {
    this.id = id;
    this.userId = userId;
    this.name = name;
    this.description = description;
    this.isPublic = isPublic;
    this.tags = tags;
    this.flashcards = flashcards;
    this.questionLanguageId = questionLanguageId;
    this.answerLanguageId = answerLanguageId;
  }
}
