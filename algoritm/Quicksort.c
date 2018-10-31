#include<stdio.h>
int arr[10] = { 3,7,8,1,5,9,6,10,2,4 };
void quickSort(int *date, int start, int end) {
	if (start >= end)
		return;
	int key = start;
	
	int i = start + 1;
	int j = end;
	int temp;
	while (i <= j)//엇갈릴떄까지
	{
		while (date[i] <= date[key]) {
			i++;
		}
		while (date[j] >= date[key]&&j>start) {
			j--;
		}
		if (i > j) {
			temp = date[j];
			date[j] = date[key];
			date[key] = temp;
		}
		else {
			temp = date[j];
			date[j] = date[i];
			date[i] = temp;
		}
		quickSort(date, start, j - 1);
		quickSort(date, j + 1, end);
	}
}
void main(void) {
	int i;
	int piviet, j, temp;
	
	quickSort(arr, 0, 9);
	for (i = 0; i < 9; i++) {
		printf("%d", arr[i]);
	}

}