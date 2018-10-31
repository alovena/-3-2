#include<stdio.h>
int arr[8] = { 5,4,1,2,3,6,8,9 };
void partition(int low, int high, int *pp) {
	
	*pp = arr[low];
	int j = low;
	int temp;
	for (int i = low + 1; i <= high; i++) {
		if (*pp > arr[i]) {
			j++;
			temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
	}
	*pp = j;
	if (*pp == 0) {
		return 0;
	}
	printf("%d", *pp);
	temp = arr[*pp];
	arr[*pp] = arr[low];
	arr[low] = temp;
	
	}
void Quicksort(int low, int high) {
	int pp;
	if (low < high) {
		
		partition(0, 7, &pp);
		Quicksort(0, (pp)-1);
		Quicksort((pp)+1,7 );
	}
}

int main(void) {

	Quicksort(0, 7);
	for (int i = 0; i < 8; i++) {
		printf("%d", arr[i]);
	}
	return 0;
}