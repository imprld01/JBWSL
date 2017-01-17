package bwsl.db.mysql;

public class CloumnParameter {

	private String cloumnName;
	private String columnType;
	
	public CloumnParameter(String cloumnName, String columnType) {
		
		this.cloumnName = cloumnName;
		this.columnType = columnType;
	}
	
	@Override
	public String toString(){
		
		return cloumnName + " " + columnType;
	}
}