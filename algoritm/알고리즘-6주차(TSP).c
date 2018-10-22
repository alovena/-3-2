#include<stdio.h>
#include<math.h>
#include<limits.h>

typedef struct City {
	int num;
	int x;
	int y;
}City;
//전역변수
City stack[5];
int top = -1;
int min = 9999;

int visited[5] = { 0 };
//함수구현
void push(City element) {
	stack[++top] = element;
}

City pop() {
	return stack[top--];
}

City city[] = {
	{ 1,10,10 },{ 2,15,20 },{ 3,20,20 },{ 4,20,30 },{ 5,30,30 }
};

int getDistance(City a, City b) {
	return sqrt((a.x - b.x)*(a.x - b.x) + (a.y - b.y)*(a.y - b.y));
}

void TSP(int start, City city[], int number, int sum, int now) {
	visited[start] = 1;
	int i, count = 0;
	for (i = 0; i < 5; i++) {
		if (visited[i] == 0) {
			count++;
			visited[i] = 1;
			push(city[i]);
			printf("%d ",i );
			TSP(start, city, number, sum + getDistance(city[now], city[i]), i);
			visited[i] = 0;
			pop();
		}
	}
	if (count == 0) {
		printf("%d=>", city[start].num);
		sum += getDistance(city[now], city[start]);
		for (int i = 0; i <= top; i++) {
			printf("%d=>", stack[i].num);
		}
		printf("%d 합계 %d\n", city[start].num, sum);
		if (min > sum) {
			min = sum;
		}
	}
}

int main(void) {
	TSP(0, city, 5, 0, 0);
	printf("최소값 : %d\n", min);
	return 0;

}