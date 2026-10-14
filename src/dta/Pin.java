package dta;

public abstract class Pin {
    public boolean fired;
    public abstract void onFire();
    public void fire(){
        fired = true;
        onFire();
    }
}
