class Ratings{
	  private String word;    
	  private int countMovie;   
	  private int countHam;  
	  private double viAvg=0;     
	  private double pHam;
	  private double sumRate =0;
	  //private double pSpam;    
	  
	  public Ratings(String s) {
	    word = s;
	    countMovie= 0;
	    countHam = 1;
	    viAvg = 0.0f;
	    pHam = 0.0f;
	   // pSpam = 0.0f;
	  }
	  public void countMovie() {
		    countMovie++;
		  }
	  public void countHam() {
		    countHam++;
		  }
	public void finalprobs(int sp,int h) {
		viAvg = Math.log((double)countMovie/sp);
	    pHam =  Math.log((double)countHam /h);
		
	}
	public double getviAvg() {
		return viAvg;
	}

	public double getPham() {
		return pHam;
	}
	public int getCham() {
		return countHam;
	}
	public int getCMovie() {
		return countMovie;
	}
	


	public String getWord() {
		return word;
	}
	public void addRating(double parseDouble) {
		// TODO Auto-generated method stub
		sumRate= sumRate +parseDouble;
		
		
	}
	public void setViavg() {
		// TODO Auto-generated method stub
		viAvg = sumRate/countMovie;
		
	}
	  
}