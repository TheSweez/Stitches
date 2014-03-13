
public class Recovery extends Locale {

	public Recovery(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	// Getters and Setters
	    public String getRecoveryBed() {
	        return RecoveryBed;
	    }
	    public void setRecoveryBed(String RecoveryBed) {
	        this.RecoveryBed = RecoveryBed;
	    }
	
	
	    @Override
	    
	    public String toString() {
	        return "Operation..." + super.toString() + " RecoveryBed=" + this.RecoveryBed;
	    }
	
	    //
	    // Private
	    //
	    private String RecoveryBed;
		
}
