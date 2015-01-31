package sundev.test;

import java.util.Random;

import android.app.Activity;

public abstract class cCalculator {

	static int answer;
	static int lhs;
	static int rhs;
	static int lhsMaxAddition;
	static int rhsMaxAddition;
	static int lhsMaxSubtraction;
	static int rhsMaxSubtraction;
	static int lhsMaxMultiplication;
	static int rhsMaxMultiplication;
	static int lhsMaxDivision;
	static int rhsMaxDivision;
	static String questionString;
	
	static void setQuestionDifficulty(Activity theActivity,String level)
	{
		if(level.equals(theActivity.getResources().getStringArray(R.array.gameLevelArray)[0]))
		{
			lhsMaxAddition = 10;
			rhsMaxAddition = 10;
			lhsMaxSubtraction = 10;
			rhsMaxSubtraction = 10;
			lhsMaxMultiplication = 10;
			rhsMaxMultiplication = 5;
			lhsMaxDivision = 10;
			rhsMaxDivision = 5;
			
		}
		else if (level.equals(theActivity.getResources().getStringArray(R.array.gameLevelArray)[1]))
		{
			lhsMaxAddition = 20;
			rhsMaxAddition = 20;
			lhsMaxSubtraction = 20;
			rhsMaxSubtraction = 20;
			lhsMaxMultiplication = 10;
			rhsMaxMultiplication = 10;
			lhsMaxDivision = 10;
			rhsMaxDivision = 10;			
		}
		else if (level.equals(theActivity.getResources().getStringArray(R.array.gameLevelArray)[2]))
		{
			lhsMaxAddition = 100;
			rhsMaxAddition = 100;
			lhsMaxSubtraction = 100;
			rhsMaxSubtraction = 100;
			lhsMaxMultiplication = 20;
			rhsMaxMultiplication = 10;
			lhsMaxDivision = 20;
			rhsMaxDivision = 10;		
		}
		else if (level.equals(theActivity.getResources().getStringArray(R.array.gameLevelArray)[3]))
		{
			lhsMaxAddition = 500;
			rhsMaxAddition = 500;
			lhsMaxSubtraction = 500;
			rhsMaxSubtraction = 500;
			lhsMaxMultiplication = 20;
			rhsMaxMultiplication = 20;
			lhsMaxDivision = 20;
			rhsMaxDivision = 20;			
		}
		else if (level.equals(theActivity.getResources().getStringArray(R.array.gameLevelArray)[4]))
		{
			lhsMaxAddition = 1000;
			rhsMaxAddition = 1000;
			lhsMaxSubtraction = 1000;
			rhsMaxSubtraction = 1000;
			lhsMaxMultiplication = 50;
			rhsMaxMultiplication = 20;
			lhsMaxDivision = 50;
			rhsMaxDivision = 20;	
		}		
	}
	
	static String getQuestion()
	{
		//Generate a random number and use it to decide whether to give the player
		//an addition, subtraction, multiplication or division question
		int randomNumber;
		Random generator = new Random();
		randomNumber = generator.nextInt(4);	
	
		if(cUserPreferences.gameType.equals(cUserPreferences.myActivity.getResources().getStringArray(R.array.gameTypeArray)[0]))
		{
			randomNumber = 0;		
		}
		else if(cUserPreferences.gameType.equals(cUserPreferences.myActivity.getResources().getStringArray(R.array.gameTypeArray)[1]))
		{
			randomNumber = 1;			
		}
		else if(cUserPreferences.gameType.equals(cUserPreferences.myActivity.getResources().getStringArray(R.array.gameTypeArray)[2]))
		{
			randomNumber = 2;		
		}
		if(cUserPreferences.gameType.equals(cUserPreferences.myActivity.getResources().getStringArray(R.array.gameTypeArray)[3]))
		{
			randomNumber = 3;			
		}

		switch (randomNumber)
		{
		case 0:
			questionString = getAdditionQuestion();
			break;
		case 1:
			questionString = getSubtractionQuestion();
			break;
		case 2:
			questionString = getMultipicationQuestion();
			break;
		case 3:
			questionString = getDivisionQuestion();	
			break;
		}
		return questionString;		
	}
	
	static String getAdditionQuestion()
	{
		Random generator = new Random();
		lhs = generator.nextInt(lhsMaxAddition);
		rhs = generator.nextInt(rhsMaxAddition);
		answer = lhs + rhs;
		questionString = Integer.toString(lhs) + " + " + Integer.toString(rhs) + " = ";
		return questionString;		
	}
	static String getSubtractionQuestion()
	{
		Random generator = new Random();
		lhs = generator.nextInt(lhsMaxSubtraction);
		rhs = generator.nextInt(rhsMaxSubtraction);
		// Ensure lhs is greater that rhs because we only want to generate positive answers
		if (lhs < rhs)
		{
			int temp;
			temp =lhs;
			lhs = rhs;
			rhs = temp;		
		}
		
		answer = lhs - rhs;
		questionString = Integer.toString(lhs) + " - " + Integer.toString(rhs) + " = ";
		return questionString;		
	}
	static String getMultipicationQuestion()
	{
		Random generator = new Random();
		lhs = generator.nextInt(lhsMaxMultiplication);
		rhs = generator.nextInt(rhsMaxMultiplication);
		
		answer = lhs * rhs;
		questionString = Integer.toString(lhs) + " X " + Integer.toString(rhs) + " = ";
		return questionString;		
	}
	static String getDivisionQuestion()
	{
		Random generator = new Random();
		lhs = generator.nextInt(lhsMaxDivision)+1;
		rhs = generator.nextInt(rhsMaxDivision)+1;
		
		// Some clever manipulation is required to come up with a reasonable division question
		answer = lhs * rhs;
		lhs = answer;
		answer = lhs/rhs;
		questionString = Integer.toString(lhs) + " / " + Integer.toString(rhs) + " = ";
		return questionString;		
	}
	static String getAnswer()
	{
		return Integer.toString(answer);
	}
}
