# Spring boot app for generating JWT tokens
***
# Set up App
***
## Cassandra set up
To connect to cassandra nodes define the properties in yml property file like so
in application.yml file
```yaml
    cassandra:
      keyspace-name: authorization_keyspace
      contact-points: localhost
      local-datacenter: datacenter1
```
To call the endpoints you will need a user registered in the database that you can
do using the following CQL statement. To get the Bcrype encoded string you can
use http://localhost:8081/bcrypt/encode endpoint that permits all requests without
authentication
```roomsql
INSERT INTO user_details (username, roles, enabled, password) VALUES('<username>', 'ROLE_ADMIN', true, '<BcryptEncodedPassword>');
```
## Generating Asymmetric keys for JWT token generation and authentication
To generate the keys you will need openssl(comes with git) and keytool(comes with java jdk)
run the following commands after replacing the <> placeholders with their
names
```shell
keytool -genkeypair -alias <keyAliasname> -keyalg <generationAlgorithm> -keypass <password> -keystore <privateKeyFilename>.jks -storepass <keyStorePassword>
keytool -list -rfc --keystore <privateKeyFilename>.jks | openssl x509 -inform pem -pubkey
```
Store the .jks file in the classpath and copy the public key printed after
the second command and paste it in a .pub file and keep that file under the
classpath of the resource-server (the app that will authenticate the token)
After generating the keys define the properties you had provided in the command
when creating the private key in the yml file like here
```yaml
jwt:
  password: <password>
  alias: <alias>
  privateKey: <filename>.jks
```
**After the above steps you can start the app in your local IDE like any spring
boot app and by default the app runs at port 8081**
***
# Generate JWT Token
***
## Onboard client details
To generate JWT token you need to onboard the credentials of the app that will
act as the client that sends a request for a JWT token.
You can onboard them by passing a post request to the endpoint 
http://localhost:8081/client/details with the following body sample.
We need to add resource id that will be added to the JWT token which will be used
to authenticate resource server that the JWT token is used on.
```json
{
  "clientId": "<client-id>",
  "secret": "<client-secret>",
  "scope": "<scope>",
  "resourceId": "<id-for-resource-app>"
}
```
the app only supports password grant_type for now so grantType is always
password therefore pass the header **clientGrantType** as password and pass
the Authorization credentials as header to authenticate the request.
The Authorization header is the credentials of the user you had added.
Pass the request using postman and the client details are added.
## Onboard resource id (Optional)
To add another resource id for the same client id use this endpoint
Use the http://localhost:8081/resource/details to onboard resource details
The post request must have the following headers
```json
{
    "clientId": "<client-id the resource must be associated to>",
    "resourceId": "<resource-id>"
}
```
And pass the Authorization header like in the previous request
## Generate JWT token
The JWT token can be generated using the http://localhost:8081/oauth/token
endpoint and needs the following query parameters<br>
grant_type: password (always password as we support only password grant type)<br>
username: user username<br>
password: user password<br>
scope: read<br>
and **Authorization** as **Header** with client id and client secret as
username and password
You will get a jwt token like in below after sending the **POST** 
request successfully
```json
{
    "access_token": "eyJh........Y4c0ShNeYfCXtpIA",
    "token_type": "bearer",
    "expires_in": 3599,
    "scope": "read",
    "jti": "2ea3a06c-281a-491f-9c31-6c92b6ead4f7"
}
```
### I would be happy to hear from you at rohan.aditeya@gmail.com
**Note** I do have some testing code and more functionalities to add