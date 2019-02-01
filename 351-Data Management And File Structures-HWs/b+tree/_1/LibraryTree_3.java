import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;
public class LibraryTree {
	
	public LibraryNode primaryRoot;		//root of the primary B+ tree
	public LibraryNode secondaryRoot;	//root of the secondary B+ tree
	public LibraryTree(Integer order)
	{
		LibraryNode.order = order;
		primaryRoot = new LibraryNodeLeaf(null);
		primaryRoot.level = 0;
		secondaryRoot = new LibraryNodeLeaf(null);
		secondaryRoot.level = 0;
	}
	
	public void addBook(CengBook book) {
		// add methods to fill both primary and secondary tree
		/**********************************************
		Perform a search to determine what bucket the new record should go into.
		If the bucket is not full (at most b − 1 {\displaystyle b-1} b-1 entries after the insertion), add the record.
		Otherwise, before inserting the new record
			split the bucket.
				original node has ⎡(L+1)/2⎤ items
				new node has ⎣(L+1)/2⎦ items
			Move ⎡(L+1)/2⎤-th key to the parent, and insert the new node to the parent.
			Repeat until a parent is found that need not split.
		If the root splits, treat it as if it has an empty parent and split as outline above.
		***********************************************/
		LibraryNode currentNode=primaryRoot;
		ArrayList<LibraryNode> path = new ArrayList<LibraryNode>();
		ArrayList<Integer> path_keys = new ArrayList<Integer>();
		path.add(currentNode);
		path_keys.add(0);
		while(currentNode.getType() == LibraryNodeType.Internal){
			//currentNode = queue.poll();
			LibraryNodePrimaryIndex castNode = (LibraryNodePrimaryIndex)currentNode;
			ArrayList<LibraryNode> allChildren = castNode.getAllChildren();
			ArrayList<Integer>node_keys=castNode.getkeys();
			int c=0;
			for(int i=0;i<node_keys.size();i++){
				if(node_keys.get(i)>book.key()){
					path.add(currentNode);
					path_keys.add(node_keys.get(i));
					
					currentNode=allChildren.get(i);
					c=1;
					break;
				}
			}
			if(c==1)
				continue;
			currentNode=allChildren.get(node_keys.size());
			
		}
		int order=LibraryNode.order;
		LibraryNodeLeaf castNode = (LibraryNodeLeaf)currentNode;
		ArrayList<CengBook> books= castNode.getbooks();
		//if leaf node has space
		if(books.size()<2*order){
			boolean flag=false;
			for(int i=0;i<books.size();i++){
				if(books.get(i).key()>book.key()){
					System.out.println("----------"+books.get(i).key()+"---------"+book.key());
					castNode.addBook(i, book);
					flag=true;
					break;
				}
			}
			if(!flag){
				System.out.println("___damn___");
				castNode.addBook(books.size(), book);
			}
			//castNode.addBook(0, book);
			
			for(int i = 0; i < books.size(); i++)
				System.out.print(books.get(i).fullName()+"\n");
		}
		
		// if leaf node doesnt have space
		else{
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
			LibraryNodeLeaf nodeNew1 = new LibraryNodeLeaf(castNode.getParent());
			//split booklist to 2 
			for(int i=order;i<=2*order;i++){
				nodeNew1.getbooks().add(books.get(i));
			}
			ArrayList<CengBook> nbooks= new ArrayList<CengBook>();
			for(int i=0;i<order;i++){
				nbooks.add(books.get(i));
			}
			castNode.getbooks().clear();
			castNode.getbooks().addAll(nbooks);
			//update parent's children
			if(castNode.getParent()!=null){//handle root
				int place=((LibraryNodePrimaryIndex)castNode.getParent()).addKey(nodeNew1.bookAtIndex(0).key());//addchildren updated
				((LibraryNodePrimaryIndex)castNode.getParent()).getAllChildren().add(place+1,nodeNew1);//addchildren placement
			}
			else{
				LibraryNodePrimaryIndex newRoot=new LibraryNodePrimaryIndex(null);
				newRoot.addKey(nodeNew1.bookAtIndex(0).key());
				newRoot.addChildren(primaryRoot);
				newRoot.addChildren(nodeNew1);
				nodeNew1.setParent(newRoot);
				primaryRoot.setParent(newRoot);
				primaryRoot=newRoot;
			}
			while(!((LibraryNodePrimaryIndex)currentNode.getParent()).checkKeys(order)){
				currentNode=currentNode.getParent();
				LibraryNodePrimaryIndex nodeNew = new LibraryNodePrimaryIndex(currentNode.getParent());
				LibraryNodePrimaryIndex nodeCast=(LibraryNodePrimaryIndex)currentNode;
				int m_key=nodeCast.getkeys().get(order);
				//split keylist to 2 
				for(int i=order+1;i<=2*order+1;i++){
					nodeNew.addKey(nodeCast.keyAtIndex(i-1));
					nodeNew.addChildren(nodeCast.getChildrenAt(i));
					nodeCast.getChildrenAt(i).setParent(nodeNew);
				}
				nodeNew.getkeys().remove(0);
				for(int i=order;i<=2*order;i++){
					nodeCast.getkeys().remove(nodeCast.getkeys().size()-1);
					nodeCast.getAllChildren().remove(nodeCast.getAllChildren().size()-1);
				}
				//update parent's children
				if(nodeCast.getParent()!=null){//handle root
					int place=((LibraryNodePrimaryIndex)nodeCast.getParent()).addKey(m_key);//addchildren updated
					((LibraryNodePrimaryIndex)nodeCast.getParent()).getAllChildren().add(place+1,nodeNew);//addchildren placement
				}
				else{
					System.out.println("pompompom");
					LibraryNodePrimaryIndex newRoot=new LibraryNodePrimaryIndex(null);
					newRoot.addKey(m_key);
					newRoot.addChildren(primaryRoot);
					newRoot.addChildren(nodeNew);
					nodeNew.setParent(newRoot);
					primaryRoot.setParent(newRoot);
					primaryRoot=newRoot;
					
					for(LibraryNode children: newRoot.getAllChildren()){
						if(children.type==LibraryNodeType.Internal)
						System.out.println(((LibraryNodePrimaryIndex)children).keyAtIndex(0));
					}
				}
			}
		}
		
	}
	
	
	
