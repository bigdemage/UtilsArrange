package common.utils.aes;


import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.UUID;


/**
 * 
 * CreateDate:2016年11月22日下午5:14:56 
 * @Description: AES简单加密解密工具类
 * @author:yuzhao
 * @version V1.0
 */
public class AESSimpleUtils {
	
	private static final String ENCRYPTION_TYPE = "AES/ECB/PKCS5Padding";
	private static final String ENCRYPTION_ALGORITHM = "AES";
	private static final String ENCODE_TYPE = "utf-8";
	private static final int KEY_SIZE = 128;
	
	/**
	 * 
	 * @Description::生成密钥，base64编码. <br/> 
	 * @author yuzhao
	 * @return
	 * @throws Exception
	 */
	public static String generateSecretKey() throws Exception {
		KeyGenerator  kgen = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM);
		kgen.init(KEY_SIZE); // 192 and 256 bits may not be available
		SecretKey skey = kgen.generateKey();
		byte[] secretKey1  = skey.getEncoded();
		return Base64Util.encode(secretKey1);
	}
	
	/**
	 * 
	 * @Description::AES加密. <br/> 
	 * @author yuzhao
	 * @param str
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String aesEncrypt(String str, String key) throws Exception {
        if (str == null || key == null) return null;
        Cipher cipher = Cipher.getInstance(ENCRYPTION_TYPE);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(Base64Util.decode(key), ENCRYPTION_ALGORITHM));
        byte[] bytes = cipher.doFinal(str.getBytes(ENCODE_TYPE));
//        return Base64Util.encode(bytes);
        return asHex(bytes);
    }

	/**
	 * 
	 * @Description::AES解密. <br/> 
	 * @author yuzhao
	 * @param str
	 * @param key
	 * @return
	 * @throws Exception
	 */
    public static String aesDecrypt(String str, String key) throws Exception {
        if (str == null || key == null) return null;
        Cipher cipher = Cipher.getInstance(ENCRYPTION_TYPE);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(Base64Util.decode(key), ENCRYPTION_ALGORITHM));
//        byte[] bytes = Base64Util.decode(str);
        byte[] bytes = asByte(str);
        bytes = cipher.doFinal(bytes);
        return new String(bytes, ENCODE_TYPE);
    }
    
    private static String asHex(byte buf[]) {
        return new String(Hex.encodeHex(buf));
    }
    
    public static byte[] asByte(String hexString) throws DecoderException {
        return Hex.decodeHex(hexString.toCharArray());
    }
    
    public static String getUUID(){
        return UUID.randomUUID().toString().trim().replaceAll("-", "");
    }

	public static void main(String[] args) throws Exception {
    	String key="SRGQZrlpKfgOVcZi+bor8g==";
		System.out.println("金服:"+aesEncrypt("1",key));
		System.out.println("企金:"+aesEncrypt("7",key));
		System.out.println("保险:"+aesEncrypt("8",key));
		System.out.println("老带新:"+aesEncrypt("17",key));
		System.out.println("财商教育:"+aesEncrypt("18",key));

		aesDecrypt("3",key);
	}
}