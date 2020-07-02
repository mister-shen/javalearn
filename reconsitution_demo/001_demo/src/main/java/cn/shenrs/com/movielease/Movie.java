package cn.shenrs.com.movielease;

/**
 * @author shenrs
 * @Description Movie(影 片) 只是一个简单的 data class （纯数据类）。
 * @create 2019-12-02 14:55
 **/
public class Movie {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    /**
     * 名称
     */
    private String _title;
    /**
     * 价格（代号）
     */
    private int _priceCode;

    private Price _price;

    public Movie(String title, int priceCode){
        _title = title;
        // 译注：这就是㆒个 set method
        setPriceCode(priceCode);
    }
    public int getPriceCode(){
        return _priceCode;
    }

    public void setPriceCode(int arg){
        switch (arg) {
            // 普通片
            case REGULAR:
                _price = new RegularPrice();
                break;
            // 儿童片
            case CHILDRENS:
                _price = new ChildrensPrice();
                break;
            // 新片
            case NEW_RELEASE:
                _price = new NewReleasePrice();
                break;
            default:
                throw new IllegalArgumentException("Incorrect Price Code");
        }
    }
    public String getTitle(){
        return _title;
    }

    public double getCharge(int daysRented){
        return _price.getCharge(daysRented);
    }

    public int getFrequentRenterPoints(int daysRented){
        return _price.getFrequentRenterPoints(daysRented);
    }
}
