package rotor96Crypto;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * The cipherTextOnlyAttackDecrypt function takes ciphertext and password file as a input and returns HashMap of correct Key and Plaintext
 * @author Haet Trivedi
 *
 */
public class COA {

	@SuppressWarnings("resource")
	public Map<String, String> cipherTextOnlyAttackDecrypt(String cipherText, File file) throws FileNotFoundException {

		List<String> keys = new ArrayList<String>(); // Stores filtered keys as a list of strings

		Map<String, String> result = new HashMap<String, String>(); // Stores intermediate result as a HashMap of Key and Plaintext

		Scanner sc = new Scanner(file); // Creates scanner object

		List<String> commonWords = COA.getCommonWords(); // Fetches the dictionary words

		int totalCipherLength = cipherText.length(); // Total number of letters in Ciphertext

		/*
		 * Stores all the keys in the List of Strings
		 * */
		while (sc.hasNextLine()) 
		{
			String key = sc.nextLine();
				keys.add(key);
		}

		/*
		 * Keys are iterated through all the length of the ciphertext letters and removes a key for which the plaintext contains 
		 * gibberish words.
		 * Scores are stored with each key by comparing its plaintext words with the Dictionary and then removes keys with 
		 * less score from the list of keys.
		 * The above process continues until the best score single key-plaintext is achieved and returns its key-plaintext pair as a result.
		 * It also prints the minimum number of CipherText letters required to decrypt the encrypted text.
		 * */
		for (int i = 2; i < totalCipherLength; i++) 
		{
			String cipher = cipherText.substring(0, i); // stores the ciphertext letters of the length of i
			List<String> removeKeys = new ArrayList<String>(); // List of Strings to remove keys which geenrates gibberish plaintext
			Map<String, Long> resultCOA = new HashMap<String, Long>(); // Stores the final result as a HashMap of Key and its Dictionary Score

			long bestScore = 0;
			for (String cipherKey : keys) 
			{
				long count = 0;
				String decryptedCipher = Rotor96Crypto.encdec(2, cipherKey, cipher);
				boolean containsGibberish = COA.containsGibberish(decryptedCipher);

				if (containsGibberish)
				{
					removeKeys.add(cipherKey);
					if (resultCOA.containsKey(cipherKey)) 
					{
						resultCOA.remove(cipherKey);
					}
					continue;
				} 
				else 
				{
					List<String> decryptedValues = Arrays.asList(decryptedCipher.toLowerCase().split(" "));
					count = commonWords.stream().filter(word -> word.length()==cipher.length() && decryptedValues.stream().anyMatch(word::equals)).count();

					if (count != 0) 
					{
						if (count >= bestScore) 
						{
							bestScore = count;
							resultCOA.put(cipherKey, bestScore);
						}

					}

				}
			}
			
			keys.removeAll(removeKeys);
			if (resultCOA.size() == 1)
			{

				System.out.println("The number of ciphertext letters required to decode the encrypted text : " + (i)+"\n");
				String resultKey = resultCOA.keySet().iterator().next();
				String resultPlaintext = Rotor96Crypto.encdec(2, resultKey, cipherText);
				result.put(resultKey,resultPlaintext);
				break;
			}
          resultCOA.clear();
          
		}

		return result;
	}
	
	// Checks that the plaintext is Gibberish and returns boolean value
	public static boolean containsGibberish(String plainText) {
		
		String regex = "^[a-zA-Z0-9\\s]*$";
	    return !plainText.matches(regex);
	    
	}
	
	// Dictionary of most frequently occurring words (Unigram, Bigram, Trigram, n-grams) 
	public static List<String> getCommonWords()
	{
		List<String> commonWordsList = Arrays.asList("i", "a", "th", "to", "he", "us", "we", "in", "is", "it", "be", "on", "of", "or", "me", "my", "she", "ing", "ent", "ion", "his", "tha", "ere", "him", "her", "for", "you", "the","try", "and", "not", "are", "that", "with", "have", "this", "they", "they", "them");

		return commonWordsList;
	}
	
    public static void main(String[] args) throws FileNotFoundException {
		
		String cipherTextCOA = "Fys!Xi\"4*k@0b|(wwm!C%,j)ow$8hN57^I7+83$Fh&q(nN W0&$sxQ G5CG$z;9KK4A]bUh*m#6e\\7j1P=Om&w;9sqEM!Oe9+w&mV#mkO};|(P`^Wtkd$FK+j'=KN)NLu!yiTi]|TjCd)>&c&NK-l8=1FBcSvIB3aA]bUhtneU4hd6D:;)F&?Hvl'?suwuy,>&\\hli@v: PAVVb~-(%63oF{Vi%Y87|MP=x,q%(ZKDD#2^14Ks=xG\"e6UpE\\KZ159x#h!\\Z;gS;;_q$)mzh>9aGDG\\%e-gU|i\"+Ce7GB(TBt2by2T(S=+r@fm1/W2;vQ2Oj\\q=>";
       
    	Map<String, String> resultCOA = new HashMap<String,String>();

		COA cipherTextOnlyAttack = new COA();
		
		File file = new File("passwords");
		
		System.out.println("Task 2 - CipherOnlyAttack");
		System.out.println("---------------------------------------------------------------------------------------------------------------------");
		
		resultCOA = cipherTextOnlyAttack.cipherTextOnlyAttackDecrypt(cipherTextCOA, file);
		for (Map.Entry<String, String> entry : resultCOA.entrySet()) {
			System.out.println("Key: "+ entry.getKey()+"\n");
		    System.out.println("PlainText: "+ entry.getValue());
		}
	}
}
