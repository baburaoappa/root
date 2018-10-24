#include<iostream>
using namespace std ;
#ifndef RSA_H_
#define RSA_H_

#define SIZE 50

class RSA
{
	public :
		int p ;
		int q ;
		int array[SIZE] ;

	public:
		RSA();
		virtual ~RSA();
		void getdata( void ) ;
		void putdata( void ) ;
		void rsa( void ) ;
		void select_e_by_euclidean( int phin ) ;
		int calculate_d_by_extended_euclidean( int phin , int e ) ;
		int power( int plain_text , int e ) ;
		int plain_text_to_cipher_text( int plain_text , int e , int n ) ;
		int decryption( int cipher_text , int d , int n ) ;
		void cipher_text_to_plain_text( int cipher_text , int d , int n ) ;

};

#endif /* RSA_H_ */
