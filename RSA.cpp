#include "RSA.h"

RSA::RSA()
{
	// TODO Auto-generated constructor stub

	p = 0 ;
	q = 0 ;

	int i = 0 ;

	for( i = 0 ; i < SIZE ; i++ )
	{
		array[i] = 0 ;
	}
}

RSA::~RSA()
{
	// TODO Auto-generated destructor stub
}

void RSA :: getdata( void )
{
	cout << " \n\t Enter value of p : " ;
	cin >> p ;

	cout << " \n\t Enter value of q : " ;
	cin >> q ;
}

void RSA :: putdata( void )
{
	cout << " \n\n\t p : " << p ;

	cout << " \n\n\t q : " << q ;
}

void RSA :: rsa( void )
{
	int n = 0 ;
	int phin = 0 ;
	int e = 0 ;
	int d = 0 ;
	int plain_text = 0 ;
	int cipher_text = 0 ;

	n = p * q ;

	cout << " \n\n\t n : " << n ;

	phin = (p-1) * (q-1) ;

	cout << " \n\n\t phin : " << phin ;

	cout << " \n\n\t e values can be : " ;

	select_e_by_euclidean( phin ) ;

	cout << " \n\n\t select e from above given set : " ;
	cin >> e ;

	d = calculate_d_by_extended_euclidean( phin , e ) ;

	cout << " \n\n\t ____________________________________________________________________" ;

	cout << " \n\n\t Enter plain text : " ;
	cin >> plain_text ;

	cipher_text = plain_text_to_cipher_text( plain_text , e , n ) ;

	cipher_text_to_plain_text( cipher_text , d , n ) ;
}

void RSA :: select_e_by_euclidean( int phin )
{
	int i = 0 ;
	int n1 = 0 ;
	int n2 = 0 ;
	int r = 0 ;

	for( i = 2 ; i < phin ; i++ )
	{
		n1 = i ;
		n2 = phin ;

		while( n1 != 1 && n2 != 0 )
		{
			r = n1 % n2 ;
			n1 = n2 ;
			n2 = r ;
		}

		if( n1 == 1 )
		{
			cout << i << " , ";
		}
	}
}

int RSA :: calculate_d_by_extended_euclidean( int phin , int e )
{
	int a = 0 ;
	int b = 0 ;
	int r = 0 ;
	int d = 0 ;
	int t = 0 ;
	int t1 = 0 ;
	int t2 = 1 ;

	a = phin ;
	b = e ;

	if( a != 0 && b != 0 )
	{
		cout << " \n\n\t --------------------------------------------------------- " ;
		cout << " \n\t | q \t | n1 \t | n2 \t | r \t | t1 \t | t2 \t | t \t | " ;
		cout << " \n\t ========================================================= " ;
	}

	while( phin != 1 )
	{
		q = phin / e ;
		r = phin % e ;

		t = t1 - ( q * t2 ) ;

		cout << " \n\t | " << q << " \t | " << phin << " \t | " << e << " \t | " << r << " \t | " << t1 << " \t | " << t2 << " \t | " << t << " \t | ";
		cout << " \n\t --------------------------------------------------------- " ;

		phin = e ;
		e = r ;
		t1 = t2 ;
		t2 = t ;
	}

	if( a != 0 && b != 0 )
	{
		cout << " \n\t | " << q << " \t | " << phin << " \t | " << e << " \t | " << r << " \t | " << t1 << " \t | " << t2 << " \t | " << t << " \t | ";
		cout << " \n\t --------------------------------------------------------- " ;
	}

	if( phin == 1 )
	{
		//cout << " \n\n\t GCD of " << a << " and " << b <<" is : " << phin ;
		//cout << " \n\t and hence multiplicative inverse extist!!! " ;

		if( t1 < 1 )
		{
			cout << " \n\n\t Value of d is " << t1+a ;

			d = t1+a ;
		}
		else
		{
			cout << " \n\n\t value of d is " << t1 ;

			d = t1 ;
		}
	}
	else
	{
		//cout << " \n\n\t GCD : " << phin ;
		//cout << " and hence multiplicative inverse does not extist... " ;
	}

	return d ;
}

int RSA :: power( int plain_text , int e )
{
	int i = 0 ;
	int cipher_text = 1 ;

	for( i = 0 ; i < e ; i++ )
	{
		cipher_text = cipher_text * plain_text ;
	}

	return cipher_text ;
}

int RSA :: plain_text_to_cipher_text( int plain_text , int e , int n )
{
	int cipher_text = 0 ;

	cipher_text = power( plain_text , e ) ;

	cipher_text = cipher_text % n ;

	cout << "\n\n\t ____________________________________________________________________" ;

	cout << " \n\n\t After encryption : " ;

	cout << " \n\n\t cipher text : " << cipher_text ;

	return cipher_text ;
}

int RSA :: decryption( int cipher_text , int d , int n )
{
	int r = 0 ;
	int k = 0 ;
	int i = 0 ;
	int sum = 1 ;
	int array2[SIZE] = {0} ;
	int array3[SIZE] = {0} ;

	while( d >= 1 )	//decimal to binary conversion
	{
		r = d % 2 ;

		d = d / 2 ;

		array[k] = r ;

		k++ ;
	}

	for( i = 0 ; i < k ; i++ )	//to obtain power of 2 and to remove 0
	{
		if( array[i] == 1 )
		{
			array2[i] = power( 2 , i ) ;
		}
	}

	for( i = 0 ; i < k ; i++ )
	{
		if( i == 0 )
		{
			array3[i] = power( cipher_text , array2[i] ) % n ;
		}
		else
		{
			array3[i] = ( array3[i-1] * array3[i-1] ) % n ;
		}
	}

	for( i = 0 ; i < k ; i++ )
	{
		if( array[i] == 1 )
		{
			sum = sum * array3[i] ;
		}
	}

	sum = sum % n ;

	return sum ;
}

void RSA :: cipher_text_to_plain_text( int cipher_text , int d , int n )
{
	int plain_text = 0 ;

	//plain_text = power( cipher_text , d ) ;

	plain_text = decryption( cipher_text , d , n ) ;

	cout << " \n\n\t ____________________________________________________________________" ;

	cout << " \n\n\t After decryption : " ;


	cout << " \n\n\t plain text : " << plain_text ;

}


