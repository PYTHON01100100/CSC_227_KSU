
public class TransportingThread extends Thread {
	public Queue JobQ;
	public Flag flag;
	public MemoryLimit limit;
	public PQueue ReadyQ_PQ = null;
	public Queue ReadyQ_Q = null;
	public Flag flag2;
	public Flag ST3;

	public TransportingThread(Queue j, Flag F, MemoryLimit M, PQueue readyQ, Flag F2, Flag ST3) {
		JobQ = j;
		flag = F;
		limit = M;
		ReadyQ_PQ = readyQ;
		flag2 = F2;
		this.ST3 = ST3;
	}

	public TransportingThread(Queue j, Flag F, MemoryLimit M, Queue readyQ, Flag F2, Flag ST3) {
		JobQ = j;
		flag = F;
		limit = M;
		ReadyQ_Q = readyQ;
		flag2 = F2;
		this.ST3 = ST3;
	}

	public void run() {
		System.out.print("");
		while (!(JobQ.isEmpty() && flag.isFlag())) {
			PCB temp = JobQ.peek();
			if (temp != null) {
				if (temp.getMemoryRequired() <= limit.getLimit()) {
					if (ReadyQ_PQ != null) {
						PCB tem = JobQ.dequeue();
						tem.setState(ThreadState.Ready);
						ReadyQ_PQ.enqueue(tem);
						limit.setLimit(limit.getLimit() - tem.getMemoryRequired());
					} else {
						PCB tem = JobQ.dequeue();
						tem.setState(ThreadState.Ready);
						ReadyQ_Q.enqueue(tem);
						limit.setLimit(limit.getLimit() - tem.getMemoryRequired());

					}
				} else {
					// Notifies the Processing Thread that the empty space in the ready queue has been used
					ST3.setFlag(true);
				}
			}
		}
		// Notifies the Processing Thread that it fully emptied the JobQueue
		ST3.setFlag(true);
		// Notifies the Processing Thread that it fully emptied the JobQueue
		flag2.setFlag(true);
		return;
	}
}
