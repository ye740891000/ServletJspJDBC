package com.zjtyd.tms.beans;

/**
 * Created with IntelliJ IDEA.
 * User: Abbot
 * Date: 2017-10-18
 * Time: 08:55
 * Description:
 */
public class Product
{
    private String code;
    private String name;
    private float price;

    public Product()
    {

    }

    public Product(String code, String name, float price)
    {
        this.code = code;
        this.name = name;
        this.price = price;
    }


    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public float getPrice()
    {
        return price;
    }

    public void setPrice(float price)
    {
        this.price = price;
    }
}
