export class User {
  id;
  nickname;
  email;
  registrationDate;
  isConfirmed;

  constructor(id, nickname, email, registrationDate, isConfirmed) {
    this.id = id;
    this.nickname = nickname;
    this.email = email;
    this.registrationDate = registrationDate;
    this.isConfirmed = isConfirmed;
  }
}
