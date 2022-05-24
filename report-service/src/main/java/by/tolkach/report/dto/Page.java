package by.tolkach.report.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.ArrayList;
import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Page<T> {

    private Integer number;
    private Integer size;
    private Integer totalPages;
    private Integer totalElements;
    private boolean first;
    private Integer numberOfElements;
    private boolean last;
    private List<T> content = new ArrayList<>();

    public Page() {
    }

    public Page(Integer number, Integer size, Integer totalPages, Integer totalElements,
                boolean first, Integer numberOfElements, boolean last, List<T> content) {
        this.number = number;
        this.size = size;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.first = first;
        this.numberOfElements = numberOfElements;
        this.last = last;
        this.content = content;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public Integer getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(Integer numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public void add(T content) {
        this.content.add(content);
    }

    public Page<T> createPageProperty(Integer number, Integer size, Integer totalElements, Integer totalPages,
                                      boolean first, Integer numberOfElements, boolean last) {
        this.setNumber(number);
        this.setSize(size);
        this.setTotalElements((totalElements));
        this.setTotalPages(totalElements / size);
        this.setFirst(number == 0);
        this.setNumberOfElements(numberOfElements);
        this.setLast(number >= totalPages);
        return this;
    }

    public static class Builder<T> {

        private Integer number;
        private Integer size;
        private Integer totalPages;
        private Integer totalElements;
        private boolean first;
        private Integer numberOfElements;
        private boolean last;
        private List<T> content = new ArrayList<>();

        private Builder() {
        }

        public static <Z> Builder<Z> createBuilder(Class<Z> zClass) {
            return new Builder<Z>();
        }

        public Builder<T> setNumber(Integer number) {
            this.number = number;
            return this;
        }

        public Builder<T> setSize(Integer size) {
            this.size = size;
            return this;
        }

        public Builder<T> setTotalPages(Integer totalPages) {
            this.totalPages = totalPages;
            return this;
        }

        public Builder<T> setTotalElements(Integer totalElements) {
            this.totalElements = totalElements;
            return this;
        }

        public Builder<T> setFirst(boolean first) {
            this.first = first;
            return this;
        }

        public Builder<T> setNumberOfElements(Integer numberOfElements) {
            this.numberOfElements = numberOfElements;
            return this;
        }

        public Builder<T> setLast(boolean last) {
            this.last = last;
            return this;
        }

        public Builder<T> setContent(List<T> content) {
            this.content = content;
            return this;
        }

        public Page<T> build() {
            return new Page<>(number, size, totalPages, totalElements, first, numberOfElements, last, content);
        }
    }
}
