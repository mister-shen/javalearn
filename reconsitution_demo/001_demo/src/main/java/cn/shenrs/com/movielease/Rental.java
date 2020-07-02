package cn.shenrs.com.movielease;

/**
 * @author shenrs
 * @Description Rental  class  表示「某个顾客租了㆒部影片」。
 * @create 2019-12-02 14:56
 **/
public class Rental {
    private Movie _movie;  // 影片
    private int _daysRented;  // 租期
    public Rental(Movie movie, int daysRented) {
        _movie = movie;
        _daysRented = daysRented;
    }
    public int getDaysRented() {
        return _daysRented;
    }
    public Movie getMovie() {
        return _movie;
    }

    public int getPriceCode(){
        return _movie.getPriceCode();
    }

    /**
     * 取得影片出租价格
     * @return
     */
    public double getCharge() {
        return _movie.getCharge(_daysRented);
    }

    /**
     *  add frequent renter points（累加 常客积点）
     * @return
     */
    int getFrequentRenterPoints() {
        return _movie.getFrequentRenterPoints(_daysRented);
    }
}
