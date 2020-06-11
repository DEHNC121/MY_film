# My_Film
Baza danych dla serwisu typu IMDB/FilmWeb

O aplikacji:
Aplikacja automatycznie łączy się z bazą danych na serwerze Azure. Dzięki wpisywaniu danych
 i wybrze odpowiednich opcji z menu można wyświetlać zawartość bazy i dodawać nowe rekordy 
bez znajomości postgresql.

Aby utworzyć potrzebne tabele i funkcje oraz wypełnić część z nich danymi należy wykonać komendę:
psql -h dehncserver.postgres.database.azure.com postgres dehnc@dehncserver < create.sql

Aby usunąć wszystkie tabele z bazy, należy wykonać:
psql -h dehncserver.postgres.database.azure.com postgres dehnc@dehncserver < clear.sql 

Hasło do bazy: kodkodkod1#

Opis tabel:
film, episode, season, series - podstawowe dane dotyczące filmów, odcinków, sezonów i seriali

type - tabela pomocnicza, określa, czy wpis z mark, list_content, film_* itd. odwołuje się do filmu,
serialu, sezonu czy odcinka o danym id.  

member - osoby biorące udział w tworzeniu filmu, serialu itd. (aktorzy, rzeżyserzy itd.)
team - ekipy tworzące dany film,seial itd. Pole position oznacza zawód (aktor, reżyser itd.)
character - postaci grane przez aktorów

db_user - użytkownicy serwisu do oceny filmów
mark - oceny filmów, seriali itd.
list - listy filmów, seriali itd. tworzone przez użytkowników serwisu
list_content - zawartość list

film_genre - gatunki filmów, seriali itd. 
film_language - wersje językowe filmów, seriali itd. z podanym typem (oryginalna, dubbing, lektor)
film_country - kraje produkcji
film_studio - studia będące producentami danego filmu, serialu itd.

genre - gatunki filmowe
language - języki
country - kraje
studio - studia filmowe

