#include<stdio.h>
int a[5] = { 1,2,3,4,5 };
int binarySearch(int low, int high,int x) {
	
	if (low > high)
		return 0;
	else {
		int mid = (low + high) / 2;
		if (a[mid] == x)
			return mid;
		else if (a[mid] > x) {
			binarySearch(low, mid - 1,x);
		}
		else
			binarySearch(mid + 1,high, x);
	}
	return 0;
}
int main(void) {
	
	printf("%d", binarySearch(0, 4,3));
	return 0;
}