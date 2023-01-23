package ru.SOHNick;

public abstract class myFunction
{
    public  Double F(Double x)
    {
        return (1+Math.pow(x+2,2));
    }
    public  Double FXT(Double t)
    {
        return (Math.asin(Math.sin(t)));
    }
    public Double FYT(Double t)
    {
        return (Math.acos(Math.cos(t)));
    }

}
