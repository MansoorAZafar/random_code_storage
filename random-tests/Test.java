import java.util.Scanner;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.concurrent.Semaphore;

class Baker {
	JFrame frame;
	JLabel label = new JLabel();
	
	public Baker() {
		new Thread(this::doWork).start();
	}
	
	
	
	private void doWork() {
		frame = new JFrame("Thread Panel");
		frame.setSize(200, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true); 
		frame.add(label);
		
		while(true) {
			this.MakeBread();
			this.PutCounter();
		}
	}
	
	private void MakeBread() {
		SwingUtilities.invokeLater(() ->
			label.setText("Making Bread...")
		);
		
		try {
			Thread.sleep(1000);			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void PutCounter() {
		SwingUtilities.invokeLater(() ->
			label.setText("Putting on Counter...")
		);
		
		try {			
			Counter.freeSpots.acquire();
			Counter.mutex.acquire();
				
			Counter.addItem();
			
			Counter.mutex.release();
			Counter.filledSpots.release(); 
			
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Counter {
	private static final int maxItems = 20;
	private static int numberOfItems = 0;
	
	static Semaphore freeSpots = new Semaphore(maxItems);
	static Semaphore filledSpots = new Semaphore(0);
	
	// Binary Semaphore
	static Semaphore mutex = new Semaphore(1);
	
	public static boolean isFull() {
		return numberOfItems == maxItems;
	}
	
	public static boolean 	isEmpty() {
		return numberOfItems == 0;
	}
	
	public static void addItem() {
		++numberOfItems;
	}
	
	public static void removeOne() {
		--numberOfItems;
	}
	
	public static String display() {
		StringBuilder res = new StringBuilder();
		for(int i = 0; i < maxItems; ++i) {
			res.append((i + 1) <= numberOfItems ? "[x]" : "[ ]");
		}
		
		return res.toString();
	}
}

class Test extends JFrame implements KeyListener {
	JLabel text = new JLabel(Counter.display(), SwingConstants.CENTER);
	Timer timer;
	
	public Test() {
		setTitle("Bakery");
		timer = new Timer(1000, e -> {
			text.setText(Counter.display());
		});
		
		
		addKeyListener(this);
		setSize(500, 300);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		setVisible(true);
		
		text.setFont(text.getFont().deriveFont(18.0f));
		add(text, BorderLayout.CENTER);
		
		final JLabel hintText = new JLabel("Press T to take one");
		hintText.setFont(hintText.getFont().deriveFont(24.0f));
		
		add(hintText, BorderLayout.SOUTH);
		timer.start();
	}
	
	@Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
	
	@Override
    public void keyPressed(KeyEvent e) {
		final int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_T) {
			new Thread(() -> {
				try {
					Counter.fileldSpots.acquire();
					Counter.mutex.acquire();

					Counter.removeOne();

					Counter.mutex.release();
					Counter.freeSpots.release();

				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}).start();
		}
    }
	
	public static void main(String[] args) {		
		Baker baker = new Baker();
		new Baker();
		
		Scanner scanner = new Scanner(System.in);
		
		new Test();
	}
}