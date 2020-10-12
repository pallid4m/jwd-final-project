package by.estore.controller.dto;

import java.io.Serializable;
import java.util.Objects;

public class Pagination implements Serializable {
    private static final long serialVersionUID = 1L;

    private int currentPage = 1;
    private int nextPage = 2;
    private int prevPage = 0;
    private int lastPage;
    private int countPage;

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

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public int getcountPage() {
        return countPage;
    }

    public void setcountPage(int countPage) {
        this.countPage = countPage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagination that = (Pagination) o;
        return currentPage == that.currentPage &&
                nextPage == that.nextPage &&
                prevPage == that.prevPage &&
                lastPage == that.lastPage &&
                countPage == that.countPage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentPage, nextPage, prevPage, lastPage, countPage);
    }

    @Override
    public String toString() {
        return "Pagination{" +
                "currentPage=" + currentPage +
                ", nextPage=" + nextPage +
                ", prevPage=" + prevPage +
                ", lastPage=" + lastPage +
                ", countPage=" + countPage +
                '}';
    }
}
