public class Pair implements Comparable<Pair>{
    private String key;
    private String value;

    public Pair(String key, String value){
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int compareTo(Pair pair) {
       return this.key.compareTo(pair.getKey());
    }
}
