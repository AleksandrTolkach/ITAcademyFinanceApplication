package by.tolkach.classifier.dto;

public class SimplePageable {

    private int page;
    private int size;

    public SimplePageable() {
    }

    public SimplePageable(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public static SimplePageable of(int page, int size) {
        SimplePageable pageable = new SimplePageable();
        pageable.setPage(page);
        pageable.setSize(size);
        return pageable;
    }
}
