import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;



public class Grafo extends JPanel implements MouseListener
{
	private int VertexCount, EdgeCount;
	private boolean digraph;
	private boolean adjacencies[][];
	public GraphDetails graphDetails;
	
	private int x_coord_V[];
	private int y_coord_V[];
	private Color colorNode[];
	
	int RAIO_NO = 50;
	int X_0 = 70;
	int Y_0 = 70;
	int X_OFF = 22;
	int Y_OFF = 27;
	int MAX_COL = 4;
		
	private boolean done;
	private int primeiro;
	private int segundo;
	
	JButton button;
	JButton button2;
	
	private boolean isCircular; 
	
	Grafo(int numVertices, boolean flag)
	{
		VertexCount = numVertices;
		EdgeCount = 0;
		digraph = flag;
		adjacencies = new boolean[VertexCount][VertexCount];
		x_coord_V = new int[VertexCount];
		y_coord_V = new int[VertexCount];
		colorNode = new Color[VertexCount];

		graphDetails = new GraphDetails();
		super.addMouseListener(this);
		
		
		primeiro = -1;
		segundo = -1;
		isCircular = false;
		done = false;
		
		button = new JButton("Circular");
        button.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
            	        		
            	isCircular = true; //Circular
            	done = false;
            	repaint();
            	
            }
        });
        
        
        button2 = new JButton("Retangular");
        button2.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
            	isCircular = false;  //Retangular
            	done = false;
            	repaint();
            }
        });
        
		
		
	}

	int numVertices() 
	{ 
		return VertexCount; 
	}
	
	int numEdges() 
	{ 
		return EdgeCount; 
	}
	
	boolean directed() 
	{ 
		return digraph; 
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		for (int i = 0; i < this.numVertices(); i++)
		{
			//colorNode[i] = Color.GREEN;
			
			int d_x = 0;
			int d_y = 0;
			
			d_x = Math.abs(x_coord_V[i] - e.getX()); 
			d_y = Math.abs(y_coord_V[i] - e.getY());
			
			if (d_x<=(int)(RAIO_NO) && d_y<=(int)(RAIO_NO) )
			{
				if((primeiro==-1) && (segundo==-1))
				{
					colorNode[i] = Color.GREEN;
					primeiro = i;
					System.out.println("Primeiro Nó Selecionado");
					
				}
				else if(segundo==-1)
				{
					
					colorNode[i] = Color.RED;
					segundo = i;
					System.out.println("Segundo Nó Selecionado");
					
				}
			}
			
		}
				
        repaint();
    }

	@Override
	public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
    
    @Override
    public void mouseEntered(MouseEvent e) {

    } 
	
    
    public void desenhaAresta()
    {}
    
    public void clearPanel(Graphics g)
    {
    	int xs=10;   //where to start printing on the panel
	    int ys=20;
	    
	    g.drawString("Meu Grafo:\n",xs,ys);
    	
    	this.removeAll();
    	this.updateUI();
    	this.add(button);
        this.add(button2);
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, 500, 500);
        validate();
    }
	
	
    public void paintComponent(Graphics g) 
	{
		
    	clearPanel(g);
	    
	    if (!done)
	    {
	    
		    if(isCircular)
	    		drawGrafoCircular(g);
	    	else
	    		drawGrafoRetangular(g);
		    
		    conectEdges(g);
	    }
		else
	    {
	    	for (int i = 0; i < this.numVertices(); i++)
		    {
		    	g.setColor(colorNode[i]);
		    	g.fillOval(x_coord_V[i] ,y_coord_V[i], RAIO_NO, RAIO_NO);
		    	
		    	g.setColor(Color.BLUE);
		    	g.drawString(Integer.toString(i),x_coord_V[i] +X_OFF,y_coord_V[i] + Y_OFF);
		    }
	    	
	    	conectEdges(g);
	    }
	    
	    
	    
        
        
    }
	
	public void drawGrafoCircular(Graphics g )
	{
		
		X_0 = 250;
		Y_0 = 250;
		int RADIUS = 150;
		double rad, angulo, anguloStep;
		
		anguloStep = 360/this.numVertices();
		angulo = 0.0;
		
		
		for (int i = 0; i < this.numVertices(); i++)
		{
			rad = (Math.PI/180) * angulo;
			
			x_coord_V[i] = X_0 + (int)(RADIUS * Math.cos(rad));
			y_coord_V[i] = Y_0 + (int)(RADIUS * Math.sin(rad));
			colorNode[i] = Color.WHITE;
			angulo = angulo + anguloStep;
		}
		
		conectEdges(g);
	
	}
	
	void drawGrafoRetangular(Graphics g )
	{
		
		X_0 = 70;
		Y_0 = 70;
		
		int colCount = 0;
		int line = 0;
		int XCoord = X_0;
		int YCoord = Y_0;
		
		
		for (int i = 0; i < this.numVertices(); i++)
		{
			
			if (colCount < MAX_COL)
			{
				x_coord_V[i] = XCoord;
				y_coord_V[i] = YCoord;
				colorNode[i] = Color.WHITE;
				
				XCoord = XCoord + 2*RAIO_NO;
				colCount++;
			}
			else
			{
				colCount = 0;
				YCoord = YCoord+2*RAIO_NO;
				XCoord = X_0;
				
				x_coord_V[i] = XCoord;
				y_coord_V[i] = YCoord;
				colorNode[i] = Color.WHITE;
				
				
				XCoord = XCoord + 2*RAIO_NO;
				colCount++;
			}
				
		}
		
		
		
		
		
	}
	
	
	public void conectEdges(Graphics g)
	{
		int x0_inter =0;
		int y0_inter = 0;
		int x1_inter =0;
		int y1_inter = 0;
		
		double scale = 0.0f;
		
		int x0_aux_coord = 0;
		int y0_aux_coord = 0;
		
		int x1_aux_coord = 0;
		int y1_aux_coord = 0;
		
		int d_x = 0;
		int d_y = 0;
		
	    
	    for (int i = 0; i < this.numVertices(); i++)
		{
			AdjArray A = this.getAdjList(i);

			for (int j = A.beginning(); !A.end(); j = A.next())
			{
				
				x0_aux_coord = x_coord_V[i] +X_OFF;
				y0_aux_coord = y_coord_V[i] +Y_OFF;
				
				x1_aux_coord = x_coord_V[j] +X_OFF;
				y1_aux_coord = y_coord_V[j] +Y_OFF;
				
				d_x = Math.abs(x1_aux_coord - x0_aux_coord);
				d_y = Math.abs(y1_aux_coord - y0_aux_coord);
				
				scale = RAIO_NO/2/((Math.sqrt(d_x*d_x+ d_y*d_y)));
				
				if (x0_aux_coord > x1_aux_coord)
				{
					x0_inter = x0_aux_coord - (int) (scale*d_x);
					x1_inter = x1_aux_coord + (int) (scale*d_x);
				}
				else
				{
					x0_inter = x0_aux_coord + (int) (scale*d_x);
					x1_inter = x1_aux_coord - (int) (scale*d_x);
				}
				
				
				if (y0_aux_coord > y1_aux_coord)
				{
					y0_inter = y0_aux_coord - (int) (scale*d_y);
					y1_inter = y1_aux_coord + (int) (scale*d_y);
				}
				else
				{
					y0_inter = y0_aux_coord + (int) (scale*d_y);
					y1_inter = y1_aux_coord - (int) (scale*d_y);
				}
				
				g.setColor(Color.BLUE);
				g.drawLine(x0_inter,y0_inter, x1_inter, y1_inter);
				
			}
			
			
			
		}
	    
	    for (int i = 0; i < this.numVertices(); i++)
	    {
	    	g.setColor(colorNode[i]);
	    	g.fillOval(x_coord_V[i] ,y_coord_V[i], RAIO_NO, RAIO_NO);
	    	
	    	g.setColor(Color.BLUE);
	    	g.drawString(Integer.toString(i),x_coord_V[i] +X_OFF,y_coord_V[i] + Y_OFF);
	    }
	    
	    done = true;
	    
	    
	}
	
	
	
	void insertEdge(Aresta e)
	{
		int v = e.v;
		int w = e.w;

		if (adjacencies[v][w] == false)
		{
			EdgeCount++;
			adjacencies[v][w] = true;
		}

		if (!digraph)
		{
			adjacencies[w][v] = true;
		}
	}

	void removeEdge(Aresta e)
	{
		int v = e.v;
		int w = e.w;

		if (adjacencies[v][w] == true)
		{
			EdgeCount--;
			adjacencies[v][w] = false;
		}

		if (!digraph)
		{
			adjacencies[w][v] = false;
		}
	}

	boolean edgeTest(int v, int w)
	{
		return adjacencies[v][w];
	}

	AdjArray getAdjList(int v)
	{
		return new AdjArray(v);
	}
	
	
	private class AdjArray
	{
		private int i, v;

		AdjArray(int v)
		{
			this.v = v;
			i = -1;
		}

		public int beginning()
		{
			i = -1;
			return next();
		}

		public int next()
		{
			for (i++; i < numVertices(); i++)
			{
				if (edgeTest(v, i) == true)
				return i;
			}

			return -1;
		}

		public boolean end()
		{
			return i >= numVertices();
		}
	}
	
	
	public class GraphDetails
	{
		public void display(Grafo g)
		{
			System.out.println("Graph details");

			for (int i = 0; i < g.numVertices(); i++)
			{
				System.out.print(i + ": ");
				AdjArray A = g.getAdjList(i);

				for (int j = A.beginning(); !A.end(); j = A.next())
					System.out.print(j + " ");
				
				
				System.out.println("");
			}
		}
	}	

}