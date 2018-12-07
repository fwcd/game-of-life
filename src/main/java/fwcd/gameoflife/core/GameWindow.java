package fwcd.gameoflife.core;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import fwcd.gameoflife.figures.Figure;
import fwcd.gameoflife.figures.PufferTrain;
import fwcd.gameoflife.utils.Position;

public class GameWindow {
	private JFrame view;
	
	private final Game game;
	
	private final BufferedImage image;
	private final Thread thread;
	private boolean running;
	private int[] pixels;
	
	public GameWindow(String title, int width, int height) {
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		pixels = new int[width * height];
		
		game = new Game(width, height);
		
		thread = new Thread(() -> {
			long time = System.currentTimeMillis();
			long lastTick = System.currentTimeMillis();
			long tickDelta;
			long delta;
			
			double tickDelay = 25D; // For maximum tps, set this to 1
			int maxTps = (int) (1000 / tickDelay);
			
			int ticks = 0;
			int frames = 0;
			
			while (running) {
				delta = System.currentTimeMillis() - time;
				tickDelta = System.currentTimeMillis() - lastTick;
				frames++;
				
				if (tickDelta >= tickDelay) {
					tick();
					
					lastTick = System.currentTimeMillis();
					ticks++;
				}
				
				render();
				
				if (delta >= 1000) {
					System.out.println(frames + " fps - " + ticks + " tps" + " (max. " + maxTps + " tps)");
					
					time = System.currentTimeMillis();
					frames = 0;
					ticks = 0;
				}
			}
		});
		
		view = new JFrame(title);
		view.setSize(width, height);
		view.setResizable(false);
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MouseAdapter mouseAdapter = new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					// Spawns a figure
					Figure figure = new PufferTrain();
					
					for (Position pos : figure.getPixels()) {
						game.cellAt(pos.getX() + e.getX(), pos.getY() + e.getY())
								.setAlive();
					}
				}
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// Spawns single cells
				game.cellAt(e.getX(), e.getY()).setAndKeepAlive();
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				game.setAllVulnerable();
			}
			
		};
		view.addMouseListener(mouseAdapter);
		view.addMouseMotionListener(mouseAdapter);
		view.setVisible(true);
	}
	
	public synchronized void start() {
		running = true;
		thread.start();
	}
	
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private synchronized void tick() {
		game.update(pixels);
		//Arrays.setAll(pixels, (x) -> (int) System.nanoTime());
	}
	
	private synchronized void render() {
		BufferStrategy strategy = view.getBufferStrategy();
		
		if (strategy == null) {
			view.createBufferStrategy(3);
			return;
		}
		
		game.silentlyUpdate(pixels);
		System.arraycopy(
				pixels, 0,
				((DataBufferInt) image.getRaster().getDataBuffer()).getData(), 0,
				pixels.length
		);
		
		Graphics g = strategy.getDrawGraphics();
		g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
		
		strategy.show();
	}
	
	public JFrame getView() {
		return view;
	}

	public Game getGame() {
		return game;
	}
}
