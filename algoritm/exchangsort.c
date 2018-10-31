#include<stdio.h>
int arr[5] = { 1,5,4,3,2 };
//가장 작은것을 선택해서 바꾸면 어떨까?
void exchange(int n, int arr[]) {
	int temp;
	for (int i = 1; i <=4; i++) {
		for (int j = i+1; j <= 5; j++) {
			if (arr[i] > arr[j]) {
				temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
	}
}

int main(void) {
	exchange(5, arr);
	for (int i = 0; i < 5; i++)
		printf("%d", arr[i]);
	return 0;
}