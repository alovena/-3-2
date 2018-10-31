#include<stdio.h>
#include<time.h>
#include<stdlib.h>
int arr[6] = { 0,1,2,3,4,5 };
void location(int n, int arr[] ,int x,int *l);

int main(void) {
	int index;
	location(5, arr, 4, &index);
	printf("%d", index);
	return 0;
}

void location(int n, int arr[], int x,int *l) {
	int low = 1;
	int high = n;
	int mid;
	*l = 0;
	while (*l == 0 && high >= low) {
		mid = (low + high) / 2;
		if (arr[mid] == x) {
			*l = mid;
		}
		else if (arr[mid] > x) {
			high = mid - 1;
		}
		else {
			low = mid + 1;
		}
	}
	
}
