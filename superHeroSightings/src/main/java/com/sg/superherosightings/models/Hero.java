package com.sg.superherosightings.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hero
{
    private int heroID;
    private String heroName;
    private String heroDescription;
    private String superPower;

    private List<Organization> organizationList = new ArrayList<>();

    public List<Organization> getOrganizationList() {
        return organizationList;
    }

    public void setOrganizationList(List<Organization> organizationList) {
        this.organizationList = organizationList;
    }

    public int getHeroID()
    {
        return heroID;
    }

    public void setHeroID(int heroID)
    {
        this.heroID = heroID;
    }

    public String getHeroName()
    {
        return heroName;
    }

    public void setHeroName(String heroName)
    {
        this.heroName = heroName;
    }

    public String getHeroDescription()
    {
        return heroDescription;
    }

    public void setHeroDescription(String heroDescription)
    {
        this.heroDescription = heroDescription;
    }

    public String getSuperPower()
    {
        return superPower;
    }

    public void setSuperPower(String superPower)
    {
        this.superPower = superPower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hero hero = (Hero) o;
        return getHeroName().equals(hero.getHeroName()) && getHeroDescription().equals(hero.getHeroDescription()) && getSuperPower().equals(hero.getSuperPower()) && Objects.equals(getOrganizationList(), hero.getOrganizationList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHeroName(), getHeroDescription(), getSuperPower(), getOrganizationList());
    }

//    @Override
//    public int hashCode()
//    {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + heroID;
//        result = prime * result + ((heroName == null) ? 0 : heroName.hashCode());
//        result = prime * result + ((heroDescription == null) ? 0 : heroDescription.hashCode());
//        result = prime * result + ((superPower == null) ? 0 : superPower.hashCode());
//        return result;
//    }
//
//    @Override
//    public boolean equals(Object obj)
//    {
//        if (this == obj)
//            return true;
//        if (obj == null)
//            return false;
//        if (getClass() != obj.getClass())
//            return false;
//        Hero other = (Hero) obj;
//        if (heroID != other.heroID)
//            return false;
//        if (heroName == null)
//        {
//            if (other.heroName != null)
//                return false;
//        }
//        else if (!heroName.equals(other.heroName))
//            return false;
//        if (heroDescription == null)
//        {
//            if (other.heroDescription != null)
//                return false;
//        }
//        else if (!heroDescription.equals(other.heroDescription))
//            return false;
//        if (superPower == null)
//        {
//            if (other.superPower != null)
//                return false;
//        }
//        else if (!superPower.equals(other.superPower))
//            return false;
//        return true;
//    }
}