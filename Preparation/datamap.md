Budoucí data

1. Model Předmětu
Název předmětu / string / Pokročilé programování
Zkratka předmětu / string / AK8PO
Počet kreditů / int / např: 5  
Počet týdnů / int /
Počet přednášek / int /
Počet seminářů / int /
Zakončení předmětu / enum / zápočet, zkouška
Jazyk / enum / CZ, AJ
Jméno vyučujícího / string /
Garant / enum / 

2. Model Zaměstnanec
Titul před jménem / string / Ing.
Jméno / string / Pavel
Příjmení / string / Novák
Titul za jménem / string / Dis.
Telefon / string / +420565565001
Mobil / string / +420702666001
Email / string / pavel.novak@utb.cz
Kancelář / string / 
Úvazek / enum / poloviční, plný

3. Model Skupinky
Název skupinky / string / Softwarové inženýrství
Zkratka skupinky / string / SWI, ITA
Ročník / int / 1
Semestr / enum / letní semestr (LS), zimní semestr (ZS)
Počet studentů / int / např: 16
Forma studia / enum / P, K, PH
Typ studia / enum / Bc., Ing., Mgr., PhDr.
Jazyk / enum / CZ, AJ


4. Model Pracovního štítku
Název / string / Cvičení AP8PO 1
Zaměstnanec / reference na zaměstnance nebo null
Předmět / reference na zkratku předmětu nebo null 
Typ / enum / přednáška, cvičení, seminář, zápočet, klasifikovaný zápočet zkouška
Počet studentů – int – 11
Počet hodin – int – 0 u z, zk, kl, protože se počítá na člověka, číslo u p, c, s, (např. 2)
Počet týdnů – int – 0 u z, zk, kl, protože se počítá na člověka, číslo u p, c, s, (např. 14)
Jazyk / enum / CZ, AJ
Počet bodů za pracovní štítek – int – 12.5 (Toto nebude vlastnost, ale metoda.)
