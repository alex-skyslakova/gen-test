import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.concurrent.atomic.AtomicInteger;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckpointSyncTest {

    @BeforeEach
    public void setUp() {
        // Reset static variables before each test
        CheckpointSync.Worker.nFinished = 0;
        CheckpointSync.Worker.nWorkers = 0;
    }

    @Test
    public void testSingleTaskSingleWorker() {
        CheckpointSync.Worker.nWorkers = 1;
        CheckpointSync.runTasks(1);
        assertEquals(0, CheckpointSync.Worker.nFinished, "nFinished should be reset to 0 after checkpoint");
    }

    @Test
    public void testMultipleTasksSingleWorker() {
        CheckpointSync.Worker.nWorkers = 1;
        CheckpointSync.runTasks(3);
        assertEquals(0, CheckpointSync.Worker.nFinished, "nFinished should be reset to 0 after each checkpoint");
    }

    @Test
    public void testSingleTaskMultipleWorkers() {
        CheckpointSync.Worker.nWorkers = 5;
        CheckpointSync.runTasks(1);
        assertEquals(0, CheckpointSync.Worker.nFinished, "nFinished should be reset to 0 after checkpoint");
    }

    @Test
    public void testMultipleTasksMultipleWorkers() {
        CheckpointSync.Worker.nWorkers = 5;
        CheckpointSync.runTasks(3);
        assertEquals(0, CheckpointSync.Worker.nFinished, "nFinished should be reset to 0 after each checkpoint");
    }

    @Test
    public void testWorkersSynchronization() {
        CheckpointSync.Worker.nWorkers = 3;
        AtomicInteger completedTasks = new AtomicInteger(0);

        // Override the checkpoint method to track task completion
        CheckpointSync.Worker.checkpoint = () -> {
            while (CheckpointSync.Worker.nFinished != CheckpointSync.Worker.nWorkers) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            completedTasks.incrementAndGet();
            CheckpointSync.Worker.nFinished = 0;
        };

        CheckpointSync.runTasks(2);
        assertEquals(2, completedTasks.get(), "All tasks should be completed and synchronized correctly");
    }
}
