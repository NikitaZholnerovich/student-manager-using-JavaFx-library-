package model;

import java.util.Date;

public class Player {

    public String name;
    public Date dateOfBirth;
    public String footballTeam;
    public String hometown;
    public SquadEnum squad;
    public String position;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFootballTeam() {
        return footballTeam;
    }

    public void setFootballTeam(String footballTeam) {
        this.footballTeam = footballTeam;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public SquadEnum getSquad() {
        return squad;
    }

    public void setSquad(SquadEnum squad) {
        this.squad = squad;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }


}
