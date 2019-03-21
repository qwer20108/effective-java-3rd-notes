package item13;

public class HashTableCloneable implements Cloneable {
    private Entry[] buckets;

    private static class Entry {
        final Object key;
        Object value;
        Entry next;

        Entry(Object key, Object value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
        // Recursively copy the linked list headed by this Entry
        /*
                因為遞迴 一直 call stack 效率不好
                Entry deepCopy() {
                    return new Entry(key, value, next == null ? null : next.deepCopy());
                }
                */
        // 改為迴圈 new Entry
        // Iteratively copy the linked list headed by this Entry
        Entry deepCopy() {
            Entry result = new Entry(key, value, next);
            for (Entry p = result; p.next != null; p = p.next)
                p.next = new Entry(p.next.key, p.next.value, p.next.next);
            return result;
        }
    }

    @Override
    public HashTableCloneable clone() {
        try {
            HashTableCloneable result = (HashTableCloneable) super.clone();
            result.buckets = new Entry[buckets.length];
            for (int i = 0; i < buckets.length; i++) if (buckets[i] != null) result.buckets[i] = buckets[i].deepCopy();
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
