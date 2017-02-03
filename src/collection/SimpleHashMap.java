package collection;

import java.util.*;

/**
 * Created by EricLi on 2016/12/18.
 * Email me : EricLi1235@gmail.com.
 */
public class SimpleHashMap<K, V> extends AbstractMap<K, V> {

    static final int SIZE = 997;
    //桶位
    @SuppressWarnings("unchecked")
    LinkedList<MapEntry<K, V>>[] buckets = new LinkedList[SIZE];

    @Override
    public V put(K key, V value) {
        V oldValue = null;
        int index = Math.abs(key.hashCode()) % SIZE;//计算散列码
        if(buckets[index] == null){
            buckets[index] = new LinkedList<>();
        }
        LinkedList<MapEntry<K, V>> bucket = buckets[index];
        MapEntry<K, V> pair = new MapEntry<K, V>(key,value);
        boolean found = false;
        ListIterator<MapEntry<K, V>> it = bucket.listIterator();
        while (it.hasNext()){
            MapEntry<K, V> iPair = it.next();
            if(iPair.getKey().equals(key)){
                oldValue = iPair.getValue();
                it.set(pair);
                found = true;
                break;
            }
        }
        if(!(found)){
            buckets[index].add(pair);
        }
        return oldValue;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = new HashSet<>();
        for(LinkedList<MapEntry<K ,V>> bucket: buckets){
            if(bucket == null){
                continue;
            }
            for(MapEntry<K, V> mpair : bucket){
                set.add(mpair);
            }
        }
        return set;
    }

    public static void main(String[] args){
        SimpleHashMap<String,String> map = new SimpleHashMap<>();
        map.put("Eric", "Good");
        System.out.println(map);
        System.out.println(map.get("Eric"));
        System.out.println(map.entrySet());
        System.out.println(map.get("A"));

        //对于String而言，生成的散列码是和内容有关的
        String s = "Hello World";
        String[] hello = s.split(" ");
        System.out.println(hello[0].hashCode());
        System.out.println(hello[1].hashCode());
    }
}
