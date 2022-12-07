#!/bin/sh
P=$1/keys

#create directory for keys
mkdir -p $P

# create rsa key pair
openssl genrsa -out $P/keypair.pem 2048

# extract public key
openssl rsa -in $P/keypair.pem -pubout -out $P/public.pem

# create private key in PKCS#8 format
openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in $P/keypair.pem -out $P/private.pem
# I can generate keys as system variables and remove files