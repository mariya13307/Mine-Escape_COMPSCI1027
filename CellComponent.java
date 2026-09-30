import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class CellComponent extends JComponent  {

	public static enum CellType {
		FLOOR, WALL, HOR_CORR, VER_CORR, START, EXIT, LAVA
	};
	
	private CellType type;
	private String IMG_FLOOR = "floor.jpg";
	private String IMG_WALL = "wall.jpg";
	private String IMG_HCORR = "hor-corr.jpg";
	private String IMG_VCORR = "ver-corr.jpg";
	private String IMG_START = "start.jpg";
	private String IMG_EXIT = "exit.jpg";
	private String IMG_LAVA = "lava.jpg";
	


	
	public void setType(CellType type) {
		this.type = type;
		repaint();
	}
	
	/**
	 * Draws the different types of map cells on the screen
	 * 
	 * @param g
	 *            Graphics object used to draw the cells on the screen
	 */
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		int width = getWidth();
		int height = getHeight();
		
		String imgFile = "";
		if (type == CellType.FLOOR) {
			imgFile = IMG_FLOOR;
		} else if (type == CellType.WALL) {
			imgFile = IMG_WALL;
		} else if (type == CellType.HOR_CORR) {
			imgFile = IMG_HCORR;
		} else if (type == CellType.VER_CORR) {
			imgFile = IMG_VCORR;
		} else if (type == CellType.START) {
			imgFile = IMG_START;
		} else if (type == CellType.EXIT) {
			imgFile = IMG_EXIT;
		} else if (type == CellType.LAVA) {
			imgFile = IMG_LAVA;
		}


		try {
			Image img = new ImageIcon(imgFile).getImage();
			g2d.drawImage(img, 0, 0, width, height, null);
		} catch (Exception e) {
			System.out.println("Error opening file " + imgFile);
		}
		
		if (Map.SHOW_IDS) {
			g.setFont(new Font("TimesRoman", Font.BOLD, 16)); 
			g2d.setColor(Color.white);
			g2d.drawString(toString(), 12, 25);
		}

	}
	
}
