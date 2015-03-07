import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class EM {
	public static void main(String[] args) {
	    Scanner scan;
	    //double [] = new double [];
	    List<Double> data = new ArrayList<Double>();
	    
	    File file = new File("em_data.txt");
	    try {
	        scan = new Scanner(file);

	        while(scan.hasNextDouble())
	        {
	           data.add(scan.nextDouble());
	        	//System.out.println( data.size() );
	        }

	    } catch (FileNotFoundException e1) {
	            e1.printStackTrace();
	    }
	    // pxtheta
	    int K=3;
	    int zk[];
   	    double alpha[] =new double[K];
	    double muK[] =  new double[K];
	    double Ek[] = new double[K];
	    int N = data.size();
	    double pxtheta[][]=new double[N][K];
	    Arrays.fill(alpha, 1.0/K);
	    System.out.println("alpha: "+alpha[0]+"_"+alpha[1]+"_"+alpha[2]);
	  //  for(int itr=0;itr<10;itr++){
	    double logL=0;
	    for(int i = 0; i < muK.length; i++) {
	    	muK[i] = data.get((int)Math.floor(Math.random()*data.size()));
	    	
	    }
	    System.out.println("Initial muK : "+muK[0]+"_"+muK[1]+"_"+muK[2]);
	    double var=0;
	    //double sum[]=new double[K];
	    double sum=0;
	    double avg=0;
	    double Nk[]=new double[K];
	    for(int x=0;x<N;x++){
	    	
	    	avg+= data.get(x);
	    }
	    //System.out.println("sum: "+avg);
	    avg=avg/N;
	    for(int x=0;x<N;x++){
	    	sum=sum+ (data.get(x)-avg)*(data.get(x)-avg);
	    	//System.out.println("sum: "+sum);
	    }
	    	var=sum/N;
	    	//System.out.println("sum: "+avg);
	    	//System.out.println("var: "+var);
	    	
	    for(int k=0;k<K;k++){
	    	Ek[k]=var*(1+k);//1.0;
	    	//System.out.println("EK: "+Ek[k]);

	    }
	    System.out.println("Initial Ek : "+Ek[0]+"_"+Ek[1]+"_"+Ek[2]);
	    double logLnew=0;
	    double wk[][] = new double[N][K];
	    do{
	    for(int it=0;it<100;it++){
	    	logL=logLnew;
	   for(int x=0;x<N;x++){
	    for(int k=0;k<K;k++){
	    		double num1=0,num=0,den=0;
	    		num1= (-0.5)*(data.get(x)-muK[k])*(data.get(x)-muK[k])/Ek[k];
	    		num= Math.exp(num1);
	    		den=  Math.sqrt(2*Math.PI);
	    		den=den*Math.sqrt(Math.abs(Ek[k]));
	    		if(den!=0)
	    			pxtheta[x][k]= num/den;
	    		else
	    			pxtheta[x][k] = 0.0;	
//	    		System.out.println("den "+den);
	    }
	   }
	    
	    
	    for(int x=0;x<N;x++){
		    for(int k=0;k<K;k++){
		    double den=0,num=0;
		    num=pxtheta[x][k]*alpha[k];
		    for(int m=0;m<K;m++){
		    	den=den+(pxtheta[x][m]*alpha[m]);
		    }
		    //System.out.println("den "+den);
		    if(den!=0){
		    	wk[x][k]=num/den;
		    } else {
		    	wk[x][k]=0.0;
		    //System.out.println("wk "+wk[x][k]);
		    }
	    }
		    
		 }
	   
	    for(int k=0;k<K;k++){
	    	Nk[k]=0;
	    	for(int i=0;i<N;i++){
	    		Nk[k]=Nk[k]+wk[i][k];
	    	}
	    }
	   // System.out.println("nk: "+Nk[0]);
	    //update alphak
	    for(int k=0;k<K;k++){
	    	alpha[k]=Nk[k]/(double)N;
	    }
//	    System.out.println(alpha[0]);
	    
	    for(int k=0;k<K;k++){
	    	muK[k]=0;
	    	for(int i=0;i<N;i++){
	    		muK[k]=muK[k]+((wk[i][k]*data.get(i))/Nk[k]);
	    		
	    	}
	    }
	    //wk
	    //muK
	    for(int k=0;k<K;k++){
	    	Ek[k]=0;
	    	for(int i=0;i<N;i++){
	    		Ek[k]=Ek[k]+((wk[i][k]*(data.get(i)-muK[k])*(data.get(i)-muK[k]))/Nk[k]);
	    		
	    	}
	    }
// convergence step
	    logLnew=0;
	    for(int i=0;i<N;i++){
	    	double a=0;
	    	for(int k=0;k<K;k++){
	    		a=a+(alpha[k]*pxtheta[i][k]);
	    	}
	    	logLnew=logLnew+Math.log10(a);
	    	
	    }
		
		//System.out.println();
	    //System.out.println("iteration: "+it+"log Theta: "+logLnew);
	    }
	}while(logLnew!=logL);
	
	
//	for(int k=0;k<K;k++){
//		
//		System.out.println("muK: "+muK[k]);
//	}
		  System.out.println("Final muK : "+muK[0]+"_"+muK[1]+"_"+muK[2]);

	  System.out.println("Final Ek : "+Ek[0]+"_"+Ek[1]+"_"+Ek[2]);
	}
//	}
}