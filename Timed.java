import java.util.Scanner;

class Timed implements Runnable
{

	private Scanner scanner;
	private StringBuilder buffer;
	private boolean reading;
	public Thread t;
	Object ob = new Object();


	public Timed()
	{
		scanner = new Scanner(System.in);
		buffer = new StringBuilder();
		reading = false;
		t = new Thread(this);
		t.setDaemon(true);
		t.start();
	}

	public String nextLine(int time)
	{

		reading = true;
		String result = null;
		if(time>0) {
			long startTime = System.currentTimeMillis();
			int k=(int)time/1000;
			int checkTimeElapsed = 1;
			System.out.println(k--);
			while (System.currentTimeMillis() - startTime < time && result == null)
			{
				if(System.currentTimeMillis() - startTime>checkTimeElapsed*1000) {
					System.out.println(k--);
					checkTimeElapsed++;
				}
				try
				{
					Thread.sleep(30);
				}
				catch (InterruptedException e)
				{
				}
				synchronized (buffer)
				{
					if (buffer.length() > 0)
					{
						Scanner temp = new Scanner(buffer.toString());
						result = temp.nextLine();




					}
				}
			}
			if(System.currentTimeMillis() - startTime>=checkTimeElapsed*1000) {
				System.out.println(k--);
				checkTimeElapsed++;
			}
		}
		else {
			while(result == null) {
				try
				{
					Thread.sleep(30);
				}
				catch (InterruptedException e)
				{
				}
				synchronized (buffer)
				{
					if (buffer.length() > 0)
					{
						Scanner temp = new Scanner(buffer.toString());
						result = temp.nextLine();
						result = result.trim();
						if(result.equals("")) {
							result = null;
							buffer.delete(0, buffer.length());
						}




					}
				}
			}}

		reading = false;
		synchronized(buffer){
			buffer.delete(0, buffer.length());
		}

		return result;
	}

	@Override
	public void run()
	{


		while (scanner.hasNextLine())
		{
			String line = scanner.nextLine();
			synchronized (buffer)
			{
				if (reading)
				{

					buffer.append(line);
					buffer.append("\n");

				}
				else
				{
					// flush the buffer
					if (buffer.length() != 0)
					{
						buffer.delete(0, buffer.length());
					}
				}
			}
		}
	}
}