public class Wezel {

    int klucz;
    Wezel lewy;
    Wezel prawy;
    int wywazenie;

    public Wezel(int klucz)
    {
        this.klucz = klucz;
        lewy = null;
        prawy = null;
        wywazenie = 0;
    }


}
