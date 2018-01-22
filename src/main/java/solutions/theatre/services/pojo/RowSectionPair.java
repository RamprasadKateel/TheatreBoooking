package solutions.theatre.services.pojo;

/**
 * @author Ram
 * Very important bean class to maintain the ROw Section pair and also implements Comparable
 */
public class RowSectionPair implements Comparable<RowSectionPair>{
	private int row;
	
	
	private int section;
	
	public RowSectionPair(int row,int section){
		this.row = row;
		this.section = section;
	}
	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getSection() {
		return section;
	}
	public void setSection(int section) {
		this.section = section;
	}
	@Override
	public int compareTo(RowSectionPair otherRowSect) {
		return this.row-otherRowSect.row == 0?this.section-otherRowSect.section:this.row-otherRowSect.row;
	}
}
