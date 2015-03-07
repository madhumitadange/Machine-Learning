import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class Netfix{
	public static void main(String args[]) throws IOException{
	File titleFile = new  File("movie_titles.txt");
	String titlePath = "";
	System.out.println("movie titles");
	FileReader frs = new FileReader(titleFile,titlePath);
	String contents = frs.getContent();
	String[] tokens = contents.split("\n");
	//String[] tokens = tokensw.split("\n");
	//List<List<String>> movies = new ArrayList<List<String>>();
	ArrayList<String[]> movie = new ArrayList<String[]>();
	for(int i = 0; i < tokens.length; i++) {
		  String line1 = tokens[i];//.toLowerCase();
		  String[] row = line1.split(",");
//		  for(int j = 0; j < row.length; j++) {
//			  String word = row[j];
//			  movie.add(word);
//		  }
		  //word1.replaceAll("\\s+","");
		  movie.add(row);
	}
	String t =movie.get(1)[2];
	System.out.println(t);
	File trainFile = new  File("TrainingRatings.txt");
	String trainPath = "";
	System.out.println("train");
	FileReader fr = new FileReader(trainFile,trainPath);
	String content = fr.getContent();
	String[] token = content.split("\n");
	//String[] tokens = tokensw.split("\n");
	//List<List<String>> movies = new ArrayList<List<String>>();
	ArrayList<String[]> trainRate = new ArrayList<String[]>();
	HashMap<String,HashMap<String,Double>> train = new HashMap<String, HashMap<String,Double>>();
	for(int i = 0; i < token.length; i++) {
		  String line1 = token[i];//.toLowerCase();
		  String[] row = line1.split(",");
		  if(train.containsKey(row[1])){
			  HashMap<String, Double> movies=  train.get(row[1]);
			  movies.put(row[0], Double.parseDouble(row[2]));
		  }
		  else{
		  HashMap<String, Double> movies = new HashMap<String, Double>();
		// Do what you want to do with val
		movies.put(row[0], Double.parseDouble(row[2]));
		
		train.put(row[1], movies);
		  }
//		  for(int j = 0; j < row.length; j++) {
//			  String word = row[j];
//			  movie.add(word);
//		  }
		  //word1.replaceAll("\\s+","");
		  trainRate.add(row);
	}
	String t1 =trainRate.get(1)[2];
	System.out.println(train.size());
	File testFile = new  File("TestingRatings.txt");
	String testPath = "";
	System.out.println("test");
	FileReader frt = new FileReader(testFile,testPath);
	String contentt = frt.getContent();
	String[] tokent = contentt.split("\n");
	HashMap<String,HashMap<String,Double>> test = new HashMap<String, HashMap<String,Double>>();
	ArrayList<String[]> testRate = new ArrayList<String[]>();
	for(int i = 0; i < tokent.length; i++) {
		  String line1 = tokent[i];//.toLowerCase();
		  String[] row = line1.split(",");
		  if(test.containsKey(row[1])){
			  HashMap<String, Double> movies=  test.get(row[1]);
			  movies.put(row[0], Double.parseDouble(row[2]));
		  }
		  else{
		  HashMap<String, Double> movies = new HashMap<String, Double>();
		// Do what you want to do with val
		movies.put(row[0], Double.parseDouble(row[2]));
		
		test.put(row[1], movies);
		  }
//		  for(int j = 0; j < row.length; j++) {
//			  String word = row[j];
//			  movie.add(word);
//		  }
		  //word1.replaceAll("\\s+","");
		  testRate.add(row);
	}
	//double tt1 =Double.parseDouble(testRate.get(100477)[2]);
	//System.out.println(tt1+"  "+trainRate.size());
	HashMap<String,Ratings> viAv = new HashMap<String, Ratings>();
	List<Double> viAvg = new ArrayList<Double>();
	for(int i = 0; i < trainRate.size(); i++) {
		String userId =trainRate.get(i)[1];
		 if (viAv .containsKey(userId)) {
 		    Ratings user = (Ratings) viAv.get(userId);
 		    user.countMovie();
 		    user.addRating(Double.parseDouble(trainRate.get(i)[2]));
 		  } else {
 			  
 		    Ratings user = new Ratings(userId);
 		    user.countMovie();
		    user.addRating(Double.parseDouble(trainRate.get(i)[2]));
 		    viAv.put(userId,user);
 		    
 		  }
		
	}
	 Iterator it = viAv.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        Ratings user =(Ratings) pairs.getValue();
	        user.setViavg();
	       // System.out.println(word.getPham()+"spam "+word.getPspam());
	        //System.out.println(pairs.getKey() + " = " + pairs.getValue().toString());
	        //it.remove();
	    }
	    System.out.println(viAv.get("208724").getviAvg());
	    ArrayList<String> trainUsers = new ArrayList();
	    trainUsers.addAll(viAv.keySet());
	    int m = trainUsers.size() ;
		int n= movie.size();
		
		HashMap<String,Ratings> viAvTest = new HashMap<String, Ratings>();
		//List<Double> viAvg = new ArrayList<Double>();
		for(int i = 0; i < testRate.size(); i++) {
			String userId =testRate.get(i)[1];
			 if (viAvTest .containsKey(userId)) {
	 		    Ratings user = (Ratings) viAvTest.get(userId);
	 		    user.countMovie();
	 		    user.addRating(Double.parseDouble(testRate.get(i)[2]));
	 		  } else {
	 			  
	 		    Ratings user = new Ratings(userId);
	 		    user.countMovie();
			    user.addRating(Double.parseDouble(testRate.get(i)[2]));
	 		    viAvTest.put(userId,user);
	 		    
	 		  }
			
		}
		 Iterator it1 = viAvTest.entrySet().iterator();
		    while (it1.hasNext()) {
		        Map.Entry pairs = (Map.Entry)it1.next();
		        Ratings user =(Ratings) pairs.getValue();
		        user.setViavg();
		       // System.out.println(word.getPham()+"spam "+word.getPspam());
		        //System.out.println(pairs.getKey() + " = " + pairs.getValue().toString());
		        //it.remove();
		    }
		    ArrayList<String> testUsers = new ArrayList();
		    testUsers.addAll(viAvTest.keySet());
		    double MAPE=0 ;
		    double RMSE=0;
		    int count =0;
		    for(String userId:test.keySet()){
		    	
		    	HashMap<String, Double> testMovie=test.get(userId);
		    	for(String movieId:testMovie.keySet()) {
		    		double prob=0;
		    		
		    		//Va
		    		Ratings r= viAvTest.get(userId);
				    double va= r.getviAvg();
		    		
				    double k=0;
				    double sumOfWeights=0,sum=0;
				    for(String trainUserId: train.keySet()) {
					    HashMap<String, Double> trainMovie=train.get(trainUserId);
					    
					    double diff=0;
					    Double vij=trainMovie.get(movieId);
					    if(vij!=null) {
					    	Ratings r1= viAv.get(trainUserId);
						    double vi= r1.getviAvg();
					    	
					    	diff=vij.floatValue()-vi;
					    }
					    
					    double weight=0;
					    double num=0,den1=0,den2=0;
					    for(String trainMovieId: trainMovie.keySet()){
					    	
					    	double a=0,b=0;
					    	
					    	if(testMovie.containsKey(trainMovieId)) {
					    		Double vaj=testMovie.get(trainMovieId);
					    		
					    		a=vaj-va;
					    	
					    		Ratings r1= viAv.get(trainUserId);
							    double vi= r1.getviAvg();
							    //System.out.println("vij= "+vij+" vi= "+vi);
							    if(vij!=null) {
					    		b=vij-vi;
					    	
							    }
							    num+=a*b;
							    
							    den1+=a*a;
							    		
							    den2+=b*b;
					    	}
					    }
					    
					    if(num!=0 && den1!=0 && den2!=0) {
					    	weight=num/Math.sqrt(den1*den2);
					    }
					    
					    if(weight<0){sumOfWeights+=-weight;} else{sumOfWeights+=weight;}
					    
					    if(weight!=0 && diff!=0) {
					    	sum+=(weight*diff);
					    	
					    }
					    
				    }
				    
				    if(sumOfWeights!=0){
				    k=1/sumOfWeights;}
				    
				    prob=va+k*sum;
				    //System.out.println("prob ="+prob+"actual= "+testMovie.get(movieId));  
				    count++;
				    if((prob-testMovie.get(movieId))<0){
				    MAPE+=-(prob-testMovie.get(movieId))/(prob);
				    
				    }
				    else
				    	MAPE+=(prob-testMovie.get(movieId))/(prob);
				    RMSE+=(prob-testMovie.get(movieId))*(prob-testMovie.get(movieId));
				    double error =100*MAPE/count;
				    double rootError =100*Math.sqrt(RMSE/count);
				    System.out.println("MAPE= "+MAPE+" MeanAbsError= "+error+" Root mean Squared Error= "+ rootError+" count= "+count);
				   //// System.out.println("prob= "+prob+" act= "+testMovie.get(movieId)+" movieId "+movieId);
		    	}
		    	
		    }
//calculate weights
		 
	
	}
	
}
