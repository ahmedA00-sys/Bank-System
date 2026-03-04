#include <iostream>
using namespace std;
int main() {
	int x = 10;
	cout << x << endl;
	cout << &x << endl;

	int* ptr = &x;
	cout << *ptr << endl;
	return 0;
}