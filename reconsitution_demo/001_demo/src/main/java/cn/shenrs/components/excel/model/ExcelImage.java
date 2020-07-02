//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.shenrs.components.excel.model;

import java.io.Serializable;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.ss.usermodel.ClientAnchor;

public class ExcelImage implements Serializable {
    private static final long serialVersionUID = 8049228766726486041L;
    private String imgPath;
    private int startCellColumn;
    private int startCellRow;
    private int endCellColumn;
    private int endCellRow;
    private int startCellX;
    private int startCellY;
    private int endCellX;
    private int endCellY;
    private ClientAnchor anchor;

    public ExcelImage(String imgPath, int startCellRow, int startCellColumn, int endCellRow, int endCellColumn, int startCellX, int startCellY, int endCellX, int endCellY) {
        this.imgPath = imgPath;
        this.startCellRow = startCellRow;
        this.startCellColumn = startCellColumn;
        this.endCellRow = endCellRow;
        this.endCellColumn = endCellColumn;
        this.startCellX = startCellX;
        this.startCellY = startCellY;
        this.endCellX = endCellX;
        this.endCellY = endCellY;
        this.anchor = new HSSFClientAnchor(startCellX, startCellY, endCellX, endCellY, (short)startCellColumn, startCellRow, (short)endCellColumn, endCellRow);
    }

    public String getImgPath() {
        return this.imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public int getStartCellColumn() {
        return this.startCellColumn;
    }

    public void setStartCellColumn(int startCellColumn) {
        this.startCellColumn = startCellColumn;
    }

    public int getStartCellRow() {
        return this.startCellRow;
    }

    public void setStartCellRow(int startCellRow) {
        this.startCellRow = startCellRow;
    }

    public int getEndCellColumn() {
        return this.endCellColumn;
    }

    public void setEndCellColumn(int endCellColumn) {
        this.endCellColumn = endCellColumn;
    }

    public int getEndCellRow() {
        return this.endCellRow;
    }

    public void setEndCellRow(int endCellRow) {
        this.endCellRow = endCellRow;
    }

    public int getStartCellX() {
        return this.startCellX;
    }

    public void setStartCellX(int startCellX) {
        this.startCellX = startCellX;
    }

    public int getStartCellY() {
        return this.startCellY;
    }

    public void setStartCellY(int startCellY) {
        this.startCellY = startCellY;
    }

    public int getEndCellX() {
        return this.endCellX;
    }

    public void setEndCellX(int endCellX) {
        this.endCellX = endCellX;
    }

    public int getEndCellY() {
        return this.endCellY;
    }

    public void setEndCellY(int endCellY) {
        this.endCellY = endCellY;
    }

    public ClientAnchor getAnchor() {
        return this.anchor;
    }

    public void setAnchor(ClientAnchor anchor) {
        this.anchor = anchor;
    }
}
