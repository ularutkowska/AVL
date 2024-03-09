import java.io.FileWriter;
import java.io.IOException;

public class Drzewo {

    Wezel korzen;

    boolean z1;
    boolean z2;

    public Wezel search(Wezel a, int klucz) {
        if (a == null)
            return null;

        if (a.klucz == klucz)
            return a;

        if (a.klucz < klucz)
            return search(a.prawy, klucz);
        else return search(a.lewy, klucz);

    }

    public Wezel search1(int klucz)
    {
        return search(korzen, klucz);
    }


    public int poziom(Wezel a, int klucz,int poziom)
    {

        if(klucz == a.klucz)
            return poziom;
        if(klucz < a.klucz)
        {
            poziom++;
            return poziom(a.lewy, klucz, poziom);
        }
        else{
            poziom++;
            return poziom(a.prawy, klucz, poziom);
        }
    }
    public int poziom1(int klucz)
    {
        if(search1(klucz) == null)
        {
            System.out.println("Nie znaleziono." );
            return -1;
        }
        int poziom = 0;
        return poziom(korzen, klucz, poziom);
    }


    public Wezel insert(Wezel a, int klucz)
    {
        if(a == null)
        {
            z1 = false;
            return new Wezel(klucz);
        }
        if(a.klucz > klucz)
        {
            a.lewy = insert(a.lewy, klucz);
            if(!z1)
                return rownowagainpr(a);
        }
        if(a.klucz < klucz)
        {
            a.prawy = insert(a.prawy, klucz);
            if(!z1)
                return rownowagainle(a);
        }

        z1 = true;
        return a;
    }
    public void insert1(int klucz)
    {
        if(search1(klucz)!=null)
            return;
        korzen = insert(korzen, klucz);
    }


    public Wezel rownowagainpr(Wezel a)
    {
        if(a.wywazenie == 1)
        {
            if(a.lewy.wywazenie == 1)
            {
                a = rotacjaprawo(a);
                a.prawy.wywazenie = 0;
                }else {
                a.lewy = rotacjalewo(a.lewy);
                a = rotacjaprawo(a);
                if (a.wywazenie == 0) {
                    a.prawy.wywazenie = 0;
                    a.lewy.wywazenie = 0;

                } else if (a.wywazenie == 1) {
                    a.prawy.wywazenie = -1;
                    a.lewy.wywazenie = 0;

                } else if (a.wywazenie == -1) {
                    a.prawy.wywazenie = 0;
                    a.lewy.wywazenie = 1;
                }

            }
            a.wywazenie = 0;
            z1 = true;
            }else if(a.wywazenie == 0)
            {
                a.wywazenie = 1;
                z1 = false;
            }else if(a.wywazenie == -1)
            {
                a.wywazenie = 0;
                z1 = true;
            }
        return a;
    }

    public Wezel rownowagainle(Wezel a)
    {
        if(a.wywazenie == -1)
        {
            if(a.prawy.wywazenie == -1)
            {
                a = rotacjalewo(a);
                a.lewy.wywazenie = 0;
            }else {
                a.prawy = rotacjaprawo(a.prawy);
                a = rotacjalewo(a);
                if (a.wywazenie == 0) {
                    a.prawy.wywazenie = 0;
                    a.lewy.wywazenie = 0;

                } else if (a.wywazenie == 1) {
                    a.prawy.wywazenie = -1;
                    a.lewy.wywazenie = 0;

                } else if (a.wywazenie == -1) {
                    a.prawy.wywazenie = 0;
                    a.lewy.wywazenie = 1;
                }

            }
            a.wywazenie = 0;
            z1 = true;
        }else if(a.wywazenie == 0)
        {
            a.wywazenie = -1;
            z1 = false;
        }else if(a.wywazenie == 1)
        {
            a.wywazenie = 0;
            z1 = true;
        }
        return a;
    }

    public static Wezel rotacjalewo(Wezel a)
    {
        Wezel b = a.prawy;
        a.prawy = b.lewy;
        b.lewy = a;
        a = b;
        return a;

    }
    public static Wezel rotacjaprawo(Wezel a)
    {
        Wezel b = a.lewy;
        a.lewy = b.prawy;
        b.prawy = a;
        a = b;
        return a;
    }

