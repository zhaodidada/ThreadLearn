package Day2.demo2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TransferQueue;

/**
 * 集合类的演变
 */
public class Demo2_0
{
	@SuppressWarnings({ "rawtypes", "unused" })
	public static void main(String[] args)
	{
		// ----------------------------- 开 始 -----------------------------
		// Since JDK 1.0
		Vector v;
			Stack s;
		Hashtable h;
		
		// -----------------------------分隔线-----------------------------
		// Since JDK 1.2
		Collection c; 
			List list;
				ArrayList al;
				LinkedList ll;
			Set set; 
				HashSet hs;
				TreeSet ts;
		
		Map map;
			HashMap hm;
			TreeMap tm;
			
		// -----------------------------分隔线-----------------------------
		// Since 1.4	
		LinkedHashSet ls;
		
		LinkedHashMap lh;
		
		// -----------------------------分隔线-----------------------------
		// Since 1.5
		CopyOnWriteArrayList cowal;
		
		CopyOnWriteArraySet  cowas;		
		
		Queue<Object> queue;
			ConcurrentLinkedQueue clq;
			BlockingQueue bq;
				LinkedBlockingQueue lbq;
				ArrayBlockingQueue abq;
				DelayQueue dq;
				SynchronousQueue sq;
				PriorityBlockingQueue pbq;
				
		ConcurrentMap cm;
			ConcurrentHashMap chm;
				
		// -----------------------------分隔线-----------------------------
		// Since 1.6
		ConcurrentSkipListSet csls;	
			
		BlockingDeque bd;
			LinkedBlockingDeque lbd;
		TransferQueue tq;
		
		ConcurrentSkipListMap cslm;
		
	}
}
