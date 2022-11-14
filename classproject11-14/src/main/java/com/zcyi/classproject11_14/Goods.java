package com.zcyi.classproject11_14;

public class Goods {
    private float id;
    private String count;
    private String goodsName;
    private String goodsPic;

    public Goods(float id, String count, String goodsName, String goodsPic) {
        this.id = id;
        this.count = count;
        this.goodsName = goodsName;
        this.goodsPic = goodsPic;
    }

    public void setId(float id) {
        this.id = id;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public void setGoodsPic(String goodsPic) {
        this.goodsPic = goodsPic;
    }

    public float getId() {
        return id;
    }

    public String getCount() {
        return count;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public String getGoodsPic() {
        return goodsPic;
    }
}
