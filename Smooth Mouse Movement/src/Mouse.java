
import java.awt.AWTException;
import java.math.*;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;

class processor extends Thread {
	
	private boolean running = true;
	
	public void run() {
		
		while(running) {
			System.out.println("Hello");
			
			try {
				Thread.sleep(1000);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		}
		
	}

    public void shutdown() {
    	running = false;
    }
}

public class Mouse extends Mouse_extension {
	
	static int t_steps = 10000;
	static ArrayList<Integer> coordinates_final_to_initial_x = new ArrayList<Integer>();
	static ArrayList<Integer> coordinates_final_to_initial_y = new ArrayList<Integer>();
	static Queue<Integer> q_coordinates_final_to_initial_x = new LinkedList<Integer>();
	static Queue<Integer> q_coordinates_final_to_initial_y = new LinkedList<Integer>();
	
	public static int newRandom() {
		int number;
		Random random = new Random();
		number = random.nextInt(600) + 100;
		return number;
	}
	
	public static int RandomTime() {
		int number;
		Random random = new Random();
		number = random.nextInt(6000) + 4000;
		return number;
	}
	
	public void mouseGlide(int x1, int y1, int x2, int y2, int t, int n) {
	    try {
	        Robot r = new Robot();
	        double dx = (x2 - x1) / ((double) n);
	        double dy = (y2 - y1) / ((double) n);
	        double dt = t / ((double) n);
	        for (int step = 1; step <= n; step++) {
	            Thread.sleep((int) dt);
	            r.mouseMove((int) (x1 + dx * step), (int) (y1 + dy * step));
	        }
	    } catch (AWTException e) {
	        e.printStackTrace();
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}
	
	public void mouseGlide_curve_yx2(int x1, int y1, int x2, int y2, int t, int n) { // we need to use graph of hyperbola
		// y should equal x^2 (x, x^2, initials supposed to be counted as (0,0)
		try {
			Robot r = new Robot();
			double dx = (x2 - x1) / ((double) n); // we find the rate of change, everything aight yet
	        double dy = (y2 - y1) / ((double) n); // same here
	        double dt = t / ((double) n); // and here
	        for (int step = 1; step <= n; step++) {
	        	Thread.sleep((int) dt);
	            r.mouseMove((int) (x1 + dx * step), (int) (y1 + Math.pow(dy,2) * step));
	            // Math.pow(dy,2)
	        }
	    } catch (AWTException e) {
	        e.printStackTrace();
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
		}
	
	public void mouseGlide_curve_xy2(int x1, int y1, int x2, int y2, int t, int n) { // we need to use graph of hyperbola
		// y should equal x^2 (x, x^2, initials supposed to be counted as (0,0)
		try {
			Robot r = new Robot();
			double dx = (x2 - x1) / ((double) n); // we find the rate of change, everything aight yet
	        double dy = (y2 - y1) / ((double) n); // same here
	        double dt = t / ((double) n); // and here
	        for (int step = 1; step <= n; step++) {
	        	Thread.sleep((int) dt);
	            r.mouseMove((int) (x1 + Math.pow(dx,2) * step), (int) (y1 + dy * step));
	            // Math.pow(dy,2)
	        }
	    } catch (AWTException e) {
	        e.printStackTrace();
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
		}
	
	public static void main (String[] a) throws InterruptedException {
		int initial_x = newRandom();
		int initial_y = newRandom();
        // coordinates_final_to_initial_x.add(initial_x);
		coordinates_final_to_initial_y.add(initial_y);
		q_coordinates_final_to_initial_x.add(initial_x);
		// q_coordinates_final_to_initial_y.add(initial_y);
		// -----
		for (int i=0; i <= 5; i++) {
		TimeUnit.SECONDS.sleep(1/2);
		int final_x = newRandom();
		int final_y = newRandom();
		// -----
		// int xx = coordinates_final_to_initial_x.get(coordinates_final_to_initial_x.size()-1);
		int xy = coordinates_final_to_initial_y.get(coordinates_final_to_initial_y.size()-1);
		int xx = q_coordinates_final_to_initial_x.peek();
		// int xy = q_coordinates_final_to_initial_y.peek();
		// -----
		Mouse x = new Mouse();
		x.mouseGlide(xx, xy, final_x, final_y, RandomTime(), t_steps); // arraylist as a tool to use coordinates from one to another
		// x.mouseGlide_curve_yx2(50, 50, 450, 1650, 11000, 10000);
		// doesn't work with the standart layout of the mouseGlide, have to create new
		coordinates_final_to_initial_x.add(final_x);
		coordinates_final_to_initial_y.add(final_y);
		}
	}
}
