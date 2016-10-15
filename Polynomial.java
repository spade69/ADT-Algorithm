import java.util.*;

public class Polynomial{
    poly head,rear;
    public class poly{
        int exp=0;
        int cof=0;
        poly next=null;
        poly(int cof,int exp){
            this.exp=exp;
            this.cof=cof;
        }
    }
    public Polynomial(){
        head=new poly(0,0);
        rear=head;
        //head.next=null;
    }
    public int compare(int a,int b){
        if(a<b) return 1;
        else if(a>b) return -1;
        else return 0;
    }
    //Link a new data to polynomial node
    public void Attach(poly node){
        if(node!=null){
            poly tmp=new poly(node.cof,node.exp);
            rear.next=tmp;
            rear=rear.next;//node
        }
        //return rear;
    }
    public void printPoly(poly node){
        if(node.next==null)
            return;
        while(node!=null){
            System.out.printf("%d %d\n",node.cof,node.exp);
            node=node.next;
        }
    }

    public poly createPoly(int[] arCof,int[] arExp){
        if(arCof.length!=arExp.length)
            return null;
        int len=arCof.length;
        poly head=new poly(0,0);
        poly result=head;
        for(int i=0;i<len;i++){
            poly tmp=new poly(arCof[i],arExp[i]);
            result.next=tmp;
            result=result.next;
        }
        return head.next;
    }

    public void PolyAdd(poly p1,poly p2){
        int sum;
        while(p1!=null&&p2!=null){
            switch(compare(p1.exp,p2.exp)){
                case 1:
                    Attach(p1);
                    p1=p1.next;break;
                case -1:
                    Attach(p2);
                    p2=p2.next;break;
                case 0:
                    sum=p1.cof+p2.cof;
                    if(sum!=0){
                        poly tmp=new poly(sum,p1.exp);
                        Attach(tmp);
                        p1=p1.next;
                        p2=p2.next;break;
                    }
            }
        //if p1==null or p2==null
        }
        for(;p1!=null;p1=p1.next) Attach(p1);
        for(;p2!=null;p2=p2.next) Attach(p2);
        rear.next=null;

        //return head;
    }
    public static void main(String[] args){
        Polynomial tp=new Polynomial();//create head
        poly px1=tp.new poly(2,3);
        poly px2=tp.new poly(2,4);
        poly result;
        //Scanner s=new Scanner(System.in);
        //int exp=s.nextInt();
        //int cof=s.nextInt();
        //System.out.printf("%d %d",exp,cof);
        int[] arCof={1,2,3,5,6,2,34,10};
        int[] xarCof={3,2,3,8,1,10,5,2};
        int[] arExp={0,1,2,3,4,5,6,7};
        int[] xarExp={0,1,2,3,4,5,6,7};
        poly p2=tp.createPoly(arCof,arExp);
        poly p1=tp.createPoly(xarCof,xarExp);
        //tp.printPoly(p2);
        //tp.printPoly(p1);
        //tp.Attach(px1);
        //tp.Attach(px2);


        tp.PolyAdd(p1,p2);
        tp.printPoly(tp.head);
    }
}
