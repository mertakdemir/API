package practice;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Test05Pojo {

    private Object meta;
    private Test5InnerPojo data;

    public Test05Pojo(Object meta, Test5InnerPojo data) {
        this.meta = meta;
        this.data = data;
    }

    public Test05Pojo() {
    }

    public Object getMeta() {
        return meta;
    }

    public void setMeta(Object meta) {
        this.meta = meta;
    }

    public Test5InnerPojo getData() {
        return data;
    }

    public void setData(Test5InnerPojo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Test05Pojo{" +
                "meta=" + meta +
                ", data=" + data +
                '}';
    }
}
