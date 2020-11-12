package by.estore.dto;

import java.io.Serializable;
import java.util.Objects;

public class Pagination implements Serializable {
    private static final long serialVersionUID = 1L;

    private int currentPage;
    private int nextPage;
    private int prevPage;
    private int firstPage = 1;
    private int lastPage;

    public Pagination() {}

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getPrevPage() {
        return prevPage;
    }

    public void setPrevPage(int prevPage) {
        this.prevPage = prevPage;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagination that = (Pagination) o;
        return currentPage == that.currentPage &&
                nextPage == that.nextPage &&
                prevPage == that.prevPage &&
                firstPage == that.firstPage &&
                lastPage == that.lastPage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentPage, nextPage, prevPage, firstPage, lastPage);
    }

    @Override
    public String toString() {
        return "Pagination{" +
                "currentPage=" + currentPage +
                ", nextPage=" + nextPage +
                ", prevPage=" + prevPage +
                ", firstPage=" + firstPage +
                ", lastPage=" + lastPage +
                '}';
    }
}
