import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
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
		1) Initialize x as root.
		2) While x is not leaf, do following
		..a) Find the child of x that is going to to be traversed next.
				Let the child be y.
		..b) If y is not full, change x to point to y.
		..c) If y is full, split it and change x to point 
			to one of the two parts of y. If k is smaller 
			than mid key in y, then set x as first part of y.
			Else second part of y. When we split y, we move a 
			key from y to its parent x.
		3) The loop in step 2 stops when x is leaf. x must have space for 
			1 extra key as we have been splitting all nodes in advance. 
			So simply insert k to x. 
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
					continue;
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
		// if node is internal
		else if(currentNode.type==LibraryNodeType.Internal){

		}
		// if node is root
		else if(currentNode==primaryRoot){
			LibraryNodePrimaryIndex nodeNew0 = new LibraryNodePrimaryIndex(null) ;
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
			nodeNew0.addKey(books.get(order).key());

			LibraryNodeLeaf nodeNew1 = new LibraryNodeLeaf(nodeNew0);
			LibraryNodeLeaf nodeNew2 = new LibraryNodeLeaf(nodeNew0);
			//split booklist to 2 children
			for(int i=0;i<order;i++){
				nodeNew1.addBook(i, books.get(i));
			}
			for(int i=order;i<=2*order;i++){
				System.out.println(i);
				nodeNew2.getbooks().add(books.get(i));
			}
			nodeNew0.addChildren(nodeNew1);
			nodeNew0.addChildren(nodeNew2);
			primaryRoot=nodeNew0;
			primaryRoot.level=0;
		}
		else if(currentNode.type==LibraryNodeType.Leaf){
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
			LibraryNodeLeaf nodeNew1 = new LibraryNodeLeaf(castNode.getParent());
			//split booklist to 2 
			for(int i=order;i<=2*order;i++){
				nodeNew1.getbooks().add(books.get(i));
			}
			System.out.println("tatatatatata");
			ArrayList<CengBook> nbooks= new ArrayList<CengBook>();
			for(int i=0;i<order;i++){
				nbooks.add(books.get(i));
			}
			castNode.getbooks().clear();
			castNode.getbooks().addAll(nbooks);
			//update parent's children
			((LibraryNodePrimaryIndex)castNode.getParent()).addChildren(nodeNew1);
			((LibraryNodePrimaryIndex)castNode.getParent()).addKey(nodeNew1.bookAtIndex(0).key());
			
			LibraryNodePrimaryIndex nodeParent = (LibraryNodePrimaryIndex)currentNode.getParent();
			if(nodeParent.getkeys().size()>2*order && nodeParent==primaryRoot){
				System.out.println("root overflow");
				ArrayList<LibraryNode> children = nodeParent.getAllChildren();
				for(LibraryNode child : children){
					LibraryNodeLeaf castChild = (LibraryNodeLeaf)child;
					System.out.println(castChild.bookAtIndex(0).key());
				}
				ArrayList<Integer> keys = nodeParent.getkeys();
				LibraryNodePrimaryIndex newUpNode = new LibraryNodePrimaryIndex(currentNode.getParent());
				LibraryNodePrimaryIndex newMidNode1 = new LibraryNodePrimaryIndex(newUpNode);
				LibraryNodePrimaryIndex newMidNode2 = new LibraryNodePrimaryIndex(newUpNode);
				newUpNode.addKey(keys.get(order));
				newUpNode.addChildren(newMidNode1);
				newUpNode.addChildren(newMidNode2);
				int i=0;
				for(;i<order+1;i++){
					newMidNode1.addChildren(children.get(i));
					children.get(i).setParent(newMidNode1);
				}
				for(;i<=2*order+1;i++){
					newMidNode2.addChildren(children.get(i));
					children.get(i).setParent(newMidNode2);
				}
				for(LibraryNode child : newMidNode1.getAllChildren()){
					LibraryNodeLeaf castChild = (LibraryNodeLeaf)child;
					newMidNode1.addKey(castChild.bookAtIndex(0).key());
				}
				newMidNode1.getkeys().remove(0);
				for(LibraryNode child : newMidNode2.getAllChildren()){
					LibraryNodeLeaf castChild = (LibraryNodeLeaf)child;
					newMidNode2.addKey(castChild.bookAtIndex(0).key());
				}
				newMidNode2.getkeys().remove(0);
				primaryRoot=newUpNode;
			}
			else if(nodeParent.getkeys().size()>2*order){
				System.out.println("internal node overflow");
				ArrayList<LibraryNode> children = nodeParent.getAllChildren();
				for(LibraryNode child : children){
					LibraryNodeLeaf castChild = (LibraryNodeLeaf)child;
					System.out.println(castChild.bookAtIndex(0).key());
				}
				ArrayList<Integer> keys = nodeParent.getkeys();
				LibraryNodePrimaryIndex newNode = new LibraryNodePrimaryIndex(nodeParent.getParent());
				for(int i=order+1;i<=2*order+1;i++){
					newNode.addChildren(children.get(i));
					children.get(i).setParent(newNode);
				}
				for(int i=order+1 ;i<=2*order+1;i++){
					nodeParent.getAllChildren().remove(nodeParent.getAllChildren().size()-1);
					nodeParent.getkeys().remove(nodeParent.getkeys().size()-1);
				}
				for(LibraryNode child : newNode.getAllChildren()){
					LibraryNodeLeaf castChild = (LibraryNodeLeaf)child;
					newNode.addKey(castChild.bookAtIndex(0).key());
				}
				newNode.getkeys().remove(0);
				((LibraryNodePrimaryIndex)nodeParent.getParent()).addChildren(newNode);
				((LibraryNodePrimaryIndex)nodeParent.getParent()).addKey(((LibraryNodeLeaf)newNode.getChildrenAt(0)).bookAtIndex(0).key());

			}
			
		}
	}
	
	
	
	public CengBook searchBook(Integer key) {
		// add methods to find the book with the searched key in primary B+ tree
		// return value will not be tested, just print as the specicifications say
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
				if(node_keys.get(i)>key){
					path.add(currentNode);
					path_keys.add(node_keys.get(i));
					
					currentNode=allChildren.get(i);
					c=1;
					continue;
				}
			}
			if(c==1)
				continue;
			currentNode=allChildren.get(node_keys.size());
			
		}
		LibraryNodeLeaf castNode = (LibraryNodeLeaf)currentNode;
		path.add(castNode);
		
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
	
	
	public void printPrimaryLibrary() {
		// add methods to print the primary B+ tree in Depth-first order
		ArrayList<LibraryNode> path = new ArrayList<LibraryNode>();
		LibraryNode currentNode=primaryRoot;
		if(currentNode.type==LibraryNodeType.Leaf){
			
			LibraryNodeLeaf cast=(LibraryNodeLeaf)currentNode;
			printLeafNode(cast);
			return;
		}
		Queue<LibraryNode> visit=new LinkedList<LibraryNode>();
		visit.add(currentNode);
		while(visit.size()>0){
			currentNode=visit.poll();
			LibraryNodePrimaryIndex castNode=(LibraryNodePrimaryIndex)currentNode;
			System.out.println("<index>");
			for(int key_n:castNode.getkeys())
				System.out.println(key_n);
			System.out.println("</index>");
			if(castNode.getChildrenAt(0).type==LibraryNodeType.Internal){
				for(int i=castNode.getAllChildren().size();i>=0;i--){
					visit.add(castNode.getChildrenAt(i));
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
