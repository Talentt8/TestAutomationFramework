import org.apache.commons.codec.binary.Base64;

public class encrypt {


	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String pass1 = "Pass1we";

		byte[] encodedPwdBytes = Base64.encodeBase64(pass1.getBytes());
		System.out.println("Encrypted password is : " + encodedPwdBytes);
	}

}
