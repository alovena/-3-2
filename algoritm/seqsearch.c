#include<stdio.h>
int arr[6] = { 0,5,4,3,2,7 };
void seqsearch(int n, int arr[], int x, int *l) {
	for (int i = 1; i <= 5; i++) {
		if (arr[i] == x) {
			*l = i;
		}
	}
	
}
int main(void) {
	int num;
	seqsearch(6, arr, 7, &num);
	printf("%d", num);
	return 0;
}