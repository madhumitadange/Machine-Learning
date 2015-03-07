import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class Perceptron {
	public static void main(String args[]) throws IOException{
		long t1=System.nanoTime();
		HashMap<String, Dic> bowords = new HashMap();
		String spamtrainPath =args[0]+"\\spam\\";  //"F:\\ML Books\\train\\spam\\";//args[0];
		File foldertrainSpam = new File(spamtrainPath);
		String hamtrainPath =args[0]+"\\ham\\";;
		File foldertrainHam = new File(hamtrainPath);
		//;//args[1];
		File[] listOfSpamFiles = foldertrainSpam.listFiles();
		File[] listOfHamFiles = foldertrainHam.listFiles();
		
		String spamtestPath =args[1]+"\\spam\\";
		File foldertestSpam = new File(spamtestPath);
		String hamtestPath =args[1]+"\\ham\\";
		File foldertestHam = new File(hamtestPath);
		//System.out.println(args[1]+""+spamtestPath);
		File[] listOfSpamTestFiles = foldertestSpam.listFiles();
		File[] listOfHamTestFiles = foldertestHam.listFiles();
		
		
		File stopFile = new  File("stop.txt");
		String stopPath = "";
		
		int noSpams=foldertrainSpam.list().length; ;
		int noHams = foldertrainHam.list().length;
		int totMails = noSpams +noHams;
		double priSpam =Math.log((double)noSpams/totMails);
		double priHam =Math.log((double)noHams/totMails);
		//System.out.println(noHams+totMails+" "+priSpam);
		int spamWords=0;
		int hamWords=0;
		int nh=0,ns=0;
		double stot=0,htot=0;
		String sw="n"	;
		sw = args[4];
		int spam=0;
		// stop words
		if(sw.equalsIgnoreCase("y")){
			System.out.println("without stopwords");
				FileReader frs = new FileReader(stopFile,stopPath);
		    	String contents = frs.getContent();
		    	String[] tokensw = contents.split("\\s+");
		    	ArrayList<String> stopWords = new ArrayList();
		    	for(int i = 0; i < tokensw.length; i++) {
		    		  String word1 = tokensw[i].toLowerCase();
		    		  //word1.replaceAll("\\s+","");
		    		  stopWords.add(word1);
		    	}
		    	for (File file : listOfSpamFiles) {
				    if (file.isFile()) {
				    	FileReader fr = new FileReader(file,spamtrainPath);
				    	String content = fr.getContent();
				    	String[] tokens = content.split("\\s+");
				    	for(int i = 0; i < tokens.length; i++) {
				    		  String word = tokens[i].toLowerCase();
				    		  if (bowords.containsKey(word)) {
				    		    Dic w = (Dic) bowords.get(word);
				    		    w.countSpam();
				    		  } else {
				    			  if (!stopWords.contains((Object)(word))){
				    		    Dic w = new Dic(word);
				    		    w.countSpam();
				    		    
				    		    bowords.put(word,w);
				    		    ns++;
				    			  }
				    			  else
				    				  spamWords--;
				    		  }
				    		  spamWords++;
				    		}
				    	
				    	
				    }
				   
				}
				for (File file : listOfHamFiles) {
				    if (file.isFile()) {
				    	FileReader fr = new FileReader(file,hamtrainPath);
				    	String content = fr.getContent();
				    	String[] tokens = content.split("\\s+");
				    	for(int i = 0; i < tokens.length; i++) {
				    		  String word = tokens[i].toLowerCase();
				    		  if (bowords.containsKey(word)) {
				    		    Dic w = (Dic) bowords.get(word);
				    		    w.countHam();
				    		  } else {
				    			 if (!stopWords.contains((Object)(word))){
				    		    Dic w = new Dic(word);
				    		    w.countHam();
				    		    
				    		    bowords.put(word,w);
				    		    nh++;
				    			  }
				    			 else
				    				 hamWords--;
				    		  }
				    		  hamWords++;
				    		}
				    }
				   // System.out.println(noHams++);
				    
				}
//				 Iterator it = bowords.entrySet().iterator();
//				    while (it.hasNext()) {
//				        Map.Entry pairs = (Map.Entry)it.next();
//				        Dic word =(Dic) pairs.getValue();
//				        word.finalprobs(spamWords+ns,hamWords+nh);
//				       // System.out.println(word.getPham()+"spam "+word.getPspam());
//				        //System.out.println(pairs.getKey() + " = " + pairs.getValue().toString());
//				        //it.remove();
//				    }
		}
		else{
			System.out.println("with stopwords");
		for (File file : listOfSpamFiles) {
		    if (file.isFile()) {
		    	FileReader fr = new FileReader(file,spamtrainPath);
		    	String content = fr.getContent();
		    	String[] tokens = content.split("\\s+");
		    	for(int i = 0; i < tokens.length; i++) {
		    		  String word = tokens[i].toLowerCase();
		    		  if (bowords.containsKey(word)) {
		    		    Dic w = (Dic) bowords.get(word);
		    		    w.countSpam();
		    		  } else {
		    		    Dic w = new Dic(word);
		    		    w.countSpam();
		    		    bowords.put(word,w);
		    		    ns++;
		    		  }
		    		  spamWords++;
		    		}
		    	
		    	
		    }
		   
		}
		for (File file : listOfHamFiles) {
		    if (file.isFile()) {
		    	FileReader fr = new FileReader(file,hamtrainPath);
		    	String content = fr.getContent();
		    	String[] tokens = content.split("\\s+");
		    	for(int i = 0; i < tokens.length; i++) {
		    		  String word = tokens[i].toLowerCase();
		    		  if (bowords.containsKey(word)) {
		    		    Dic w = (Dic) bowords.get(word);
		    		    w.countHam();
		    		  } else {
		    		    Dic w = new Dic(word);
		    		    w.countHam();
		    		    bowords.put(word,w);
		    		    nh++;
		    		  }
		    		  hamWords++;
		    		}
		    }
		   // System.out.println(noHams++);
		    
		}
//		 Iterator it = bowords.entrySet().iterator();
//		    while (it.hasNext()) {
//		        Map.Entry pairs = (Map.Entry)it.next();
//		        Dic word =(Dic) pairs.getValue();
//		        word.finalprobs(spamWords+ns,hamWords+nh);
//		       // System.out.println(word.getPham()+"spam "+word.getPspam());
//		        //System.out.println(pairs.getKey() + " = " + pairs.getValue().toString());
//		        //it.remove();
//		    }
		    
		Dic w = bowords.get((Object)"filename");
		//if(w!=null)
		//System.out.println(w.getPham()+"spam "+w.getPspam());
		//System.out.println(bowords.size()+" "+priHam+" "+priSpam+" "+spamWords+" "+hamWords+" "+ns+" "+nh);
		}
		
		//Perceptron code
		
		ArrayList<String> Voc = new ArrayList();
		Voc.addAll(bowords.keySet());
		
		//System.out.println(Voc.get(113));
		int m = listOfSpamFiles.length + listOfHamFiles.length ;
		int n= Voc.size();
		//System.out.println("n="+Voc.size()+"m= "+m);
		int XMat[][]= new int[m][n+2];
		
		
		int docNo =0;
		// fill matrix
		for (File file : listOfSpamFiles) {
			
		    if (file.isFile()) {
		    	FileReader fr1 = new FileReader(file,spamtrainPath);
		    	String content1 = fr1.getContent();
		    	String[] tokens1 = content1.split("\\s+");
		    	XMat[docNo][0]=1;
		    	XMat[docNo][n+1]=1;
		    	for(int t = 0; t < tokens1.length; t++) {
		    		  String word = tokens1[t].toLowerCase();
		    		  //logic get ind word update x
		    		  //int pos = ;
		    		  XMat[docNo][Voc.indexOf(word)+1]=XMat[docNo][Voc.indexOf(word)+1]+1;
		    		  

		    	}
		    	
		    	
		    	
		    }
		    docNo++;
		   
		}
		for (File file : listOfHamFiles) {
		    if (file.isFile()) {
		    	FileReader fr1 = new FileReader(file,hamtrainPath);
		    	String content1 = fr1.getContent();
		    	String[] tokens1 = content1.split("\\s+");
		    	XMat[docNo][0]=1;
		    	XMat[docNo][n+1]=-1;
		    	for(int t = 0; t < tokens1.length; t++) {
		    		  String word = tokens1[t].toLowerCase();
		    		  XMat[docNo][Voc.indexOf(word)+1]=XMat[docNo][Voc.indexOf(word)+1]+1;
		    		 
		    		
		    		}
		    }
		    docNo++;
		   // System.out.println(noHams++);
		    
		}
		//System.out.println("Hi");
		//for(double trail =0.1;trail<0.9;trail+=0.005){
		// until convergence 
		//double a[] = {0.05,0.01,0.1,0.08,0.5};
		//int b[]={50,250,100,150,200};
//		for(int p=0;p<5;p++){
//			for(int q=0;q<5;q++){
				double pr[]= new double[m];
				Arrays.fill(pr, (1/m));
				double wt[] = new double[n+1];
				Arrays.fill(wt, 0.15);
		
		double eta =Double.parseDouble(args[2]);//a[p];//
		//double labda=Double.parseDouble(args[3]);
		int it =Integer.parseInt(args[3]);//b[q]; //Integer.parseInt(args[4]);
	
		for(int i=0;i<it;i++){
			for(int j=0;j<m;j++){
	
				//cal pr
				double pdash=wt[0];
				for(int c=1;c<=n;c++){
					pdash= pdash+wt[c]*XMat[j][c];
				}
				pr[j]=pdash>0?1:-1;
			
				double dw[] = new double[n+1];
				for(int k=0;k<n+1;k++){
					
						dw[k]=dw[k]+XMat[j][k]*(XMat[j][n+1]-pr[j]);
					
				}

				for(int k=0;k<n+1;k++){
					//wt[k]=wt[k]+eta*(dw[k]-labda*wt[k]);
					wt[k]=wt[k]+eta*(dw[k]);
//					System.out.println("wt: "+wt[k]);
				}
			}
			
		}
		
		// test regression 
		int spamCount=0;
			for (File file : listOfSpamTestFiles) {
			
		    if (file.isFile()) {
		    	FileReader fr1 = new FileReader(file,spamtestPath);
		    	String content1 = fr1.getContent();
		    	String[] tokens1 = content1.split("\\s+");
		    	double samp[]= new double[n+2];
		    	samp[0]=1;
		    	//XMat[docNo][n+1]=1;
		    	for(int t = 0; t < tokens1.length; t++) {
		    		  String word = tokens1[t].toLowerCase();
		    		  //logic get ind word update x
		    		  //int pos = ;
		    		  samp[Voc.indexOf(word)+1]=samp[Voc.indexOf(word)+1]+1;
		    		  

		    	}
		    	double wx=wt[0];
		    	
					for(int c=1;c<n+1;c++){
					wx= wx+wt[c]*samp[c];
					}
			//System.out.println(wx);
		    	if(wx>0)
		    	spamCount++;
		    	
		    }
		    
		   
		}
			System.out.println("Perceptron spam acc= "+(double)100*(spamCount)/listOfSpamTestFiles.length);
			spamCount=0;
		for (File file : listOfHamTestFiles) {
		    if (file.isFile()) {
		    	FileReader fr1 = new FileReader(file,hamtestPath);
		    	String content1 = fr1.getContent();
		    	String[] tokens1 = content1.split("\\s+");
		    	double samp[]= new double[n+2];
		    	samp[0]=1;
		    	//XMat[docNo][n+1]=1;
		    	for(int t = 0; t < tokens1.length; t++) {
		    		  String word = tokens1[t].toLowerCase();
		    		  //logic get ind word update x
		    		  //int pos = ;
		    		  samp[Voc.indexOf(word)+1]=samp[Voc.indexOf(word)+1]+1;
		    		  

		    	}
		    	double wx=wt[0];
		    	
					for(int c=1;c<n+1;c++){
					wx= wx+wt[c]*samp[c];
					}
			//System.out.println(wx);
		    	if(wx>0)
		    	spamCount++;
		    	
		    }
		 
		    
		}
		
		  System.out.println("ham acc="+(double)100*(listOfHamTestFiles.length-spamCount)/listOfHamTestFiles.length);
		
		//}
		
		
		
		System.out.println("eta="+eta+" iterations="+it);
		
	}
	public void chooseClass(){
		
		
	}
}