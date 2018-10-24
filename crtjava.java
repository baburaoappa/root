
import java.util.Scanner;



public class crt 

{

	static int inv(int n1,int n2)

	{

		int q,r,t=0;

		int t1=0;

		int t2=1;

		int bn1=n1;

		while(n2!=0)

		{

			q=n1/n2;

			r = n1%n2;

			t = t1 - q*t2;

			n1=n2;

			n2=r;

			t1=t2;

			t2=t;

			

		}

		if(t1<0)

		{

			t1=t1+bn1;

		}

		

		return t1;

	}

	static int findX(int a[],int m[], int n)

	{

		int result=0;

		int prod =1;

		

		int i=0;

		for(i=0;i<n;i++)

		{

			prod = prod*m[i];

		}

		for(i=0;i<n;i++)

		{

			int frac = prod/m[i];

			result = result+ frac*inv(m[i],frac)*a[i];

			System.out.println("\nResult is:"+result);

		}

		

		return result % prod;

		

	}

	static int checkgcd(int m[],int n)

	{

		System.out.println("hellooooo");

		int flag=0;

		int i=0;

		int j=0;

		for(i=0;i<n;i++)

		{

			for(j=0;j<n;j++)

			{

				if(i!=j)

				{

					for(int k = 2; k <= m[i] && k <= m[j]; ++k)

			        {



						if(m[i] % k==0 && m[j] % k==0)

			            {

			  

			                flag=1;

			            }

			        }

				}

				else

				{

					

					j++;

				}

			}

		}

		return flag;

		

	}

	public static void main(String[] args)

	{

		

		int i=0;

		int a[];

		a = new int[10];

		int m[];

		m = new int[10];

		int flag=0;

		

		System.out.println("\nEnter number of equations:");

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();

		System.out.println("\nEnter a array:");

		for(i=0;i<n;i++)

		{

			a[i]= sc.nextInt();

		}

		System.out.println("\nEnter m array:");

		for(i=0;i<n;i++)

		{

			m[i]= sc.nextInt();

		}

		flag = checkgcd(m,n);

		while(flag==1)

		{

			System.out.println("\nEnter coprime numbers only");

			System.out.println("\nEnter m array again:");

			for(i=0;i<n;i++)

			{

				m[i]= sc.nextInt();

			}

			flag = checkgcd(m,n);

		}

		

		

			System.out.println("\nValue of x is:"+findX(a,m,n));

		

	}

}