    public void KLP(Wezel a)
    {
        if(a != null)
        {
            System.out.println(a.klucz + "(" + a.wywazenie + ") ");
            KLP(a.lewy);
            KLP(a.prawy);

        }
    }
    public void KLP1()
    {
        KLP(korzen);
    }

    public void delete1(int klucz)
    {
        if(search1(klucz) == null)
        {
            System.out.println("Nie ma takiego klucza.");
            return;
        }
        korzen = delete(klucz, korzen);
    }
    public Wezel delete(int klucz, Wezel a)
    {
        if(a.klucz < klucz)
        {
            a.prawy = delete(klucz, a.prawy);
            if(!z2)
                return rownowagadpr(a);
        }else if(a.klucz > klucz)
        {
            a.lewy = delete(klucz, a.lewy);
            if(!z2)
                return rownowagadle(a);
        }else if (a.prawy == null || a.lewy == null) {
            {
                z2 = false;
                if (a.prawy == null)
                    a = a.lewy;
                else a = a.prawy;
            }
        }else {
            Wezel b = m(a.lewy);
            a.klucz = b.klucz;
            a.lewy = delete(b.klucz, a.lewy);
            if(!z2)
                return rownowagadle(a);
        }

        return a;
    }

    public Wezel m(Wezel a)
    {
        Wezel b = a;
        while(b.prawy != null)
            b = b.prawy;
        return b;
    }
    public Wezel rownowagadpr(Wezel a)
    {
        if(a.wywazenie == 1)
        {
            if(a.lewy.wywazenie >= 0)
            {
                a = rotacjaprawo(a);
                if(a.prawy.wywazenie == 0)
                {
                    a.wywazenie = 1;
                    a.prawy.wywazenie = -1;
                    z2 = true;
                }else {
                    a.wywazenie = 0;
                    a.prawy.wywazenie = 0;

                }
            }else
            {
                a.lewy = rotacjalewo(a.lewy);
                a = rotacjaprawo(a);

                if(a.wywazenie == 0)
                {
                    a.prawy.wywazenie = 0;
                    a.lewy.wywazenie = 0;
                }
                else if(a.wywazenie == 1)
                {
                    a.prawy.wywazenie = -1;
                    a.lewy.wywazenie = 0;
                }else {
                    a.lewy.wywazenie = -1;
                    a.prawy.wywazenie = 0;
                }
                a.wywazenie = 0;
            }
            return a;
        }
        if(a.wywazenie == 0)
        {
            a.wywazenie = 1;
            z2 = true;
            return a;
        }
        if(a.wywazenie == -1)
        {
            a.wywazenie = 0;
            return a;
        }

        return a;

    }
    public Wezel rownowagadle(Wezel a)
    {
        if(a.wywazenie == -1)
        {
            if(a.prawy.wywazenie <= 0)
            {
                a = rotacjalewo(a);
                if(a.wywazenie == -1)
                {
                    a.wywazenie = 0;
                    a.lewy.wywazenie = 0;
                    z2 = true;
                }else {
                    a.wywazenie = 1;
                    a.lewy.wywazenie = -1;

                }
            }else
            {
                a.prawy = rotacjaprawo(a.prawy);
                a = rotacjalewo(a);

                if(a.wywazenie == 0)
                {
                    a.prawy.wywazenie = 0;
                    a.lewy.wywazenie = 0;
                }
                else if(a.wywazenie == -1)
                {
                    a.prawy.wywazenie = 0;
                    a.lewy.wywazenie = 1;
                }else {
                    a.lewy.wywazenie = 0;
                    a.prawy.wywazenie = -1;
                }
                a.wywazenie = 0;
            }
            return a;
        }
        if(a.wywazenie == 0)
        {
            a.wywazenie = -1;
            z2 = true;
            return a;
        }
        if(a.wywazenie == 1)
        {
            a.wywazenie = 0;
            return a;
        }

        return a;
    }
    public void zapisz(FileWriter fw, Wezel a) throws IOException {
        if (a != null) {
            fw.write(a.klucz + " (" + a.wywazenie+"), ");
            System.out.println();
            zapisz(fw,a.lewy);
            zapisz(fw,a.prawy);
        }
    }

    public void zapisz1(String plik) throws IOException {
        FileWriter fw = new FileWriter(plik);
        zapisz(fw, korzen);
        fw.close();
    }
}
