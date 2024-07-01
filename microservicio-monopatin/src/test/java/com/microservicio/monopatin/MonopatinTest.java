package com.microservicio.monopatin;

import com.microservicio.monopatin.model.Monopatin;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class MonopatinTest {

    @DataProvider(name= "monopatinProvider")
    public Monopatin[] generarMonopatines() {
        return new Monopatin[]{
                new Monopatin("BMW"),
                new Monopatin("Mercedes"),
                new Monopatin("Volkswagen")
        };
    }

    @Test(dataProvider = "monopatinProvider")
    public void testSetDisponible(Monopatin monopatin) {
        Assert.assertEquals(monopatin.getEstado(), "DISPONIBLE");
    }

    @Test(dataProvider = "monopatinProvider")
    public void testKm(Monopatin monopatin) {
        monopatin.setKmAcumulados(139);
        Assert.assertEquals(monopatin.getKmAcumulados(), 139);
    }

    @Test(dataProvider = "monopatinProvider")
    public void testModelo(Monopatin monopatin) {
        Assert.assertEquals(monopatin.getModelo(), "BMW");
    }

}
