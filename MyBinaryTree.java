import java.util.Scanner;
public class MyBinaryTree<T> {
	// Data Members
	MyTreeNode root;
	
	//Initialization
	public MyBinaryTree() {
		root = null;
	}
	
	/* Insert new node into
	 * BST using the findNode 
	 * 	method */
	public boolean insert(T newObject) {
		KeyMode newInfo = (KeyMode) newObject;
		TreeNodeWrapper p = new TreeNodeWrapper();
		TreeNodeWrapper c = new TreeNodeWrapper();
		MyTreeNode n = new MyTreeNode();
		if (n == null) // out of memory
			return false;
		else {
			n.node = (T) newInfo.deepCopy();
			n.lc = null;
			n.rc = null;
			if (root == null)
				root = n;
			else {
				findNode(newInfo.getKey(), p, c);
				if (((KeyMode) (p.get().node)).compareTo(newInfo.getKey()) > 0)
					p.get().lc = n;
				else
					p.get().rc = n;
			}
			return true;
		}
		
	}  // end of insert method
	
	public T fetch(Object targetKey) {
		boolean found;
		TreeNodeWrapper p = new TreeNodeWrapper();
		TreeNodeWrapper c = new TreeNodeWrapper();
		found = findNode(targetKey, p, c);
		if (found == true)
			return (T) ((KeyMode) (c.get().node)).deepCopy();
		else
			return null;
	}		// end of fetch method
	
	public boolean delete(Object targetKey) {
		boolean found;
		TreeNodeWrapper p = new TreeNodeWrapper();
		TreeNodeWrapper c = new TreeNodeWrapper();
		MyTreeNode largest;
		MyTreeNode nextLargest;
		found = findNode(targetKey, p, c);
		if (found == false)
			return false;
		else {				// identify the case number
			if (c.get().lc == null && c.get().rc == null) {		//Case #1 (No children)
				if (p.get().lc == c.get())
					p.get().lc = null;
				else
					p.get().rc = null;
			}						// end of Case #1
			else if (c.get().lc == null || c.get().rc == null) {	//Case #2 (One child)
				if (p.get().lc == c.get()) { 	// deleted node is a left child
					if (c.get().lc != null)		//deleted node has a left child
						p.get().lc = c.get().lc;	
					else
						p.get().lc = c.get().rc;
				} else {									//deleted node is a right child
					if (c.get().lc != null)		//deleted node has a left child
						p.get().rc = c.get().lc;	
					else
						p.get().rc = c.get().rc;
				}
			} 						// end of Case #2
			else {					//Case #3 (Two children)
				nextLargest = c.get().lc;
				largest = nextLargest.rc;
				if (largest != null) {  //left child has a right subtree
					while (largest.rc != null) {			// move down right subtree
						nextLargest = largest;
						largest = largest.rc;
					}
					c.get().node = largest.node;		// overwrite the deleted node with next lower value node
					nextLargest.rc = largest.lc;		//save the left subtree (if any) on next lower value node
				} 
				else {							// left child has no right subtree
					nextLargest.rc = c.get().rc;     // save the right subtree of deleted node
					if (p.get().lc == c.get()) 		// deleted node is a left child
						p.get().lc = nextLargest;		// jump around deleted node
					else										// deleted node is a right child
						p.get().rc = nextLargest;   // jump around deleted node
				}
			}         		// end of Case #3
			return true;
		}
	} 			// end of delete method
	
