#include<stdio.h>
#include<math.h>

void CalcDistance(int num);

double Table[5][5] = { 0.0, };
int XY[5][2] = { 0 };

void main()
{
	double Diatance = 0.0;
	int Number;
	int i, j, k;
	double minDistance;
	int minP;
	int Tracking[6] = { 0, };//���� ����
	double TrackingD[6] = { 0.0, };//�����Ÿ�
	int TrackP = 0;
	
	Number=5;
	XY[0][0]=0;
	XY[0][1]=0;

	XY[1][0]=1;
	XY[1][1]=1;

	XY[2][0]=2;
	XY[2][1]=4;

	XY[3][0]=5;
	XY[3][1]=7;

	XY[4][0]=8;
	XY[4][1]=9;
	

	CalcDistance(Number);

	printf("\n ��κ�� ���̺� \n");
	for (i = 0; i<Number; i++)
	{
		printf("\n");
		for (j = 0; j<Number; j++)
		{
			printf("%5.2lf ", Table[i][j]);
		}
	}


	Tracking[TrackP] = 0;
	TrackP++;
	i = 0;//����� ����Ʈ
	while (TrackP != Number)
	{

		for (j = 0; j<Number; j++)//������ �ʱⰪ
		{
			int flag = 1;
			for (k = 0; k<TrackP; k++)//��ģ��� ����
			{
				if (Tracking[k] == j)
				{
					flag = 0; break;
				}
			}
			if (flag == 1)
			{
				if (Table[i][j]<0) continue;
				minDistance = Table[i][j];
				minP = j;
				break;
			}
		}
		for (j = 0; j<Number; j++)
		{
			int flag = 1;
			for (k = 0; k<TrackP; k++)//��ģ��� ����
			{
				if (Tracking[k] == j)
				{
					flag = 0; break;
				}
			}
			if (flag == 1)
			{
				if (Table[i][j]<0)//0 ������ ��� ����
				{
					continue;
				}

				else if (minDistance>Table[i][j])
				{
					minDistance = Table[i][j];
					minP = j;
				}
			}

		}
		Diatance = Diatance + Table[i][minP];
		Tracking[TrackP] = minP;
		TrackingD[TrackP] = Table[i][minP];
		TrackP++;
		i = Tracking[TrackP - 1];
	}
	//������ ���������� ����
	Diatance = Diatance + Table[minP][0];
	//Tracking[TrackP] = 0;
	//TrackingD[TrackP] = Table[minP][0];

	printf("\n\n ��ü ��κ�� : %3.3lf \n", Diatance);
}
//�Ÿ����
void CalcDistance(int Number)
{
	int i, j;
	double x, y;
	for (i = 0; i<Number; i++)
	{
		for (j = 0; j<Number; j++)
		{
			if (i == j)
			{
				Table[i][j] = 0;
				continue;
			}
			else
			{
				x = (XY[i][0] - XY[j][0])*(XY[i][0] - XY[j][0]);
				y = (XY[i][1] - XY[j][1])*(XY[i][1] - XY[j][1]);
				Table[i][j] = sqrt(x + y);
			}
		}
	}
}