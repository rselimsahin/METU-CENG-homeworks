import java.util.ArrayList;

public class LibraryNodeSecondaryIndex extends LibraryNode
{
	private ArrayList<Integer> keys;
	private ArrayList<Integer> years;
	private ArrayList<LibraryNode> children;	
	
	public LibraryNodeSecondaryIndex(LibraryNode parent) 
	{
		super(parent);
		keys = new ArrayList<Integer>();
		years = new ArrayList<Integer>();
		children = new ArrayList<LibraryNode>();
		this.type = LibraryNodeType.Internal;
	}
	
	public LibraryNodeSecondaryIndex(LibraryNode parent, ArrayList<Integer> years, ArrayList<Integer> keys, ArrayList<LibraryNode> children) 
	{
		super(parent);
		this.years = years;
		this.keys = keys;
		this.children = children;
		this.type = LibraryNodeType.Internal;
	}
	
	// GUI Methods - Do not modify
	public ArrayList<LibraryNode> getAllChildren()
	{
		return this.children;
	}
	
	public LibraryNode getChildrenAt(Integer index) {
		
		return this.children.get(index);
	}
	
	public Integer keyCount()
	{
		return this.keys.size();
	}
	public Integer keyAtIndex(Integer index)
	{
		if(index >= this.keyCount() || index < 0)
		{
			return -1;
		}
		else
		{
			return this.keys.get(index);			
		}
	}
	
	public Integer yearAtIndex(Integer index)
	{
		if(index >= this.keyCount() || index < 0)
		{
			return -1;
		}
		else
		{
			return this.years.get(index);			
		}
	}
	
	
	// Extra functions if needed
	public ArrayList<Integer> getkeys() {
		return this.keys;
	}
	public ArrayList<Integer> getyears() {
		return this.years;
	}

	public int addIndex(Integer year, Integer key) {
		boolean flag=false;
		for(int i=0;i<this.years.size();i++){

			if(this.years.get(i)-year==0){
				int j=i;
				boolean flag_2=false;
				while(j<years.size() && this.years.get(j)-year==0 && j<years.size()){
					if(this.keys.get(j)>key){
						this.years.add(j, year);
						this.keys.add(j, key);
						flag_2=true;
						return j;
					}
					j++;
				}
				if(!flag_2){
					this.years.add(j ,year);
					this.keys.add(j ,key);
					return j;
				}
				this.years.add(i, year);
				this.keys.add(i, key);
				flag=true;
				return i;
			}
			if(this.years.get(i)>year){
				this.years.add(i, year);
				this.keys.add(i, key);
				flag=true;
				return i;
			}
		}
		if(!flag){
			this.years.add(year);
			this.keys.add(key);
		}
		return this.keys.size()-1;
	}
	public void addChildren(LibraryNode node) {
		
		this.children.add(node);
	}
	public boolean checkKeys(int order){
		if(this.keys.size()>order*2){
			return false;
		}
		else{
			return true;
		}
	}
}
