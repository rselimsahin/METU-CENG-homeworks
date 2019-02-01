import java.util.ArrayList;

public class LibraryNodePrimaryIndex extends LibraryNode
{
	private ArrayList<Integer> keys;
	private ArrayList<LibraryNode> children;	
	
	public LibraryNodePrimaryIndex(LibraryNode parent) 
	{
		super(parent);
		keys = new ArrayList<Integer>();
		children = new ArrayList<LibraryNode>();
		this.type = LibraryNodeType.Internal;
	}
	
	public LibraryNodePrimaryIndex(LibraryNode parent, ArrayList<Integer> keys, ArrayList<LibraryNode> children) 
	{
		super(parent);
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
	
	// Extra functions if needed
	public ArrayList<Integer> getkeys() {
		return this.keys;
	}
	public int addKey(Integer key) {
		boolean flag=false;
		for(int i=0;i<this.keys.size();i++){
			if(this.keys.get(i)>key){
				this.keys.add(i, key);
				flag=true;
				return i;
			}
		}
		if(!flag){
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
	/*public void addChildren(LibraryNode node, boolean mode) {
		int key=-1;
		while(key==-1){
			if(node.type==LibraryNodeLeaf){
				key=((LibraryNodeLeaf)node).bookAtIndex(0).key();
			}
			else{
				node=((LibraryNodePrimaryIndex)node).getChildrenAt(0);
			}
		}
		if(this.keys.get(0)>key){
			this.children.add(0,node);
			LibraryNode node_old=this.children.get(1);
			int newkey=-1;
			while(newkey==-1){
				if(node_old.type==LibraryNodeLeaf){
					newkey=((LibraryNodeLeaf)node_old).bookAtIndex(0).key();
				}
				else{
					node_old=((LibraryNodePrimaryIndex)node_old).getChildrenAt(0);
				}
			}
			this.keys.add(0, newkey);
		}
		boolean flag=false;
		for(int i=1;i<this.keys.size();i++){
			if(this.keys.get(i)>key){
				this.children.add(i, node);
				flag=true;
				break;
			}
		}
		if(!flag){
			this.children.add(node);
		}
	}*/
}
