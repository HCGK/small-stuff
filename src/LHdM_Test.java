import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import java.util.*;

public class LHdM_Test extends JPanel {
	
	public static final int I = 12;
	public static final int J = 12;
	private static LeftHandM L;
	
	private boolean[][] map;
	private int i;
	private int j;
	private int l;
	private int m;
	
	public static void main (String[] args){
		LHdM_Test panel = new LHdM_Test(I,J);
		L = new LeftHandM(I,J);
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(32*I + 22, 32*J +64);
		frame.add(panel);
		frame.setVisible(true);
		panel.repaint();
	}
	
	LHdM_Test(int i, int j){
		super();
		this.i = i;
		this.j = j;
		l = 2*i + 1;
		m = 2*j + 1;
		map = new boolean[l][m];
		for(int a=0;a<l;a++){
			for(int b=0;b<m;b++){
				map[a][b] = false;
			}
		}
		this.setSize(16*l + 8, 16*m + 16);	
		Handler handler = new Handler();
		this.addMouseListener(handler );
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(int a=0;a<l;a++){
			for(int b=0;b<m;b++){
				if(map[a][b])
					g.setColor(Color.green);
				else
					g.setColor(Color.blue);
				g.fillRect(16*a + 3, 16*b + 1, 10, 10);
			}
		}
		g.setColor(Color.BLACK);
		g.fillRect(16*i + 3, 16*j + 1, 10, 10);
		map[i][j] = false;
	}
	
	public int readMap(int x, int y){
		if(map[x+i][y+j])
			return 1;
		else
			return 0;
	}
	
	private class Handler implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getY() > 16*m){
				System.out.println ("Begin");
				System.out.println ("Test " + L.tester(LHdM_Test.this));
			}
			else{
				map[e.getX()/16][e.getY()/16] = !map[e.getX()/16][e.getY()/16];
				repaint();
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}

	}
}
