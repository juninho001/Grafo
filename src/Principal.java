import java.util.Random;

import javax.swing.JFrame;


public class Principal 
{

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		/*ArvoreBinaria tree = new ArvoreBinaria();
		
		Random randomGenerator = new Random();
		
		for (int idx = 1; idx <= 50; ++idx)
        {
			
          int randomInt = randomGenerator.nextInt(100);
          tree.insert(randomInt);
          System.out.println("Aleatorios : " + randomInt);
          
        }
		        
		        
		JFrame jFrame = new JFrame();
        jFrame.add(tree);
        jFrame.setSize(500, 500);
        jFrame.setVisible(true);
        
        */
		
		
		Grafo myGraph = new Grafo(10, true);
		//myGraph.insertEdge(new Aresta(0, 1));
		myGraph.insertEdge(new Aresta(5, 0));
		/*myGraph.insertEdge(new Aresta(0, 3));
		myGraph.insertEdge(new Aresta(0, 4));
		myGraph.insertEdge(new Aresta(1, 2));
		myGraph.insertEdge(new Aresta(0, 5));*/
		
		myGraph.graphDetails.display(myGraph);

		//myGraph.removeEdge(new Aresta(0, 1));

		myGraph.graphDetails.display(myGraph);
		
		JFrame jFrame = new JFrame();
        jFrame.add(myGraph);
        jFrame.setSize(500, 500);
        jFrame.setVisible(true);

	}

}
