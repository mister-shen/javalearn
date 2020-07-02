package cn.shenrs.com.movielease;

/**
 * @author shenrs
 * @Description
 * @create 2019-12-03 17:19
 **/
public abstract class Price {
    /**
     * 取得价格代号
     * @return
     */
    abstract int getPriceCode();

    /**
     * // 取得影片出租价格
     * @param daysRented
     * @return
     */
    abstract double getCharge(int daysRented);

    /**
     * 取得 累加 常客积点  在 superclass 内保留它，使它成为㆒种缺省行为
     * @param daysRented
     * @return
     */
    int getFrequentRenterPoints(int daysRented){
        return 1;
    }
}

class ChildrensPrice extends Price {
    @Override
    int getPriceCode() {
        return Movie.CHILDRENS;
    }

    @Override
    double getCharge(int daysRented) {
        double result = 1.5;
        if (daysRented > 3) {
            result += (daysRented - 3) * 1.5;
        }
        return result;
    }
}
class NewReleasePrice extends Price {
    @Override
    int getPriceCode() {
        return Movie.NEW_RELEASE;
    }

    @Override
    double getCharge(int daysRented) {
        return daysRented * 3;
    }

    @Override
    int getFrequentRenterPoints(int daysRented) {
        return daysRented > 1 ? 2 : 1;
    }
}
class RegularPrice extends Price {
    @Override
    int getPriceCode() {
        return Movie.REGULAR;
    }

    @Override
    double getCharge(int daysRented) {
        double result = 2;
        if (daysRented > 2) {
            result += (daysRented - 2) * 1.5;
        }
        return result;
    }
}
