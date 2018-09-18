import java.util.Scanner;
import java.util.ArrayList;
public class Password_Generator {
	public static void main(String[] args)
	{
		Scanner keyboard = new Scanner(System.in);
		String answer = "";
		System.out.println("Enter a possible password or 'quit.'");
		while (!answer.equalsIgnoreCase("quit"))
		{
		
		answer = keyboard.nextLine();
		printAnagrams(answer, keyboard);
		
		System.out.println("Enter a possible password or 'quit.'");
		
		}
		keyboard.close();
		
	}
	
	
	
	public static int possibleAnagrams(String word)
	{
		int probably = calcFactorial(word.length());
		ArrayList<Character> repeated = new ArrayList<Character>();
		int probablyNot = 1;
		int counter = 0;
		
		for (int i = 0; i < word.length(); ++i)
		{
			for (int j = i + 1; j < word.length(); ++j)
			{
				if (!repeated.contains(word.charAt(i))) {
					
					if (word.charAt(i) == word.charAt(j))
					{
						++counter;
					}
				}
			}
			
			if (counter > 0)
			{
				probablyNot *= calcFactorial(counter + 1);
				repeated.add(word.charAt(i));
			}
			counter = 0;
		}
		return probably / probablyNot;
	}
	
	public static int calcFactorial(int num)
	{
		int answer = 1;
		while (num > 1)
		{
			answer *= num;
			--num;
		}
		return answer;
	}
	
	public static void printAnagrams(String word, Scanner scnr)
	{
		int grams = possibleAnagrams(word);
		String answer = "";
		String possibleGram = "";
		int counter = 0;
		int sanityCounter = 0;
		ArrayList<String> alreadyDone = new ArrayList<String>();
		boolean halt = false;
		
		//System.out.println(grams);
		//System.out.println(counter);
		//System.out.println(sanityCounter);
		while (counter < grams && !halt)
			//System.out.println("Entering first loop");
		{
			while (sanityCounter < 100 && counter < grams)
			{
				//System.out.println("Entering second loop");
			possibleGram = generateAnagram(word);
			if (!alreadyDone.contains(possibleGram))
			{
				System.out.println(possibleGram);
				alreadyDone.add(possibleGram);
				++counter;
				++sanityCounter;
			}
			}
			System.out.println("Here are " + counter + " anagrams.");
			if (counter < grams)
			{
			System.out.println("There are a total of " + grams + " anagrams available. Would you like to see more?");
			System.out.println("Enter yes or no.");
			
			answer = scnr.nextLine();
			if (!answer.equalsIgnoreCase("yes"))
			{
				halt = true;
			}
			sanityCounter = 0;
			}
			
		}
}
	public static String generateAnagram(String word)
	{
		int random;
		int counter = 0;
		String anagram = "";
		ArrayList<Integer> letters = new ArrayList<Integer>();
		while (counter < word.length())
		{
			random = (int) Math.floor(Math.random() * word.length());
			if (!letters.contains(random))
			{
				anagram += word.charAt(random);
				++counter;
				letters.add(random);
			}
		}
		return anagram;
	}
}
