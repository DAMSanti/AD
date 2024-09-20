package org.example.objetos;

public class Acts {
    private int id;
    private int character_id;
    private int movie_id;
    private int minutes_in_movie;
    private int main_character;
    private String actor;

    public Acts(int id, int character_id, int movie_id, int minutes_in_movie, int main_character, String actor) {
        this.id = id;
        this.character_id = character_id;
        this.movie_id = movie_id;
        this.minutes_in_movie = minutes_in_movie;
        this.main_character = main_character;
        this.actor = actor;
    }

    public Acts(int character_id, int movie_id, int minutes_in_movie, int main_character, String actor) {
        this.character_id = character_id;
        this.movie_id = movie_id;
        this.minutes_in_movie = minutes_in_movie;
        this.main_character = main_character;
        this.actor = actor;
    }

    public int getId() {
        return id;
    }

    public int getCharacter_id() {
        return character_id;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public int getMinutes_in_movie() {
        return minutes_in_movie;
    }

    public int isMain_character() {
        return main_character;
    }

    public String getActor() {
        return actor;
    }

    @Override
    public String toString() {
        return "Acts{" +
                "id=" + id +
                ", character_id=" + character_id +
                ", movie_id=" + movie_id +
                ", minutes_in_movie=" + minutes_in_movie +
                ", main_character=" + main_character +
                ", actor='" + actor + '\'' +
                '}';
    }
}
