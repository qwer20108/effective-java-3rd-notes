package ch4.item22;


import static ch4.item22.PhysicalConstants.AVOGADROS_NUMBER;

// Use of static import to avoid qualifying constants
public class TestPhysicalConstants {
    double atoms(double mols) {
        return AVOGADROS_NUMBER * mols;
    }
    // Many more uses of PhysicalConstants justify static import

}
