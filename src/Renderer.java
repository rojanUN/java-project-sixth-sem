import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Renderer extends JPanel
{
    private Simon simon;

    public Renderer(Simon simon) {
        this.simon = simon;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        if (simon != null)
        {
            simon.paint((Graphics2D) g);
        }
    }

}



