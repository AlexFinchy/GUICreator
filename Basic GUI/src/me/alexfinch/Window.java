package me.alexfinch;

import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import me.alexfinch.GuiBuilder.XLocation;
import me.alexfinch.GuiBuilder.YLocation;

public class Window extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5679042082203767504L;

	
	public static void main(String[] args) {
		
		Window window = new Window();
		
		window.setSize(1000,1000);
		
		GuiBuilder guibuilder = new GuiBuilder();
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		
		InputStream Play = classLoader.getResourceAsStream("Play.png");
		
		InputStream Options = classLoader.getResourceAsStream("Options.png");
		
		InputStream Exit = classLoader.getResourceAsStream("Exit.png");
		
		try {
			Image Playimage = ImageIO.read(Play);
			
			Image Optionsimage = ImageIO.read(Options);
			
			Image Exitimage = ImageIO.read(Exit);
			
			Dimension buttondim = new Dimension(300, 50);
			
			Runnable Event = new Runnable() {
				
				@Override
				public void run() {
					System.out.println("Button was Clicked");
					
				}
			};
			
			guibuilder.SetButtonGap(10);
			
			guibuilder.AddButton(window.getSize(), Playimage, buttondim, XLocation.CENTER, YLocation.CENTER, Event);
			
			guibuilder.AddButton(window.getSize(), Optionsimage, buttondim, XLocation.CENTER, YLocation.CENTER, Event);
			
			guibuilder.AddButton(window.getSize(), Exitimage, buttondim, XLocation.CENTER, YLocation.CENTER, Event);
			
			JPanel panel = guibuilder.CreatePanel();
			
			
			
			window.add(panel);
			
			
			window.setVisible(true);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}
	
}
