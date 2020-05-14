package test;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

public class CameraTest
{
  public static void main(String[] args) throws InterruptedException
  {
    try
    {
      Webcam webcam = null;
      List<Webcam> list = Webcam.getWebcams();

      for(Webcam c : list)
      {
        System.out.println(c.toString());
        webcam = c;
      }

      webcam.setViewSize(WebcamResolution.VGA.getSize());
      webcam.open();
      WebcamPanel panel = new WebcamPanel(webcam);
      panel.setFPSDisplayed(true);
      panel.setDisplayDebugInfo(true);
      panel.setImageSizeDisplayed(true);
      panel.setMirrored(true);
      JFrame window = new JFrame("Test webcam panel");
      window.add(panel);
      window.setResizable(true);
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      window.pack();
      window.setVisible(true);

      // 5秒ごとに写真を三枚撮影。

      for(int i=0;i<3;i++)
      {
        Thread.sleep(5000);
        BufferedImage image = webcam.getImage();
        ImageIO.write(image, "PNG", new File("test" + i + ".png"));
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}