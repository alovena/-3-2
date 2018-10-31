#include<stdio.h>
int arr[8] = { 15,22,13,27,12,10,20,25 };
typedef struct Node{
	int data;
	struct node *next;
}node;
typedef struct queue{
	node *front;
	node *rear;
	int count;
}Queue;
void InitQueue(Queue *q) {
	q->count = 0;
	q->front = NULL;
	q->rear = NULL;
}
int IsEmpty(Queue *q) {
	return (q->count) == 0;
}
void Enqueue(Queue *q, int t) {
	node *n = (node *)malloc(sizeof(node));
	n->data = t;
	n->next = NULL;
	if (IsEmpty(q)) {
		q->front = n;
	}
	else {
		q->rear->next = n;
	}
	q->rear = n;
	q->count++;
}
int Dequeue(Queue *q) {
	int re = 0;
	node *now;
	if (IsEmpty(q))//큐가 비었을 때
	{
		return re;
	}
	now = q->front;//맨 앞의 노드를 now에 기억
	re = now->data;//반환할 값은 now의 data로 설정
	q->front = now->next;//맨 앞은 now의 다음 노드로 설정
	free(now);//now 소멸
	q->count--;//보관 개수를 1 감소
	return re;
}
void quicksort(int low, int high) {
	Queue L;
	Queue R;
	int start, end;
	InitQueue(&L);
	InitQueue(&R);
	Enqueue(&L, low);
	Enqueue(&R, high);
	int top = 1;
	int pp;
	while (top > 0) {
		start=Dequeue(&L, low);
		end=Dequeue(&R, high);
		top--;
		partition(start, end);
		if (pp - 1 > start) {
			top++;
			Enqueue(&L, start);
			Enqueue(&R, pp-1);
		}
		else if (pp + 1 < end) {

		}
	}
}
void main(void) {

}