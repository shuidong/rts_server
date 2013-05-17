package server_battle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class ToDoQueue {
	long startTime = System.currentTimeMillis();
	private List<Activity> toDoQueue = new ArrayList<Activity>();
	private static final AtomicLong UniqueProcessId = new AtomicLong();
	
	
	public long add(Activity activity){
		int index;
		index = Collections.binarySearch(this.toDoQueue, activity, byRequestedExecutionTime);
		if(index < 0)
			index = ~index;
		else
			return toDoQueue.get(index).getProcess_ID();
		long process_ID = UniqueProcessId.getAndIncrement();
		toDoQueue.add(index, activity);
		return process_ID;
	}
	public Activity remove(long Activity_ID){
		int index = 0;
		for(Activity activity : toDoQueue){
			if(activity.getProcess_ID() == Activity_ID)
				return toDoQueue.remove(index);
			else
				index++;
		}
		return null;
	}
	
	final Comparator<Activity> byRequestedExecutionTime = new Comparator<Activity>() {
		public int compare(Activity a1, Activity a2) {
			long temp = a1.getRequestedExecutionTime() - a2.getRequestedExecutionTime();
			if(temp > 0)
				return 1;
			else if (temp == 0)
				return 0;
			else
				return -1;
		}
	};
}
