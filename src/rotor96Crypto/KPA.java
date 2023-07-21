package rotor96Crypto;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * The knownPlainTextDecrypt function takes ciphertext, plaintext's first 2 letters and password file as a input and return all the keys and plaintext that matches.
 * @author Haet Trivedi
 *
 */
public class KPA {
			
	public Map<String,String> knownPlainTextDecrypt(String cipherText, String plainText,File file) throws FileNotFoundException
	{
		String cipherTextSubString = cipherText.substring(0,2);
		Map<String,String> resultKPA = new HashMap<String,String>(); // stores intermediate result as a HashMap of key and its plaintext of 2 letters
		Map<String,String> result = new HashMap<String,String>(); // stores final result as a HashMap of key and its plaintext

		
		Scanner sc = new Scanner(file); // creates scanner object
		
		// Iterates through all the keys and generates the plaintext with the given ciphertext and stores the keys and plaintext that starts with "Th" 
		while(sc.hasNextLine())
		{
			String key = sc.nextLine();
			
			String decryptedMessage = Rotor96Crypto.encdec(2,key,cipherTextSubString);
			
			if(decryptedMessage.equals(plainText))
			{
             	resultKPA.put(key, decryptedMessage);
			}
					
		}
		
		for (Map.Entry<String, String> entry : resultKPA.entrySet()) {
			String plaintext  = Rotor96Crypto.encdec(2,entry.getKey(),cipherText);
		   result.put(entry.getKey(), plaintext);
		}
		sc.close();
		return result;
		
	}
	
   public static void main(String[] args) throws FileNotFoundException {
		
		String cipherTextKPA = "`#v!c2;xtDFGASfv,Vd[eM#8s=@*n=Y7?TeaZyY{zHmm3?Mpc1_:6sERvq.^1&vnXI=)$^`E)BChu<KC_i7bevwqw~J3Du=4aj4t'^";
		String plainTextKPA = "Th";

		Map<String, String> resultKPA = new HashMap<String,String>();


		KPA knownPlainText = new KPA();
		
		File file = new File("passwords");
		
		System.out.println("Task 1 - KnownPlainText");
		System.out.println("---------------------------------------------------------------------------------------------------------------------");
		resultKPA = knownPlainText.knownPlainTextDecrypt(cipherTextKPA, plainTextKPA,file);
		for (Map.Entry<String, String> entry : resultKPA.entrySet()) {
		    System.out.println("Key: "+ entry.getKey()+"\n");
		    System.out.println("PlainText: "+ entry.getValue()+"\n\n");
		   
		}
   }
}
		




