package GameInit;

import java.awt.Toolkit;

import Teris.Teris;

public class GameInit {
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	public GameInit() {
		Teris.blockImages[0]=tk.getImage(Teris.class.getClassLoader().getResource("Image/blue.png"));
		Teris.blockImages[1]=tk.getImage(Teris.class.getClassLoader().getResource("Image/gray.png"));
		Teris.blockImages[2]=tk.getImage(Teris.class.getClassLoader().getResource("Image/green.png"));
		Teris.blockImages[3]=tk.getImage(Teris.class.getClassLoader().getResource("Image/purple.png"));
		Teris.blockImages[4]=tk.getImage(Teris.class.getClassLoader().getResource("Image/red.png"));
		Teris.blockImages[5]=tk.getImage(Teris.class.getClassLoader().getResource("Image/yellow.png"));
	}

}
