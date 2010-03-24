// Copyright (c) 2003-2010, Jodd Team (jodd.org). All Rights Reserved.

package jodd.gfx.delay;

/**
 * Default delayer, using <code>Thread.sleep()</code>
 * and <code>System.currentTimeMillis()</code>. This is the most
 * unprecise delayer, especially on Windows platforms.
 */
public class DefaultDelayer extends Delayer {

	@Override
	public void start() {
		start = System.currentTimeMillis();
	}

	@Override
	public boolean end() {
		diff = (System.currentTimeMillis() - start);
		diff *= 1000000L;
		return endAndWait();
	}

	@Override
	public void sleep(long nanoSeconds) {
		int delay = (int) (nanoSeconds / 1000000L);
		if (delay == 0) {
			delay = 1;
		}
		try {
			Thread.sleep(delay);
		} catch (InterruptedException iex) {
			// ignore
		}
	}
}
