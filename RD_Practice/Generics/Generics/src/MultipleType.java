public class MultipleType<K, V> implements GenericInterface<K, V> {

    private K key;
    private V value;

    public MultipleType(K key, V value) {
	this.key = key;
	this.value = value;
    }

    public K getKey()	{ return key; }
    public V getValue() { return value; }
}