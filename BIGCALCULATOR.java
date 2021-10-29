import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class BIGCALCULATOR{
public static boolean compare(String numb1,String numb2)
	{
	    if(numb1.length()<numb2.length())
	        return  false;
	    else if(numb1.length()>numb2.length())
	        return true;
	    else
	        return numb1.compareTo(numb2)>0;
	}
static int max(int a,int b)
{
	if(a>b)
		return a;
	else return b;
}
static String ADD(String numb1,String numb2) {
	String temp="";
	int temp1=0,temp2=0;
	int a=1,b=1;
	int len1=numb1.length();
	int len2=numb2.length();
	int carry=0;
	for(int i=len1-1;i>=0;i--) {
		if(numb1.charAt(i)=='.') {
			a=len1-1-i;
			numb1=numb1.replace(".", "");
			temp1=a;
			break;
		}else temp1=0;
	}
	for(int i=len2-1;i>=0;i--) {
		if(numb2.charAt(i)=='.') {
			b=len2-1-i;
			numb2=numb2.replace(".", "");
			temp2=b;
			break;
		}else temp2=0;
	}
	numb1=numb1.concat("0".repeat(max(a,b)-temp1));
	numb2=numb2.concat("0".repeat(max(a,b)-temp2));
	len1=numb1.length();
	len2=numb2.length();
	if(compare(numb1,numb2)) {
		String t=numb1;
		numb1=numb2;
		numb2=t;
	}
	int diff=len2-len1;
	for(int i=len1-1;i>=0;i--) {
		int sum=((int)(numb1.charAt(i)-'0')+(int)(numb2.charAt(i+diff)-'0')+carry);
		temp+=(char)(sum%10+'0');
		carry=sum/10;
	}
	for(int i=diff-1;i>=0;i--){
		int sum=((int)(numb2.charAt(i)-'0')+carry);
		temp+=(char)(sum%10+'0');
		carry=sum/10;
	}
	if(carry>0) {
		temp+=(char)(carry+'0');
	}
	temp=new StringBuilder(temp).insert(max(a,b), ".").toString();
	return new StringBuilder(temp).reverse().toString();
}

static String SUB(String numb1,String numb2) {
	int temp1=0,temp2=0;
	int a=1,b=1;
	int len1=numb1.length();
	int len2=numb2.length();
	for(int i=len1-1;i>=0;i--) {
		if(numb1.charAt(i)=='.') {
			a=len1-1-i;
			numb1=numb1.replace(".", "");
			temp1=a;
			break;
		}else temp1=0;
	}
	for(int i=len2-1;i>=0;i--) {
		if(numb2.charAt(i)=='.') {
			b=len2-1-i;
			numb2=numb2.replace(".", "");
			temp2=b;
			break;
		}else temp2=0;
	}
	numb1=numb1.concat("0".repeat(max(a,b)-temp1));
	numb2=numb2.concat("0".repeat(max(a,b)-temp2));
	char sign='+';// The sign  
    if(!compare(numb1,numb2))
    {
    	//if a>b a-b else -(b-a)
    	sign='-';
        String t = numb2;
        numb2 = numb1;
        numb1 = t;
    }
	len1=numb1.length()-1;
	len2=numb2.length()-1;
    char ch1[] = numb1.toCharArray();
    char ch2[] = numb2.toCharArray();
    StringBuilder sb=new StringBuilder();
    int borrow=0;
    while (len1>=0||len2>=0)
    {
        int n1=len1>=0?(ch1[len1--]-'0'):0;
        int n2=len2>=0?(ch2[len2--]-'0'):0;

        int numb=n1-n2-borrow;
        borrow=0;
        if(numb<0)
        {
            borrow=1;
            numb+=10;
        }
        sb.append(numb);
    }
    sb=sb.insert(max(a,b), ".");
    sb=sb.reverse();//flip
    int index = 0;
    while (index<sb.length()&&sb.charAt(index) == '0')
    {
        index++;
    }
    if(index==sb.length())
        return "0";
    if(sign=='+')// If there is a positive number  
        return  sb.substring(index);
    else  return sign+sb.substring(index);// Negative numbers need to return 
}
static BigDecimal MUL(String numb1,String numb2) {
	BigDecimal a=new BigDecimal(numb1);
	BigDecimal b=new BigDecimal(numb2);
	BigDecimal mul=a.multiply(b);//Rounds to the nearest value
	return mul;
}
static BigDecimal DIV(String numb1,String numb2) {
	BigDecimal a=new BigDecimal(numb1);
	BigDecimal b=new BigDecimal(numb2);
	BigDecimal div=a.divide(b,3,RoundingMode.HALF_DOWN);//Rounds to the nearest value
	return div;
}
public static void  main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("ADD, SUB, MUL,DIV");
		while(true) {
			System.out.println("Your choice: ");
			System.out.println("1.ADD ");
			System.out.println("2.SUB ");
			System.out.println("3.MUL ");
			System.out.println("4.DIV ");
			Scanner sc =new Scanner(System.in);
			int choice=sc.nextInt();
			System.out.print("Fisrt number:");
			String numb1=sc.next();
			System.out.print("Second number:");
			String numb2=sc.next();
			switch (choice) {
			case 1:
				System.out.print("ADD: ");
				System.out.println(numb1+"+"+numb2+"="+ ADD(numb1,numb2));
				break;
			case 2:
				System.out.print("SUB: ");
				System.out.println(numb1+"-"+numb2+"="+ SUB(numb1,numb2));
				break;
			case 3:
				System.out.print("MUL: ");
				System.out.println(numb1+"*"+numb2+"="+ MUL(numb1,numb2));
				break;
			case 4:
				System.out.print("DIV: ");
				System.out.println(numb1+"/"+numb2+"="+ DIV(numb1,numb2));
				break;	
			default:
				break;
			}
		}
		
	}

}