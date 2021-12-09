# Commands to generate asymmetric key
need openssl(comes with git) and keytool(comes with java jdk)
```shell
keytool -genkeypair -alias <keyAliasname> -keyalg <generationAlgorithm> -keypass <password> -keystore <privateKeyFilename>.jks -storepass <keyStorePassword>
keytool -list -rfc --keystore <privateKeyFilename>.jks | openssl x509 -inform pem -pubkey
```