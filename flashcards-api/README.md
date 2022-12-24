# Authentication
## login

Endpoint: `/auth/login`

Method: `POST`

Request body: email, password

Response: access token and http only secure cookie with refresh token

## refresh access token

Endpoint: `/auth/refresh`

Method: `POST`

Response: access token and http only secure cookie with refresh token

*To refresh token you need http only cookie with refresh token!*

# Registration

## Register new user

Endpoint: /register

Method: POST

Request body: email, nickname, password

Response: message with registration status

## GET /confirm?token={token}

account confirmation

# Users

## get user by id

Endpoint: ` /users/{id}`

Method: `GET`

Response: user by id

## update user's email

Endpoint: ` /users/{id}/email`

Method: `PUT`

Request body: `{"newEmail" : "emai"}`

Response: user with updated email

## update user's nickname

Endpoint: ` /users/{id}/nickname`

Method: `PUT`

Request body: `{"newNickname" : "nickname"}`

Response: user with updated nickname

## update user's password

Endpoint: ` /users/{id}/password`

Method: `PUT`

Request body: `{"newPassword" : "password"}`

Response: updated user

## get user's tags

Endpoint: ` /users/{userId}/tags`

Method: `GET`

Response: list of user's tags

## get user's sets

Endpoint: ` /users/{userId}/tags`

Method: `GET`

Response list of user's tags

## is nickname available

Endpoint: ` /users/nickname/{nickname}`

Method: `GET`

Response: true if nickname available or false if not

# Sets /sets

## get set by id
Endpoint: `/sets/{id}`

Method: `GET`

Response: set by id

## add new set
Endpoint: `/sets`

Method: `POST`

Request body: new set

Response: saved set

## update existed set
Endpoint: `/sets`

Method: `PUT`

Request body: updated set

Response: updated set

## delete set
Endpoint: `/sets/{id}`

Method: `DELETE`

Response: result message

# Tags 

## get tag by id
Endpoint: `/tags/{id}`

Method: `GET`

Response: tag by id


## add new tag
Endpoint: `/tags`

Method: `POST`

Request body: new tag

Response: saved tag

## update existed tag
Endpoint: `/tags`

Method: `PUT`

Request body: updated tag

Response: updated tag


## delete tag by id
Endpoint: `/tags/{id}`

Method: `DELETE`

Response: result message

# Colors /colors

## Get all tag colors
Endpoint: `/colors`

Method: `GET`

Response: list of tag colors

# Languages

## get supported languages
Endpoint: `/languages`

Method: `GET`

Response: list of supported languages
