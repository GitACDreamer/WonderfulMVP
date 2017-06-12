package cn.net.sunet.base;

/*
 * Author: Leland
 * Email:  LelandACM@gmail.com
 * Date:   2017/1/17
   Modify: 2017/1/17
 * Description: Gank base response
 */

public class GankBaseResponse<T> {
    private int count;
    private boolean error;
    private T results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    public boolean getError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
