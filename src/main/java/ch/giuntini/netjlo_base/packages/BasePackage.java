package ch.giuntini.netjlo_base.packages;

import java.io.Serializable;

public class BasePackage implements Serializable {

    public final String information;

    public BasePackage(String information) {
        this.information = information;
    }
}
