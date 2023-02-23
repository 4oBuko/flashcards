export class ApiError {
    code;
    message;

    constructor(code, message) {
        this.code = code;
        this.message = message;
    }
}