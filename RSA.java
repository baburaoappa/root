import java.io.*;
import java.security.*;
import javax.crypto.*;
public class RSA {
/**
* @param args
*/
public static void main(String[] args) throws IOException {
try {
// TODO Auto-generated method stub
System.out.println("---- RSA in Java ----");
System.out.println();
String plainText;
byte inData[], encrypt[], outData[];
BufferedReader br = new BufferedReader(new
InputStreamReader( System.in));
System.out.println("Enter Plaintext : ");
plainText = br.readLine();
KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
kpg.initialize(512);
KeyPair kp = kpg.generateKeyPair();
PublicKey publicKey = kp.getPublic();
System.out.println("Public Key : " + publicKey); Cipher cipher= Cipher.getInstance("RSA"); cipher.init(Cipher.ENCRYPT_MODE, publicKey); inData
= plainText.getBytes(); encrypt = cipher.doFinal(inData);
System.out.println("Encrypted Plaintext is : " + encrypt);
PrivateKey privateKey = kp.getPrivate();
cipher.init(Cipher.DECRYPT_MODE, privateKey); System.
out.println("Private key:" + privateKey); outData = cipher.doFinal(encrypt);
System.out.println("Decrypted Plaintext is : " + new
String(outData));
}
catch (Exception e) {
e.printStackTrace();
}
}
}
3. Output (Console)
---- RSA in Java ----
Enter Plaintext :
Hello ABC
Public Key : Sun RSA public key, 512 bits
modulus:
92165433687687594864378993177399149887690070650798433325139346030887788337728675
80358773707793591039113164634075843957661164993023680637735988262606923387
public exponent: 65537
Encrypted Plaintext is : [B@712c20d9
Private key:sun.security.rsa.RSAPrivateCrtKeyImpl@5e870
Decrypted Plaintext is : Hello ABC
