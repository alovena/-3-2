#include<stdio.h>
typedef struct __Arr {
	int index;
	int abc[10];
}Hash;
int count;
int number = 1;
int value[10];
void insert(Hash *list, int k);
void select(Hash *list, int k);
int delete(Hash *list,int k);
void init(Hash *list) {
	for (int i = 0; i < 10; i++)
		list->abc[i] = 0;
}
void insert(Hash *list, int k) {
		//�ε��� �˻�
		//�װԸ����� 0++���ֱ�
		list->index = k % 11;
		
		for (int i = 0; i < count; i++) {
			if (value[i] == list->index) {
				printf("���־��");
				list->index = number++;
			}
			else {
				
			}
		}
		value[count++] = list->index;
		list->abc[(list->index) - 1] = k;
		//�װԾƴϸ� �׳�
}
void select(Hash *list, int k) {
	for (int i = 0; i < 10; i++) {
		if (list->abc[i] == k) {
			printf("\n%d �˻� ��� :�ε��� �ѹ� = %d\n",k, i+1);
		}
	}
}
int delete(Hash *list,int k) {
	
}
void println(Hash *list) {
	for (int i = 0; i < 10; i++) {
		printf("%d ", list->abc[i]);
	}
}
void main(void) {
	int number;
	int data,data2;
	Hash aaa;
	init(&aaa);
	printf("==================\n");


	while (1) {
		printf("1.INSERT\n");
		printf("2.SEARCH\n");
		printf("3.DELETE\n");
		scanf_s("%d", &number);
		if (number == 1) {
			printf("�������Է�:");
			scanf_s("%d", &data);
			insert(&aaa, data);
			println(&aaa);
			printf("\n==================\n");
		}
		else if (number == 2) {
			scanf_s("%d", &data2);
			select(&aaa, data2);
			printf("==================\n");
		}
		else if (number == 3) {
			//delect(&aaa);
			printf("==================\n");
			
		}
		else {
			printf("�ߴ�.");
			break;
		}
	}
	

}

/*
collision �� �ذ��ϴ� ��� : 1. open addressing -> linear probing

2. bucket
3. chainning

SA�����

*/