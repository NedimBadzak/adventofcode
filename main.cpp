#include <iostream>
#include <string>
#include <fstream>

using namespace std;
int main() {
	std::string myText;

std::ifstream MyReadFile("input2.txt");


int counter = 0;
int counterZbirova = 0;
int previous1 = 0;
int previous2 = 0;
int previous3 = 0;


while (getline (MyReadFile, myText)) {
  /* if (stoi(myText) > previous) counter++; */
  /* previous = stoi(myText); */

  if (counter == 0) previous1 = stoi(myText);
  else if (counter == 1) previous2 = stoi(myText);
  else if (counter == 2)  previous3 = stoi(myText); 
  else {
	if(stoi(myText)+previous2+previous3 > previous1+previous2+previous3) counterZbirova++;
	previous1 = previous2;
	previous2 = previous3;
	previous3 = stoi(myText);
  }
  counter++;
}

cout << "Broj povecanja je : " << counterZbirova;


// Close the file
MyReadFile.close();	


}
