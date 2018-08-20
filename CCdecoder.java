package assignOneQ1;

import java.util.Scanner;

public class Main {

	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Step 1: receive cipher and read length - set up array. 
		String cipherText = findCipher(); //find a correct text to return (a-z only)
		int textLength=cipherText.length(); //use textLength as a length maximum/safety measure
		
		//Step 2: enter each character in cipherText into a character array - cipherArray
		char[] cipherArray = cipherText.toCharArray(); //string to character array method
		
		int maxLocation = 0;
		maxLocation = findFreqLetter(maxLocation, textLength, cipherArray);
		//maxLocation is the most frequent letter's ASCII value. 
		
		decrypt('e', maxLocation, textLength, cipherArray);
		decrypt('t', maxLocation, textLength, cipherArray);
		decrypt('i', maxLocation, textLength, cipherArray);
		decrypt('o', maxLocation, textLength, cipherArray);
		decrypt('a', maxLocation, textLength, cipherArray);
		 
	}//end void main
	
	public static String findCipher() {
		System.out.println("Please enter Cipher Text to decrypt: ");
		String input = scanner.nextLine();
		String cipherText = input.toLowerCase();//to see if matches a-z strictly
		
		//if not initally an a-z text, loop through while until completed correctly.
		//using matches from [a-z]+, with the ! to negate if not a complete a-z ascii match
		if(!cipherText.matches("[a-z]+")) {
			while(!cipherText.matches("[a-z]+")) {
				System.out.println("Error, please enter Cipher Text (a-z) to decrypt: ");
				input = scanner.nextLine();
				cipherText = input.toLowerCase(); //to see if matches a-z strictly
				if(cipherText.matches("[a-z]+")) {
					break;
				}//end if cipherText matches
			}//end while 
		}//end if
		
		//System.out.println(cipherText); //it works
		String returnText = cipherText;
		return returnText;
	}//end findCipher
	
	public static int findFreqLetter(int maxLocation, int textLength, char[] cipherArray) {
		//Step 3: go through cipherArray, increasing freq by letter to another array
		//new int array, to go through [0 to .length], incrementing positions 0-25 accordingly
		//0-25 represent a-z, ASCII codes 97 to 122 (inclusive) 
		int [] azFreq = new int[26]; 
		//need to be 26 to prevent outofboundException. 26 alloted int spaces. Not same as 0-25. 
		//Index > cipher.Text, not >=
				
		for(int i=0; i<textLength; i++) {
			azFreq[(int)cipherArray[i]-97]++;
			//take the ASCII int value of the cipherArray character at location i
			//increment the azFreq letter that is recognized. 
		}//end for loop
				
		//Step 4: find the max frequency in azFreq
		for ( int i = 0; i < 26; i++ ){ 
			int temp = azFreq[i]; //create temp int value at tested i value
			if ( temp > azFreq[maxLocation] ) { //compare to value at max location
				maxLocation = i; //max location becomes i
			}	
		}
		
		maxLocation+=97; //now this gives the ASCII maxLocation, from 0-25 a-z into ASCI 97-122. 
						//will not work in instance of equal usage of 2+ letters (Ex. "aabb"). 
		return maxLocation; 
	}
	
	public static void decrypt(char letter, int maxLocation, int textLength, char [] cipherArray) {
	//Step 5: Output the series of possibilities, with e at 12.49% to a at 7.6%
		//Need to manipulate the cipherArray, filled with char values,
		//giving the user 6 sentence types. 
		//calling e in this instance
		//have a method calling letter 
		
		 int intLetter = (int) letter;
		 int key=0;
		 if(maxLocation <= intLetter){
			 key = (intLetter - maxLocation);
		 } else {
			 key = (26-maxLocation) + (intLetter);
		 }
		 //System.out.println(key);
	
		 //cipherArray same size as cipherText. 
		 int [] manipCipher = new int [textLength];
		 for(int i=0; i<textLength; i++) {
			int temp = (int)cipherArray[i]+key;
			if(temp<123) { //simply move it up
				manipCipher[i]= (int)cipherArray[i] + key;
			} else { //have to manipulate 
				manipCipher[i]= (int)cipherArray[i] + key - 26;
			}
			 
		}//end for loop
		
		//print the result to user
		char[] cipherOutputChar = new char[textLength];
		for(int i=0; i<textLength; i++) {
			cipherOutputChar[i] = (char) manipCipher[i];
		}
		String output = new String (cipherOutputChar);
		System.out.println("\nFollowing Decrypt Based on Most Freq Letter Being " + letter + ":");
		System.out.println("Output:" + output);
	}
	
	
}//end public class main
