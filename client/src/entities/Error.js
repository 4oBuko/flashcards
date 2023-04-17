export class ApiError {
  status;
  message;

  constructor(status, message) {
    this.status = status;
    this.message = message;
  }
}