	public boolean update(Object targetKey, T newObject) {
		boolean found;
		TreeNodeWrapper p = new TreeNodeWrapper();
		TreeNodeWrapper c = new TreeNodeWrapper();
		MyTreeNode largest;
		MyTreeNode nextLargest;
		KeyMode newInfo = (KeyMode) newObject;
		MyTreeNode n = new MyTreeNode();
		n.node = (T) newInfo.deepCopy();
		n.lc = null;
		n.rc = null;
		found = findNode(targetKey, p, c);
		if (found == false)
			return false;
		else {				// identify the case number
			if (c.get().lc == null && c.get().rc == null) {		//Case #1 (No children)
				if (p.get().lc == c.get())
					p.get().lc = null;
				else
					p.get().rc = null;
			}						// end of Case #1
			else if (c.get().lc == null || c.get().rc == null) {	//Case #2 (One child)
				if (p.get().lc == c.get()) { 	// deleted node is a left child
					if (c.get().lc != null)		//deleted node has a left child
						p.get().lc = c.get().lc;	
					else
						p.get().lc = c.get().rc;
				} else {									//deleted node is a right child
					if (c.get().lc != null)		//deleted node has a left child
						p.get().rc = c.get().lc;	
					else
						p.get().rc = c.get().rc;
				}
			} 						// end of Case #2
			else {					//Case #3 (Two children)
				nextLargest = c.get().lc;
				largest = nextLargest.rc;
				if (largest != null) {  //left child has a right subtree
					while (largest.rc != null) {			// move down right subtree
						nextLargest = largest;
						largest = largest.rc;
					}
					c.get().node = largest.node;		// overwrite the deleted node with next lower value node
					nextLargest.rc = largest.lc;		//save the left subtree (if any) on next lower value node
				} 
				else {							// left child has no right subtree
					nextLargest.rc = c.get().rc;     // save the right subtree of deleted node
					if (p.get().lc == c.get()) 		// deleted node is a left child
						p.get().lc = nextLargest;		// jump around deleted node
					else										// deleted node is a right child
						p.get().rc = nextLargest;   // jump around deleted node
				}
			}         		// end of Case #3
		}					// end of delete
		findNode(newInfo.getKey(), p, c);
		if (((KeyMode) (p.get().node)).compareTo(newInfo.getKey()) > 0)
			p.get().lc = n;
		else
			p.get().rc = n;
		return true;
	} 			
	
	
	public class MyTreeNode {
		private T node;
		private MyTreeNode lc;
		private MyTreeNode rc;
		
		public MyTreeNode() {
			
		}
		
	}
	
	/* Find node in BST
	*  using iterative algorithm */
	public boolean findNode(Object targetKey,
			TreeNodeWrapper parent, TreeNodeWrapper child) {
		parent.set(root);
		child.set(root);
		if (root == null)
			return true;
		while (child.get() != null) {
			if (((KeyMode) (child.get().node)).compareTo(targetKey) == 0)   // Node found
				return true;
			else {										   // Continue searching
				parent.set(child.get());
				if (((KeyMode) (child.get().node)).compareTo(targetKey) > 0)	// Move child reference to left subtree
					child.set(child.get().lc);
				else											// Move child reference to right subtree
					child.set(child.get().rc);
			}// End else
		}// End while
		return false;
	}// End method
	
	public class TreeNodeWrapper {
		MyTreeNode treeRef = null;
		
		public TreeNodeWrapper() {
			
		}
		
		public MyTreeNode get() {
			return treeRef;
		}
		
		public void set(MyTreeNode t) {
			treeRef = t;
		}
	}
	
	public void LNRoutputTraversal(MyTreeNode root) {
		if (root.lc != null)
			LNRoutputTraversal(root.lc);
		System.out.println(root.node);
		if (root.rc != null)
			LNRoutputTraversal(root.rc);
	}
	
	public void RNLoutputTraversal(MyTreeNode root) {
		if (root.rc != null)
			RNLoutputTraversal(root.rc);
		System.out.println(root.node);
		if (root.lc != null)
			RNLoutputTraversal(root.lc);
	}
	
	public void showAll() {
		if (root == null)
			System.out.println("The structure is empty.");
		else
			System.out.println("Press 1 to output structure in ascending order");
			System.out.println("Press 2 to output structure in descending order");
			Scanner input = new Scanner(System.in);
			int choice = input.nextInt();
			switch (choice) {
			case 1:
				LNRoutputTraversal(root);
				break;
			case 2:
				RNLoutputTraversal(root);
				break;
			default:
				System.out.println("Incorrect value.");
			}	
				
	}
}

