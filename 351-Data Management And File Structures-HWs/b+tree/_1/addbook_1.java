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
			((LibraryNodePrimaryIndex)castNode.getParent()).addChildren(nodeNew1);//addchildren updated
			((LibraryNodePrimaryIndex)castNode.getParent()).addKey(nodeNew1.bookAtIndex(0).key());//addchildren updated
			
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
				newUpNode.addKey(keys.get(order));//addchildren updated
				newUpNode.addChildren(newMidNode1);//addchildren updated
				newUpNode.addChildren(newMidNode2);//addchildren updated
				int i=0;
				for(;i<order+1;i++){//addchildren updated
					newMidNode1.addChildren(children.get(i));
					children.get(i).setParent(newMidNode1);
				}
				for(;i<=2*order+1;i++){//addchildren updated
					newMidNode2.addChildren(children.get(i));
					children.get(i).setParent(newMidNode2);
				}
				for(LibraryNode child : newMidNode1.getAllChildren()){//addchildren updated
					LibraryNodeLeaf castChild = (LibraryNodeLeaf)child;
					newMidNode1.addKey(castChild.bookAtIndex(0).key());
				}
				newMidNode1.getkeys().remove(0);
				for(LibraryNode child : newMidNode2.getAllChildren()){//addchildren updated
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
				for(int i=order+1 ;i<=2*order+1;i++){//addchildren updated
					nodeParent.getAllChildren().remove(nodeParent.getAllChildren().size()-1);
					nodeParent.getkeys().remove(nodeParent.getkeys().size()-1);
				}
				for(LibraryNode child : newNode.getAllChildren()){//addchildren updated
					LibraryNodeLeaf castChild = (LibraryNodeLeaf)child;
					newNode.addKey(castChild.bookAtIndex(0).key());
				}
				newNode.getkeys().remove(0);
				((LibraryNodePrimaryIndex)nodeParent.getParent()).addChildren(newNode);
				((LibraryNodePrimaryIndex)nodeParent.getParent()).addKey(((LibraryNodeLeaf)newNode.getChildrenAt(0)).bookAtIndex(0).key());

			}
			
		}
	}