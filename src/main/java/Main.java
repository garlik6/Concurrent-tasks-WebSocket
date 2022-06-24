import com.example.priorityqueue.model.Job;
import com.example.priorityqueue.model.JobPriority;

public class Main {
    public static void main(String[] args) {
        int POOL_SIZE = 1;
        int QUEUE_SIZE = 10;
        Job job1 = new Job("Job1", JobPriority.LOW, 1);
        Job job2 = new Job("Job2", JobPriority.MEDIUM, 1);
        Job job3 = new Job("Job3", JobPriority.HIGH, 1);
        Job job4 = new Job("Job4", JobPriority.MEDIUM, 1);
        Job job5 = new Job("Job5", JobPriority.LOW, 1);
        Job job6 = new Job("Job6", JobPriority.HIGH, 1);

        PriorityJobScheduler pjs = new PriorityJobScheduler(
                POOL_SIZE, QUEUE_SIZE);

        pjs.scheduleJob(job1);
        pjs.scheduleJob(job2);
        pjs.scheduleJob(job3);
        pjs.scheduleJob(job4);
        pjs.scheduleJob(job5);
        pjs.scheduleJob(job6);
    }
}
