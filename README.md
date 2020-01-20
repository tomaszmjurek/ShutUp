# ShutUp

System zdalnego zamykania systemów operacyjnych z jądrem Linux za pośrednictwem urządzenia mobilnego w systemie Android.

## Opis działania i protokołu komunikacyjnego

System działa w modelu klienci (Linux) i serwer (Android) z wykorzystaniem protokołu TCP. Urządzenia komunikują się za pośrednictwem gniazda (Socket). Po uruchomieniu systemu wątek serwera stale nasłuchuje na ustalonym z góry porcie, który jest zapisany w aplikacji klienta. Klient natomiast próbuje się z serwerem połączyć znając jego IP w sieci lokalnej oraz wspomniany port. Gdy połączenie zostanie uzyskane możliwa jest dalsza komunikacja. Po wybraniu klienta z listy dostępnych urządzeń oraz wciśnięciu przycisku "SHUT DOWN" zostaje przez zapisane gniazdo (zawiera informacje o IP i porcie na którym nasłuchuje klient - może być dowolny dostępny) wysłane polecenie zamknięcia systemu Linux. Klient po uzyskaniu połączenia czeka na komunikat następnie wykonuje go. Utworzone gniazda zostają odpowiednio wcześniej zamknięte.

## Opis implementacji

Aplikacja serwera została napisana w języku Kotlin z wykorzystaniem java.net.Socket oraz java.net.ServerSocket. W pliku activity_main.xml napisany został wygląd aplikacji. Kod zawiera 3 klasy: Server.kt - nasłuchuje nowych klientów, posiada listę aktywnych oraz odpowiada za komunikację, Client.kt - zawiera informacje dotyczące aktywnego gniazda oraz ip klienta, MainActivity.kt - odpowiada za wyświetlanie kontekstu i obsługę przycisków.

client.c - zawiera potrzebne dane serwera, tworzy gniazdo służące do połączenia, czeka na połączenie oraz odczytuje polecenie serwera i wykonuje je (shutdown -h now)

## Kompilacja i uruchomienie

Client
```
$ sudo sh setup.sh
```
Serwer
- wgrać aplikację przy włączonym module debugowania USB
- kliknąc ikonę w celu uruchomienia
