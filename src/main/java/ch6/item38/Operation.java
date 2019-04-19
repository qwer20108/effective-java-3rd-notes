package ch6.item38;

// Emulated extensible enum using an interface
public interface Operation {
    double apply(double x, double y);
}