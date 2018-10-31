#include<stdio.h>
int arr[5] = { 1,2,3,4,5 };
void bs(int n, int arr[], int x, int *p) {
	int low = 0;
	int high = n-1;
	int mid;
	*p = 0;

	while (low<=high&&*p ==0) {
		mid = (low + high) / 2;
		if (arr[mid] == x) {
			*p = mid;
			//break;
		}
		else if (x < arr[mid]) {
			high = mid - 1;
		}
		else
			low = mid + 1;
	}
}
int main(void) {
	int ll;
	bs(5, arr, 5, &ll);
	printf("%d", ll);
	return 0;
}
