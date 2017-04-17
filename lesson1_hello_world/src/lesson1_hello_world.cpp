// This is your first program.  Starting a line in a program with '//' means
// that line of code is only a comment.  Mistakes in comments will not affect
// the running of your program (Be careful though because an incorrect comment
// may confuse somebody trying to read your code)

// We include iostream so that we can write and read from the screen
// We are effectively using code somebody else has written for reading and
// writing from the screen (there is no point reinventing the wheel)
#include <iostream>

// The line below means we do not have to keep writing std::cout
using namespace std;

// C++ program always begin with a main function
// the int before the main function name means the function returns an integer value
int main()
{ // Note C++ is known as a curly bracket language because it uses curly brackets to enclose blocks of code

	// Note the use of ';' for end of line, do not miss this off else your program will not work

	// Declare your variables
	int gurvirsAge;  // I have used int instead of unsigned char because
					 // cout did not work with unsigned char variable
	string string1;
	string string2;
	// Assign values to your variables
	gurvirsAge = 9;
	string1 = "!!!Hello World - My name is Gurvir!!!";
	string2 = "I am ";
	// Write message to screen using cout
	cout << string1 << endl;
	cout << string2 << gurvirsAge << " years old";

	// The program must return an integer, we will return 0
	return 0;

}
