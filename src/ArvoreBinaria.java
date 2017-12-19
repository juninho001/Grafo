import java.awt.Graphics;

import javax.swing.JPanel;


public class ArvoreBinaria extends JPanel

{
	//Atributo
	Node root;
	int X0=200;
    int Y0=30;
    int X_OFFSET_CENT = 8;
    int Y_OFFSET_CENT = 13;
    int RAIO_NO = 20;
	
	// Constructor
    ArvoreBinaria() 
    { 
        root = null; 
    }
    
 // This method mainly calls insertRec()
    void insert(int key) 
    {
       root = insertRec(root, key);
    }
     
    /* A recursive function to insert a new key in BST */
    Node insertRec(Node root, int key) 
    {
 
        /* If the tree is empty, return a new node */
        if (root == null) 
        {
            root = new Node(key);
            return root;
        }
 
        /* Otherwise, recur down the tree */
        if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key > root.key)
            root.right = insertRec(root.right, key);
 
        /* return the (unchanged) node pointer */
        return root;
    }
	
	
	
	@Override
    public void paintComponent(Graphics g) 
	{
		int xs=10;   //where to start printing on the panel
	    int ys=20;
	    g.drawString("Minha Arvore Binária:\n",xs,ys);
	    
	    drawTree(root,X0, Y0, g);
	   
	    
        revalidate();
    }
	
	void drawTree(Node root, int xNode, int yNode, Graphics g )
	{
		if (root != null)
		{
			g.drawOval(xNode, yNode, RAIO_NO, RAIO_NO);
	        g.drawString(Integer.toString(root.key),(xNode+X_OFFSET_CENT),(yNode+Y_OFFSET_CENT));
			
	        drawTree(root.left,xNode-RAIO_NO, yNode + RAIO_NO, g );
	        drawTree(root.right,xNode+RAIO_NO, yNode + RAIO_NO, g );
			
		}
	}

}