	public CengBook searchBook(Integer key) {//TODO should it print bucket node
		// add methods to find the book with the searched key in primary B+ tree
		// return value will not be tested, just print as the specicifications say
		LibraryNode currentNode=primaryRoot;
		ArrayList<LibraryNode> path = new ArrayList<LibraryNode>();
		ArrayList<Integer> path_keys = new ArrayList<Integer>();
		while(currentNode.getType() == LibraryNodeType.Internal){
			//currentNode = queue.poll();
			LibraryNodePrimaryIndex castNode = (LibraryNodePrimaryIndex)currentNode;
			ArrayList<LibraryNode> allChildren = castNode.getAllChildren();
			for(LibraryNode children: allChildren){
				if(children.type==LibraryNodeType.Internal)
				System.out.println(((LibraryNodePrimaryIndex)children).keyAtIndex(0));
			}
			ArrayList<Integer>node_keys=castNode.getkeys();
			int c=0;
			for(int i=0;i<node_keys.size();i++){
				if(node_keys.get(i)==key){
					path.add(currentNode);
					path_keys.add(node_keys.get(i));
					currentNode=allChildren.get(i+1);
					c=1;
					break;
				}
				if(node_keys.get(i)>key){
					path.add(currentNode);
					path_keys.add(node_keys.get(i));
					//System.out.println(key);
					currentNode=allChildren.get(i);
					//System.out.println(((LibraryNodeLeaf)currentNode).bookAtIndex(0).fullName());
					c=1;
					break;
				}
			}
			if(c==1)
				continue;
			path.add(currentNode);
			currentNode=allChildren.get(node_keys.size());
			
		}
		LibraryNodeLeaf castNode = (LibraryNodeLeaf)currentNode;
		if(castNode!=primaryRoot)
			path.add(castNode);
		else{
			path.add(currentNode);
			path_keys.add(0);}
		
		/*System.out.println("path to "+key+"\n---------------------------");
		for(int i=0;i<path_keys.size();i++){
			System.out.println(path_keys.get(i));
		}*/
		for(int i=0;i<path.size();i++){
			if(path.get(i).type==LibraryNodeType.Internal){
				LibraryNodePrimaryIndex cast = (LibraryNodePrimaryIndex)path.get(i);
				System.out.println("<index>");
				for(int key_n:cast.getkeys())
					System.out.println(key_n);
				System.out.println("</index>");
			}
			else{
				LibraryNodeLeaf cast = (LibraryNodeLeaf)path.get(i);
				System.out.println("<index>");
				for(CengBook book:cast.getbooks())
					System.out.println(book.key());
				System.out.println("</index>");
			}
		}
		
		int found=0;
		for(int i=0;i<castNode.getbooks().size();i++){
			if(castNode.getbooks().get(i).key()==key){
				found=1;
				System.out.println("<data>");
				System.out.println("<record>"+castNode.getbooks().get(i).fullName()+"</record>");
				System.out.println("</data>");		
				break;
			}
		}
		if(found==0){
			System.out.println("No match for "+key);
		}
		
		return null;
	}
	
	
	public void printPrimaryLibrary() {//TODO print order is wrong
		// add methods to print the primary B+ tree in Depth-first order
		//ArrayList<LibraryNode> path = new ArrayList<LibraryNode>();
		LibraryNode currentNode=primaryRoot;
		if(currentNode.type==LibraryNodeType.Leaf){
			
			LibraryNodeLeaf cast=(LibraryNodeLeaf)currentNode;
			printLeafNode(cast);
			return;
		}
		Stack<LibraryNode> visit=new Stack<LibraryNode>();
		visit.push(currentNode);
		while(visit.size()>0){
			currentNode=visit.pop();
			System.out.println(visit.toString());
			LibraryNodePrimaryIndex castNode=(LibraryNodePrimaryIndex)currentNode;
			System.out.println("<index>");
			for(int key_n:castNode.getkeys())
				System.out.println(key_n);
			System.out.println("</index>");
			if(castNode.getChildrenAt(0).type==LibraryNodeType.Internal){
				for(int i=castNode.getAllChildren().size()-1;i>=0;i--){
					visit.push(castNode.getChildrenAt(i));
				}
				currentNode=castNode.getChildrenAt(0);
				continue;
			}
			else{
				for(LibraryNode node: castNode.getAllChildren()){
					LibraryNodeLeaf cast=(LibraryNodeLeaf)node;
					printLeafNode(cast);
				}
			}
			
		}
	
	}
	
	public void printSecondaryLibrary() {
		
		// add methods to print the secondary B+ tree in Depth-first order
		
	}
	
	// Extra functions if needed
	public void printLeafNode(LibraryNodeLeaf node){
		ArrayList<CengBook> books=node.getbooks();
		System.out.println("<data>");
		for(int i=0;i<books.size();i++){
			System.out.println("<record>"+books.get(i).fullName()+"</record>");
		}
		System.out.println("</data>");
	}
}
