package horcrux;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES 
{
    public static byte[] encrypt(String key, String value) throws NoSuchAlgorithmException, 
            NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, 
            IllegalBlockSizeException, BadPaddingException
    {
        byte[] raw = key.getBytes(Charset.forName("US-ASCII"));
        if (raw.length != 16) 
        {
            throw new IllegalArgumentException("Invalid key size.");
        }
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec,new IvParameterSpec(new byte[16]));
        return cipher.doFinal(value.getBytes(Charset.forName("US-ASCII")));
  }
    
  public static String decrypt(String key, byte[] encrypted)
      throws GeneralSecurityException 
  {
      byte[] raw = key.getBytes(Charset.forName("US-ASCII"));
      if (raw.length != 16) 
      {
          throw new IllegalArgumentException("Invalid key size.");
      }
      SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      cipher.init(Cipher.DECRYPT_MODE, skeySpec,
        new IvParameterSpec(new byte[16]));
      byte[] original = cipher.doFinal(encrypted);
      return new String(original, Charset.forName("US-ASCII"));
  }
}

