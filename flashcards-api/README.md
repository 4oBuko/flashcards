# Users
# Authentication /auth
## POST /login
### request data
email and password
### response
access token and secure http only cookie with refresh token
## POST /refresh
### response
access token and secure http only cookie with refresh token
# Registration /register
## POST
## GET /confirm

# Users /users
## GET /{id}
## PUT /{id}/email
## PUT /{id}/nickname
## PUT /{id}/password
## GET /{id}/tags
## GET /{id}/sets
## GET /nickname/{nickname}
# Sets /sets
## GET /{id}
## POST
## PUT
## DELETE /{id}
# Tags /tags
## GET /{id}
## POST
## PUT
## DELETE /{id}
# Colors /colors
## GET
### response
List of available colors for tags
# Languages /languages
## GET
### response
List of available languages for creating sets

