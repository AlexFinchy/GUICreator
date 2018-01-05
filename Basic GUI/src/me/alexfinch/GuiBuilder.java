package me.alexfinch;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JPanel;

public class GuiBuilder extends JPanel implements MouseListener,ComponentListener {
	
	private JPanel panel;
	
	private HashMap<Rectangle2D,Image> ButtonList = new HashMap<Rectangle2D,Image>();
	
	private HashMap<Rectangle2D,Runnable> MouseListeners = new HashMap<Rectangle2D,Runnable>();
	
	private int Buttongap = 10;
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -1682359555590165162L;

	public GuiBuilder() {
		panel = this;
		panel.addMouseListener(this);
		panel.addComponentListener(this);
	}
	
	/**
	 * XLocation CENTER, LEFT, RIGHT
	 * @author alexf
	 *
	 */
	
	public enum XLocation {
		CENTER,
		LEFT,
		RIGHT,
	}
	
	
	/**
	 * YLocation CENTER, UP, DOWN
	 * 
	 * @author alexf
	 *
	 */
	public enum YLocation {
		CENTER,
		UP,
		DOWN,
	}
	
	public void SetButtonGap(int Gap) {
		Buttongap = Gap;
	}
	
	/**
	 * Adds GUI Button to interface, if button is added at same location as other button it will be put under other button
	 * 
	 * 
	 * @param buttonimage Image of button
	 * @param imagedim Dimensions of image button
	 * @param xlocation Location, Center, Left or Right
	 * 
	 */
	public void AddButton(Dimension JFrameSize, Image buttonimage, Dimension buttondim, XLocation xlocation, YLocation ylocation, Runnable ClickEvent) {
		
		if(Buttongap < 1) {
			System.out.println("Button Gap must be more than 1");
			return;
		}
		
		int gap = Buttongap+buttondim.height;
		
		
		
		int x = 0;
		
		if(xlocation == XLocation.CENTER) {
			x = (int) (JFrameSize.width/2)-buttondim.width/2;
		} else if(xlocation == XLocation.LEFT) {
			x = 0;
		} else if(xlocation == XLocation.RIGHT) {
			x = JFrameSize.width-buttondim.width;
		}
		
		int y = 0;
		
		if(ylocation == YLocation.CENTER) {
			y = (int) (JFrameSize.height/2)-buttondim.height/2;
		} else if(ylocation == YLocation.UP) {
			y = 0;
		} else if(ylocation == YLocation.DOWN) {
			y = JFrameSize.height-buttondim.height;
		}
		
		
		Set<Rectangle2D> keys = ButtonList.keySet();
		
		Iterator<Rectangle2D> i = keys.iterator();
		
		while(i.hasNext()) {
			
			Rectangle2D rect = i.next();
			
			int Max = gap;
			
			for(int k = y; k < Max; k=k+gap) {
				if(rect.getY() == y) {
					y = y + gap;
					System.out.println("Is equal");
					System.out.println(gap);
					Max = Max + gap;
				}
			}
			
			
			
			
			System.out.println(gap);
			
			System.out.println(rect.getY());
			
			System.out.println(y);
		}
		
		
		Rectangle2D button = new Rectangle2D.Double(x,y,buttondim.width,buttondim.height);
		ButtonList.put(button, buttonimage);
		MouseListeners.put(button, ClickEvent);
		
		
		
		
		
		
	}
	
	public void SetBackground(Image background, boolean Resize) {
		
	}
	
	public void UpdateGUI() {
		
		
		
		
	}
	
	
	
	public void paint(Graphics graphics) {
		super.paint(graphics);
		
		Graphics2D G2D = (Graphics2D) graphics;
		
		Set<Rectangle2D> keys = ButtonList.keySet();
		
		Iterator<Rectangle2D> i = keys.iterator();
		
		while(i.hasNext()) {
			Rectangle2D rect = i.next();
			Image image = ButtonList.get(rect);
			int x = (int) rect.getX();
			int y = (int) rect.getY();
			G2D.drawImage(image, x, y, null);
			
		}
		
		
	}
	
	public JPanel CreatePanel() {
		return panel;
	}



	@Override
	public void mouseClicked(MouseEvent e) {
		
		
		Set<Rectangle2D> keys = MouseListeners.keySet();
		
		Iterator<Rectangle2D> i = keys.iterator();
		
		while(i.hasNext()) {
			
			Rectangle2D rect = i.next();
			if(rect.contains(e.getPoint())) {
				Runnable Action = MouseListeners.get(rect);
				Action.run();
			}
		}
		
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent e) {
		UpdateGUI();
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	

	
	
}


