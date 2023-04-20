# flashcards

# How to run
## Install OpenSSL
You need OpenSSL on your machi
ne to run script for generating SSL keys. 
### MacOS 
you need to install it manually.
### Linux 
in most cases OpenSSL is already installed by default.
### Windows 
the easiest way is by using Git Shell. Git Shell includes OpenSSL, so you don't need to install it on your computer. 
## Run the script
run the following command in the terminal in directory of the project
`./genkeys.sh flashcards-api/src/main/properties`

## Run the database
Run [docker-compose.yaml](docker-compose.yaml) to create db container

## Required env variables
The API of the app uses gmail for sending email letter. 
You have to put your username and password to send mails
set these env variables for that
- MAIL_USERNAME
- MAIL_PASSWORD
If you want to disable mail server open application.yml and 
set email.verification.enabled property to false

Also, you need FRONT_END_URL variable with url to the client
for API.