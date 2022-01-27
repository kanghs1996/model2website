package model.bbs;

public class MemberInterestDTO {
	private int no;
	private String id;
	private String interest = "";
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest += interest + " ";
	}	
	
}
