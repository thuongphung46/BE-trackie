package vn.kma.hrmactvn.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataFilter<T> {
    private int page;
    private int size;
    private long total;
    private long totalPages;
    private List<T> data;

    public DataFilter(int page, int size, long total, List<T> data) {
        this.page = page;
        this.size = size;
        this.total = total;
        this.data = data;
        this.calculateTotalPages();
    }

    public void calculateTotalPages() {
        this.totalPages = this.size == 0 ? 1 : (int) Math.ceil((double) this.total / (double) getSize());
    }
}
