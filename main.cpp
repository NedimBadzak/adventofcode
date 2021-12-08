#include <iostream>
#include <string>
#include <fstream>
#include <vector>

using namespace std;

int main() {
    std::string myText;

    std::ifstream MyReadFile("/home/nedim/CLionProjects/AdventOfCode/input.txt");
//    std::ifstream MyReadFile("/home/nedim/CLionProjects/AdventOfCode/input2.txt");

    vector<vector<string>> v;

    while (getline(MyReadFile, myText)) {
        vector<string> tempVector;
        for (int i = 0; i < myText.size(); i++) {
            if (myText[i] == ' ') {
                tempVector.push_back(myText.substr(0, i));
                tempVector.push_back(myText.substr(i + 1));
            }
        }
        v.push_back(tempVector);
    }

    int horizontal = 0;
    int depth = 0;
    int aim = 0;
    for (int i = 0; i < v.size(); i++) {
        if (v.at(i).at(0) == "forward") {
            horizontal += stoi(v.at(i).at(1));
            depth += aim * stoi(v.at(i).at(1));
        } else if (v.at(i).at(0) == "up") {
            aim -= stoi(v.at(i).at(1));
        } else if (v.at(i).at(0) == "down") {
            aim += stoi(v.at(i).at(1));
        }
    }
    std::cout << "Horizontal " << horizontal << " multiplied by depth " << depth << " equals " << horizontal * depth;
// Close the file
    MyReadFile.close();


}
