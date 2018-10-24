package sha;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class HashPassword
{
public static String hashPassword(String password) throws NoSuchAlgorithmException
{
MessageDigest md = MessageDigest.getInstance("SHA-1");
md.update(password.getBytes());
byte[] b = md.digest();
StringBuffer sb = new StringBuffer();
for(byte b1 : b)
{
sb.append(Integer.toHexString(b1 & 0xff).toString());
}
return sb.toString();
}
public static void main(String[] args)
{
String password = "cummins";
System.out.println("Input message is:"+password);
try
{
System.out.println("Message digest is:"+hashPassword(password));
}
catch(NoSuchAlgorithmException e)//change of message digest is avalanche eeffect,digital signature,message digest ka block
{

System.out.println(e);
}
}
}
