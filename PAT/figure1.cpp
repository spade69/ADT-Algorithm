#include<iostream>
using namespace std;

int main()
{
	int t=0,a=0,b=0,c=0;
	cin>>t;
	int i=1;
	while(t>0)
	{
		cin>>a;
		cin>>b;
		cin>>c;
		if(a+b>c)
		cout<<"Case #"<<i<<": true"<<endl;
		else
		cout<<"Case #"<<i<<": false"<<endl;
		t--;
		i++;

	}
	return 0;
}
