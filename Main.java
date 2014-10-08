import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.metal.MetalToggleButtonUI;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.Collections;

public class Main extends JApplet implements Runnable
{
	public final int SOUNDS = 16;
	JToggleButton button[][]=new JToggleButton[SOUNDS][SOUNDS];
	JPanel panel=new JPanel();
	JProgressBar progressBar = new JProgressBar();
	Container container;
	AudioClip soundClip[]=new AudioClip[SOUNDS];
	String notes[]=new String[SOUNDS];
	Thread runner;
	boolean notStopped=true, buttonOn=false;

	public void init()
	{
		soundClip[15]=getAudioClip(getDocumentBase(),"Sound/audiocheck_A3.wav");notes[15]="A3";
		soundClip[14]=getAudioClip(getDocumentBase(),"Sound/audiocheck_A4.wav");notes[15]="A4";
		soundClip[13]=getAudioClip(getDocumentBase(),"Sound/audiocheck_As4.wav");notes[15]="A#";
		soundClip[12]=getAudioClip(getDocumentBase(),"Sound/audiocheck_B3.wav");notes[15]="B3";
		soundClip[11]=getAudioClip(getDocumentBase(),"Sound/audiocheck_B4.wav");notes[15]="B4";
		soundClip[10]=getAudioClip(getDocumentBase(),"Sound/audiocheck_C3.wav");notes[15]="C3";
		soundClip[9]=getAudioClip(getDocumentBase(),"Sound/audiocheck_C4.wav");notes[15]="C4";
		soundClip[8]=getAudioClip(getDocumentBase(),"Sound/audiocheck_Cs4.wav");notes[15]="C#";
		soundClip[7]=getAudioClip(getDocumentBase(),"Sound/audiocheck_C5.wav");notes[15]="C5";
		soundClip[6]=getAudioClip(getDocumentBase(),"Sound/audiocheck_D3.wav");notes[15]="D3";
		soundClip[5]=getAudioClip(getDocumentBase(),"Sound/audiocheck_D4.wav");notes[15]="D4";
		soundClip[4]=getAudioClip(getDocumentBase(),"Sound/audiocheck_Ds4.wav");notes[15]="D#";
		soundClip[3]=getAudioClip(getDocumentBase(),"Sound/audiocheck_E3.wav");notes[15]="E3";
		soundClip[2]=getAudioClip(getDocumentBase(),"Sound/audiocheck_E4.wav");notes[15]="E4";
		soundClip[1]=getAudioClip(getDocumentBase(),"Sound/audiocheck_F3.wav");notes[15]="F3";
		soundClip[0]=getAudioClip(getDocumentBase(),"Sound/audiocheck_F4.wav");notes[15]="F4";
		container=getContentPane();
		panel.setLayout(new GridLayout(SOUNDS,1));//button.length,1));
		panel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		for(int y=0;y<SOUNDS;y++)
		{
			for(int x=0;x<SOUNDS;x++)
			{
				
				button[x][y]=new JToggleButton();	
				button[x][y].setUI(new MetalToggleButtonUI() {
				    @Override
				    protected Color getSelectColor() {
				    	
				    	float[] hsbvals = new float[3];
						Color.RGBtoHSB(192, 57, 43, hsbvals);
				        return Color.getHSBColor(hsbvals[0], hsbvals[1], hsbvals[2]);
				    }
				});
				float[] hsbvals = new float[3];
				Color.RGBtoHSB(44, 62, 80, hsbvals);
				button[x][y].setBackground(Color.getHSBColor(hsbvals[0], hsbvals[1], hsbvals[2]));
				Color.RGBtoHSB(52, 152, 219, hsbvals);
				Border border = BorderFactory.createLineBorder(Color.getHSBColor(hsbvals[0], hsbvals[1], hsbvals[2]));
				button[x][y].setBorder(border);
				panel.add(button[x][y]);
			}
		}
		setSize(650,650);
		container.add(panel,BorderLayout.CENTER);
		container.add(progressBar,BorderLayout.NORTH);
		setVisible(true);
	}

	public void start()
	{
		if(runner==null)
		{
			runner=new Thread(this);
			runner.start();
		}
	}

	public void run()
	{
		do
		{
			goApp();
		}while(notStopped);

	}



	public void goApp()
	{
		try
		{
			for(int x=0;x<SOUNDS;x++)
			{
				for(int y=0;y<SOUNDS;y++)
				{
					if(button[x][y].isSelected())
					{
						soundClip[y].play();
						System.out.println("I:\t"+y+"\tJ:\t"+x+"\tLength:\t"+button.length);
					}
				}
				progressBar.setValue(x*7);
				new Thread().sleep(150);
			}
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}