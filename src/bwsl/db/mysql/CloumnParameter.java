package bwsl.db.mysql;

/**
 * 
 * @author bowns
 * @email bowns1688@hotmail.com
 * @date 2017.01.17
 *
 */
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