package test.pivotal.pal.tracker;

import io.pivotal.pal.tracker.TimeEntry;
import io.pivotal.pal.tracker.TimeEntryRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private HashMap<Long, TimeEntry> timeEntryHashMap = new HashMap<>();

    private final AtomicInteger count = new AtomicInteger(0);

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry.setId((long) count.incrementAndGet());
        timeEntryHashMap.put(timeEntry.getId(), timeEntry);
        return timeEntry;
    }

    @Override
    public TimeEntry find(long timeEntryId) {
        return timeEntryHashMap.get(timeEntryId);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(timeEntryHashMap.values());
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        timeEntry.setId(id);
        timeEntryHashMap.put(id, timeEntry);
        return timeEntry;
    }

    @Override
    public void delete(long id) {
        timeEntryHashMap.remove(id);
    }
}
