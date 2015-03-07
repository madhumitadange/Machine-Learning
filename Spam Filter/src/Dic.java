class Dic{
	  private String word;    
	  private int countSpam;   
	  private int countHam;  
	  private double pSpam;     
	  private double pHam;    
	  //private double pSpam;    
	  
	  public Dic(String s) {
	    word = s;
	    countSpam = 1;
	    countHam = 1;
	    pSpam = 0.0f;
	    pHam = 0.0f;
	   // pSpam = 0.0f;
	  }
	  public void countSpam() {
		    countSpam++;
		  }
	  public void countHam() {
		    countHam++;
		  }
	public void finalprobs(int sp,int h) {
		pSpam = Math.log((double)countSpam/sp);
	    pHam =  Math.log((double)countHam /h);
		
	}
	public double getPspam() {
		return pSpam;
	}

	public double getPham() {
		return pHam;
	}
	public int getCham() {
		return countHam;
	}
	public int getCspam() {
		return countSpam;
	}
	


	public String getWord() {
		return word;
	}
	  
}