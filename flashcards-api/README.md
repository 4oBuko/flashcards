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
register new user
### request body
nickname, password and email. Confirmation letter 
will be send on email
### response
## GET /confirm?token={token}
account confirmation

# Users /users
## GET /{id}
### response
user by id
## PUT /{id}/email
### request body
new email
### response
user with updated email
## PUT /{id}/nickname
### request body
new nickname
### response
user with updated nickname
## PUT /{id}/password
### request body
new password
### response
user with updated password
## GET /{id}/tags
### response
list of tags of the user by id
## GET /{id}/sets
### response
list of tags of the user by id
## GET /nickname/{nickname}
### response
returns is nickname available 

# Sets /sets
## GET /{id}
get set by id
### response
set by id
## POST
create a new set
### request body
new set
### response
<!-- todo -->
## PUT 
update set
### request body
updated set
### response
updated set
## DELETE /{id}
delee set by id

# Tags /tags
## GET /{id}
### response
tag by id
## POST
### request body
new tag
### response
<!-- todo -->
## PUT
### request body
updated tag
### response
updated tag
## DELETE /{id}
delete tag by id

# Colors /colors
## GET
### response
List of available colors for tags

# Languages /languages
## GET
### response
List of available languages for creating sets

