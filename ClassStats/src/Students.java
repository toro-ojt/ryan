
public class Students {

	private String fname;
	private String lname;
	private int essay1;
	private int test1;
	private int essay2;
	private int test2;
	private int fgrade;
	
	
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public int getEssay1() {
		return essay1;
	}
	public void setEssay1(int essay1) {
		this.essay1 = essay1;
	}
	public int getTest1() {
		return test1;
	}
	public void setTest1(int test1) {
		this.test1 = test1;
	}
	public int getEssay2() {
		return essay2;
	}
	public void setEssay2(int essay2) {
		this.essay2 = essay2;
	}
	public int getTest2() {
		return test2;
	}
	public void setTest2(int test2) {
		this.test2 = test2;
	}
	public int getFgrade() {
		return fgrade;
	}
	public void setFgrade(int fgrade) {
		this.fgrade = fgrade;
	}
	
	public int getfinalGrade() {
		return (essay1 + test1 + essay2 + test2);
	}
}
