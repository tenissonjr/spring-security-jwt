package com.exemplo.tenissonjr.security;
// filepath: /D:/desenv2/aplic/spring-security-jwt/src/main/java/com/exemplo/tenissonjr/ExportPrivateKey.java
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.util.Base64;


/* 
1 Gere o par de chaves RSA usando o keytool:
keytool -genkeypair -alias mykey -keyalg RSA -keysize 2048 -validity 365 -keystore mykeystore.jks -dname "CN=example.com, OU=IT, O=Example, L=City, S=State, C=Country" -storepass changeit -keypass changeit
 * 
 2 .Exporte a chave pública para o arquivo app.pub:
 keytool -export -alias mykey -keystore mykeystore.jks -rfc -file app.pub -storepass changeit
 * 
*/

public class ExportPrivateKey {
    public static void main(String[] args) throws Exception {
        KeyStore keystore = KeyStore.getInstance("JKS");
        keystore.load(new java.io.FileInputStream("mykeystore.jks"), "changeit".toCharArray());

        KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry) keystore.getEntry("mykey", new KeyStore.PasswordProtection("changeit".toCharArray()));
        PrivateKey privateKey = pkEntry.getPrivateKey();

        String encoded = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        try (FileOutputStream fos = new FileOutputStream("app.key")) {
            fos.write(encoded.getBytes());
        }
    }
}