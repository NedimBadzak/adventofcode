#include <iostream>
#include <string>
#include <fstream>
#include <vector>
#include <cmath>

using namespace std;

void kickOut(vector<string> &vector1, int j, int n);

long binaryToDecimal(const string &s) {
    long n = stol(s);
    long decimal = 0;

    for (int i = 0; i < s.length(); i++) {
        decimal += n % 10 * pow(2, i);
        n /= 10;
    }
    return decimal;
}

bool returnMostCommonBit(vector<string> &v, int index) {
    int zeroes = 0;
    int ones = 0;
    for (int j = 0; j < v.size(); ++j) {
        if (v[j][index] == '0') {
            zeroes++;
        }
        if (v[j][index] == '1') {
            ones++;
        }
    }
    if (zeroes > ones) {
        return false;
    }

    if (ones >= zeroes) {
        return true;
    }
}

int main() {
    std::string myText;

    std::ifstream MyReadFile("/home/nedim/CLionProjects/AdventOfCode/input.txt");
//    std::ifstream MyReadFile("/home/nedim/CLionProjects/AdventOfCode/input2.txt");


    vector<string> numbers;
    while (getline(MyReadFile, myText)) {
        numbers.push_back(myText);
    }


    string gammaRate = "";
    string epsilonRate = "";
    for (int i = 0; i < numbers[0].size(); ++i) {
        int zeroes = 0;
        int ones = 0;
        for (int j = 0; j < numbers.size(); ++j) {
            if (numbers[j][i] == '0') {
                zeroes++;
            }
            if (numbers[j][i] == '1') {
                ones++;
            }
        }
        gammaRate.push_back(zeroes > ones ? '0' : '1');
        epsilonRate.push_back(zeroes > ones ? '1' : '0');

    }
    cout << gammaRate << endl;
    cout << epsilonRate << endl;
    cout << "Power consumption: " << "gamma: " << binaryToDecimal(gammaRate) << " * epsilon: "
         << binaryToDecimal(epsilonRate) << " = " << binaryToDecimal(gammaRate) *
                                                     binaryToDecimal(epsilonRate) << endl;

    vector<string> o2;
    vector<string> co2;
    std::copy(numbers.begin(), numbers.end(), std::back_inserter(o2));
    std::copy(numbers.begin(), numbers.end(), std::back_inserter(co2));
    for (int i = 0; i < o2[0].size(); ++i) {
        if (returnMostCommonBit(o2, i)) {
            kickOut(o2, i, 0);
        } else {
            kickOut(o2, i, 1);
        }
        if (o2.size() == 1) {
            break;
        }
    }
    for (int i = 0; i < co2[0].size(); ++i) {
        if (returnMostCommonBit(co2, i)) {
            kickOut(co2, i, 1);
        } else {
            kickOut(co2, i, 0);
        }
        if (co2.size() == 1) {
            break;
        }
    }

    cout << "O2: " << binaryToDecimal(o2[0]) << endl;
    cout << "CO2: " << binaryToDecimal(co2[0]) << endl;
    cout << "Quality: " << binaryToDecimal(o2[0]) * binaryToDecimal(co2[0]) << endl;

// Close the file
    MyReadFile.close();
    return 0;
}

void kickOut(vector<string> &vector1, int j, int n) {
    int size = vector1.size();
    int it = 0;
    for (int i = 0; i < size-it; i++) {
        if (vector1[i][j] - '0' == n) {
            vector1.erase(vector1.begin() + i);
            i=-1;
            it++;
        }
    }
}

