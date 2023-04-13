export class User {
  id;
  nickname;
  email;
  registrationDate;
  isConfirmed;
  token;

  constructor(id, nickname, email, registrationDate, isConfirmed, token) {
    this.id = id;
    this.nickname = nickname;
    this.email = email;
    this.registrationDate = registrationDate;
    this.isConfirmed = isConfirmed;
    this.token = token;
  }
}
