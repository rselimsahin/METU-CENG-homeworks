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
	public void addKey(Integer key) {
		boolean flag=false;
			for(int i=0;i<this.keys.size();i++){
				if(this.keys.get(i)>key){
					this.keys.add(i, key);
					flag=true;
					break;
				}
			}
			if(!flag){
				this.keys.add(key);
			}
	}
	public void addChildren(LibraryNode node) {
		
		this.children.add(node);
	}
	/*
	public ArrayList<LibraryNode> addSplit(ArrayList<CengBook> books, CengBook book){
		//add book to  the list
		boolean flag=false;
		for(int i=0;i<books.size();i++){
			if(books.get(i).key()>book.key()){
				books.add(i, book);
				flag=true;
				break;
			}
		}
		if(!flag){
			books.add(book);
		}
		this.keys.clear();
		this.keys.add(books.get(order).key());

		LibraryNodeLeaf nodeNew1 = new LibraryNodeLeaf(this.getParent());
		//split booklist to 2 
		for(int i=order;i<=2*order;i++){
			nodeNew1.getbooks().add(books.get(i));
		}
		for(int i=0;i<order;i++){
			this.books.add(books.get(i));
		}
		addChildren(nodeNew1);
		addChildren(nodeNew2);
		return null;
	}*/

}
