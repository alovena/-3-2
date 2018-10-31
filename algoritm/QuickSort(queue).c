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
	if (IsEmpty(q))//ť�� ����� ��
	{
		return re;
	}
	now = q->front;//�� ���� ��带 now�� ���
	re = now->data;//��ȯ�� ���� now�� data�� ����
	q->front = now->next;//�� ���� now�� ���� ���� ����
	free(now);//now �Ҹ�
	q->count--;//���� ������ 1 ����
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